# requirement 
# pip install numpy

"""
author: fu wei
date: 10/26/2020

"""
import time
import datetime
import random
import numpy as np
import pymysql.cursors

# params used to simplify the rout-line
MAX_HEARTBEAT = 100
MIN_HEARTBEAT = 45
MAX_GRADIENT = (MAX_HEARTBEAT-MIN_HEARTBEAT) * 0.75
WINDOW_SIZE = 10

MAX_DISTANCE = 100

SLEEP_TIME = 3

if __name__ == '__main__':

	while True:
		# 连接数据库
		connect = pymysql.Connect(
			# host='10.109.246.54',
			# port=3306,
			# user='root',
			# passwd='hadoop',
			# db='new_edge_computing_service',
			# charset='utf8'
			host='127.0.0.1',
			port=3306,
			user='root',
			passwd='a128263',
			db='edge_computing_service',
			charset='utf8'
		)

		# 获取游标
		cursor = connect.cursor()

		# 获取犯人id
		sql = "SELECT prisoner_id FROM prisoner;"
		cursor.execute(sql)
		prisoners = []
		for row in cursor.fetchall():
			prisoners.append(row[0])

		for prisoner in prisoners:
			try:
				# 下面函数计算 value和min_value-max_value之间的偏差
				def deviation(value, min_value, max_value):
					return 0 if min_value <= value <= max_value else min(abs(max_value - value), abs(value - min_value)) / float(max_value - min_value)
				#规范化各项得分
				def normalization(score):
					if score >= 1:
						return 0.999
					elif score < 0:
						return 0
					else:
						return score

				sql = """
					select create_at, heart_beat from prisoner_heartbeat 
					WHERE prisoner_id='%s' 
					order by create_at desc LIMIT %d
				""" % (prisoner, WINDOW_SIZE)
				cursor.execute(sql)

				# 获取犯人最新几条心率记录的时间戳和心率
				heartbeats = []
				for row in cursor.fetchall():
					heartbeats.append((row[0], int(row[1])))
				heartbeats.reverse()
				# print(heartbeats)
				count = len(heartbeats)

				# 心率是否有效
				# now = datetime.datetime.now()
				# heart_beat_valid = True
				# if (now - heartbeats[-1][0]).seconds >= 17:
				# 	heart_beat_valid = False

				# 计算梯度
				gradients = []
				intervals = []
				if count > 1:
					for i in range(1, count):
						interval = float((heartbeats[i][0] - heartbeats[i-1][0]).seconds)
						print(interval)
						intervals.append(interval)
						gradients.append(abs(heartbeats[i][1] - heartbeats[i-1][1]) / interval)
					total_interval = float((heartbeats[-1][0] - heartbeats[0][0]).seconds)
					total_gradient = (heartbeats[-1][1] - heartbeats[0][1]) / total_interval
				else:
					gradients = [0]
					intervals = [0]
					total_interval = 0
					total_gradient = 0
				# print(gradients)

				# 计算心率异常得分
				heartbeat_scores = []
				# 每次采样的心率平均变化程度 偏差
				heartbeat_scores.append(normalization(deviation(np.max(gradients) * np.mean(intervals), 0, MAX_GRADIENT)))
				# 心率的变化程度 偏差
				heartbeat_scores.append(normalization(deviation(total_gradient * total_interval, 0, MAX_GRADIENT) if total_gradient >= 0 else 0))
				# 连续心率拔高 偏差 平均值
				heartbeat_scores.append(normalization(np.max([np.mean([deviation(heartbeats[i][1], MIN_HEARTBEAT, MAX_HEARTBEAT) for i in range(c, count)]) for c in [0, count // 3, count // 2]])))
				# 自己给自己加权，即异常得分越高，权重越高
				heartbeat_scores_sum = np.sum(heartbeat_scores)
				heartbeat_score = 0
				if heartbeat_scores_sum > 0:
					heartbeat_score = np.sqrt(np.sum([(i / heartbeat_scores_sum) * i for i in heartbeat_scores]))
				# print(heartbeat_score)

				# 取出行为异常评分
				sql = """
					SELECT create_at,video_risk_level, abnormal_type FROM video_anomaly 
					WHERE car_no IN (SELECT car_no FROM convoy WHERE prisoner_id=%s) 
					ORDER BY create_at DESC LIMIT 1;
				""" % (prisoner)
				cursor.execute(sql)
				row = cursor.fetchone()
				video_abnormal_log = (row[0], int(row[1]), int(row[2]))
				# print(video_abnormal_log)

				# 行为异常是否有效
				# now = datetime.datetime.now()
				# video_valid = True
				# if (now - video_abnormal_log[0]).seconds >= 17:
				# 	video_valid = False

				#计算视频异常得分
				video_score = 0
				if video_abnormal_log[1] == 0:
					video_score = 0.1 + random.randint(0,15) / 100.0
				elif video_abnormal_log[1] == 1:
					video_score = 0.3 + random.randint(0,20) / 100.0
				elif video_abnormal_log[1] == 2:
					video_score = 0.8 + random.randint(-9,9) / 100.0
				elif video_abnormal_log[1] == 3:
					video_score = 0.95 + random.randint(-4,4) / 100.0

				'''
				# 取出轨迹异常评分
				sql = """
					SELECT create_at, longitude, latitude, height FROM device_gps 
					WHERE device_no IN (SELECT device_no FROM convoy JOIN device WHERE prisoner_id=%s) 
					ORDER BY create_at DESC LIMIT 1;
				""" % (prisoner)
				cursor.execute(sql)
				row = cursor.fetchone()
				print(row)

				# 轨迹是否有效
				# now = datetime.datetime.now()
				# gps_valid = True
				# if (now - row[0]).seconds >= 17:
				# 	gps_valid = False

				# 计算轨迹异常得分
				gps_score = normalization(deviation(0, 0, MAX_DISTANCE) / 10.0)

				# 异常事件记录
				abnormal_log = ''
				level = 0
				score = 0.3 * heartbeat_score + 0.2 * gps_score + 0.5 * video_score
				if heartbeat_scores[0] >= 0.8 or heartbeat_scores[1] >= 0.8:
					abnormal_log = '心率陡增'
					level = max(level, 1)
					score = 0.7 * heartbeat_score + 0.1 * gps_score + 0.2 * video_score
				if heartbeat_scores[2] >= 0.8:
					abnormal_log = '心率过高'
					level = max(level, 1)
					score = 0.7 * heartbeat_score + 0.1 * gps_score + 0.2 * video_score
				if gps_score >= 0.8:
					abnormal_log = '偏离预定轨迹'
					level = max(level, 1)
					score = 0.2 * heartbeat_score + 0.6 * gps_score + 0.3 * video_score
				if video_score >= 0.9:
					abnormal_log = '发生打架斗殴'
					level = max(level, 2)
					score = 0.03 * heartbeat_score + 0.02 * gps_score + 0.95 * video_score
				elif video_score >= 0.8:
					abnormal_log = '发生攻击行为'
					level = max(level, 2)
					score = 0.03 * heartbeat_score + 0.02 * gps_score + 0.95 * video_score
				print(heartbeat_score, gps_score, video_score, score, abnormal_log, level)
				'''

				# 异常事件记录
				abnormal_log = ''
				level = 0
				score = 0.3 * heartbeat_score + 0.7 * video_score
				if heartbeat_scores[0] >= 0.8 or heartbeat_scores[1] >= 0.8:
					abnormal_log = '心率陡增'
					level = max(level, 1)
					score = 0.9 * heartbeat_score + 0.1 * video_score
				if heartbeat_scores[2] >= 0.8:
					abnormal_log = '心率过高'
					level = max(level, 1)
					score = 0.9 * heartbeat_score + 0.1 * video_score
				if video_score >= 0.9:
					abnormal_log = '发生打架斗殴'
					level = max(level, 2)
					score = 0.05 * heartbeat_score + 0.95 * video_score
				elif video_score >= 0.7:
					abnormal_log = '发生攻击行为'
					level = max(level, 2)
					score = 0.05 * heartbeat_score + 0.95 * video_score
				print(datetime.datetime.now(), prisoner, heartbeat_score, video_score, score, abnormal_log, level)

				# 写入数据库
				sql = "insert into prisoner_risk(prisoner_id, risk_value) values ('%s', %s);" % (str(prisoner), str(int(score*100)))
				print(sql)
				cursor.execute(sql)

				if abnormal_log != '':
					sql = """select id from prisoner_risk WHERE prisoner_id='%s' 
						order by create_at desc LIMIT 1;
					""" % (prisoner)
					cursor.execute(sql)
					risk_id = cursor.fetchone()[0]
					
					sql = """insert into prisoner_anomaly(risk_id, level, deal_state, misdeclaration, description) 
					values (%d, %d, %d, %d, '%s');
					""" % (risk_id, level, 0, 0, abnormal_log)
					print(sql)
					cursor.execute(sql)
			except Exception as e:
				connect.rollback()  # 事务回滚
				print('计算风险失败', e)
			else:
				connect.commit()  # 事务提交
		# 关闭连接
		cursor.close()
		connect.close()
		time.sleep(SLEEP_TIME)

	

