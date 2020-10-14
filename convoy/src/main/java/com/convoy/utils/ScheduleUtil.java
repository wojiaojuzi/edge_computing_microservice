package com.convoy.utils;

//import com.ecs.mapper.EscapeGpsMapper;
/**
 * Created by Zhaoone on 2019/12/20
 **/
/*@Configuration
@EnableScheduling
public class ScheduleUtil {

    private final CloudService cloudService;
    //private final EscapeGpsMapper escapeGpsMapper;
    //private final RiskLevelRecordingMapper riskLevelRecordingMapper;

    @Autowired
    public ScheduleUtil(CloudService cloudService) {
        this.cloudService = cloudService;
    }

    /*
     * 定时调取云端关于犯人逃逸的数据，并解析，如果逃逸，写入数据库
     */
    /*
     *  逃逸数据（已有逃逸）处理逻辑：
     *  0、第一次出现逃逸，异常插入，有犯人进行逃逸
     *     第一次出现失联，异常插入，有犯人进行逃逸失联
     *     每次失联人数增加，系统添加人数
     *     此外无异常报警
     *  1、判断逃逸人数和GPS数是否一致
     *     如果一致，则全存数据库
     *     如果不一致，存数据库同时进行异常区分
     *     如果逃逸且无GPS信息，异常
     *  2、数据库保存逻辑(如何存储数据并进行读取）：
     *
     *  3、异常读取逻辑：
     *
     */
//    @Scheduled(fixedDelay = 5000)
//    private void escape() {
//
//        JSONObject msg = cloudService.escapeGPS();
//        System.err.println(LocalDateTime.now() + ":" + msg.toString());
//
//        if (msg.getString("status").equals("Yes | No")) {
//            Timestamp uploadTime = new Timestamp(new Date().getTime());
//            /*判断之前是否有犯人逃逸*/
//            List<Object> escapeGpss = escapeGpsMapper.isExisted();
//            if (escapeGpss.size() == 0) {
//                riskLevelRecordingMapper.createRecord("", uploadTime,
//                        true, "有犯人逃逸");
//                System.out.println("first one");
//            }
//            int escapeNum = msg.getInteger("escapeNum");
//            JSONArray positions = msg.getJSONArray("positions");
//            int gpsNum = positions.size();
//            System.err.println("GpsNum:" + gpsNum);
//            System.err.println(positions.size());
//
//            /*
//             * 全部GPS丢失
//             */
//            if (gpsNum == 0) {
//                riskLevelRecordingMapper.createRecord("", uploadTime,
//                        true, "全部逃逸犯人GPS丢失");
//            }
//            /*
//             * 部分丢失
//             */
//            else if (gpsNum < escapeNum) {
//                riskLevelRecordingMapper.createRecord("", uploadTime,
//                        true, "部分逃逸犯人GPS丢失");
//                for (int i = 0; i < gpsNum; i++) {
//                    String po = positions.get(i).toString();
//                    String latitude = po.split(",")[0].split(":")[1];
//                    String longitude = po.split(",")[1].split(":")[1].split("}")[0];
//                    escapeGpsMapper.createEscapeGps(String.valueOf(escapeNum), uploadTime, longitude, latitude);
//                }
//            }
//            /*
//             * 全部Gps存在
//             */
//            else {
//                for (int i = 0; i < gpsNum; i++) {
//                    String po = positions.get(i).toString();
//                    String latitude = po.split(",")[0].split(":")[1];
//                    String longitude = po.split(",")[1].split(":")[1].split("}")[0];
//                    escapeGpsMapper.createEscapeGps(String.valueOf(escapeNum), uploadTime, longitude, latitude);
//                }
//            }
//        }
//    }
//}
