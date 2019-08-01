//package com.aaa.house.utils;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.util.EntityUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Classname：ID
// * @author: L_Fly
// * @Date: 2019/7/25  Time：16:20
// * @Version 1.0.0
// */
//
//public class ID {
//    public static void main(String[] args) {
//        String host = "https://eid.shumaidata.com";
//        String path = "/eid/check";
//        String method = "POST";
//        String appcode = "403b99930ef14d5e9b35474b9da45eac";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        System.out.println(headers);
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("idcard", "411403199810168112");
//        querys.put("name", "李小飞");
//        Map<String, String> bodys = new HashMap<String, String>();
//
//
//        try {
//            /**
//             * 重要提示如下:
//             * HttpUtils请从
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//             * 下载
//             *
//             * 相应的依赖请参照
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//             */
//            HttpResponse response = IDCheckUtil.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
//            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
////{
////        "code": "0", //返回码
////        "message": "成功", //返回码说明
////        "result": {
////        "name": "冯**", //姓名
////        "idcard": "35030119******9422", //身份证号
////        "res": "1", //核验结果状态码，1 一致；2 不一致；3 库无
////        "description": "一致"//核验结果状态描述
////        }
////}