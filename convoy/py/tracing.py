# requirement 
# pip install rdp

"""
author: wanzicong
date: 10/26/2020

此脚本用于将一段连续的GPS轨迹转化为拟合的几段折线

在点击押解数据导入后，最后运行此脚本，生成route_brief表
一体化终端app打开时获取route_brief表中所有数据
计算得到GPS后，调用AMap.GeometryUtil.isPointOnSegment函数来判断该点是否在折线上

"""
import matplotlib.pyplot as plt
import numpy as np
import os
import rdp
import pymysql.cursors

# params used to simplify the rout-line
TOLERANCE = 0.002
MIN_ANGLE = np.pi * 0.003

def angle(dir):
    dir2 = dir[1:]
    dir1 = dir[:-1]
    return np.arccos((dir1*dir2).sum(axis=1)/(
        np.sqrt((dir1**2).sum(axis=1)*(dir2**2).sum(axis=1))))

if __name__ == '__main__':

    # 连接数据库
    connect = pymysql.Connect(
        host='127.0.0.1',
        port=3306,
        user='root',
        passwd='a128263',
        db='edge_computing_service',
        charset='utf8'
    )

    # 获取游标
    cursor = connect.cursor()
    sql = "SELECT longitude, latitude FROM route;"
    cursor.execute(sql)
    points = []
    for row in cursor.fetchall():
        points.append([np.float64(row[0]), np.float64(row[1])])
    points = np.array(points[:1149])

    # filename = os.path.expanduser('./tracing.data')
    # points = np.genfromtxt(filename,delimiter=',')
    x, y = points.T
    simplified = np.array(rdp.rdp(points.tolist(), TOLERANCE))
    sx, sy = simplified.T
    directions = np.diff(simplified, axis=0)
    theta = angle(directions)
    idx = np.where(theta>MIN_ANGLE)[0]+1

    # 可视化
    # fig = plt.figure(figsize=(10,8))
    # ax =fig.add_subplot(111)
    # ax.plot(x, y, 'b-', label='original path')
    # ax.plot(sx, sy, 'g--', label='simplified path')
    # ax.plot(sx[idx], sy[idx], 'ro', markersize = 10, label='turning points')
    # plt.legend(loc='best')
    # plt.show()

    # 转折点
    res = list(zip(list(sx[idx]),list(sy[idx])))

    # 保存数据库
    sql = " create TABLE 'route_brief' ('id' int(11) NOT NULL AUTO_INCREMENT, 'longitude' varchar(45) DEFAULT NULL, 'latitude' varchar(45) DEFAULT NULL, PRIMARY KEY ('id'))"
    try:
        sql = "DROP TABLE IF EXISTS route_brief;"
        cursor.execute(sql)
        sql = """CREATE TABLE route_brief (
                 id int(11) NOT NULL AUTO_INCREMENT,
                 longitude varchar(45) DEFAULT NULL,
                 latitude varchar(45) DEFAULT NULL, 
                 PRIMARY KEY (id))"""
        cursor.execute(sql)
        for longitude, latitude in res:
            sql = "insert into route_brief(longitude, latitude) values (%s, %s);" % (str(longitude), str(latitude))
            cursor.execute(sql)
    except Exception as e:
        connect.rollback()  # 事务回滚
        print('事务处理失败', e)
    else:
        connect.commit()  # 事务提交

    # 关闭连接
    cursor.close()
    connect.close()

    # with open('./turningPoints.data', 'w') as f:
    #     f.write(str(res))

