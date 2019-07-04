package com.API;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.IniUtil;
import okhttp3.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebServices {
   public static String LoginSerices(Integer WSID,String userName,String password) throws IOException {

       OkHttpClient client = new OkHttpClient();

       MediaType mediaType = MediaType.parse("text/plain");
       RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
               "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+"" +
                   "<soap:Body>"+
                       "<ns:userLogin xmlns:ns= \"http://USService.xh.com\"> \t"+
                       "<WS_ID>"+WSID+"</WS_ID>"+
                       "<Staff_Code >"+userName+"</Staff_Code>"+
                       "<Staff_Pass>"+password+"</Staff_Pass>"+
                       "</ns:userLogin>"+
                   "</soap:Body>"+
               "</soap:Envelope>");
       Request request = new Request.Builder()
               .url("http://"+ IniUtil.getIPConfig().get("ipAddress")+":8080/services/USService/soap")
               .post(body)
               .addHeader("Content-Type", "application/xml")
               .build();

       Response response = client.newCall(request).execute();
      String s = new String(response.body().bytes(), "utf-8");
       InputStream   is   =   new ByteArrayInputStream(s.getBytes());
       SAXReader reader = new SAXReader();
       Document document = null;// 生成XML文档
       try {
           document = reader.read(is);
       } catch (DocumentException e) {
           e.printStackTrace();
       }
       return document.getRootElement().elements().get(0).elements().get(0).elements().get(0).getText();
       /*return new String(response.body().bytes(), "utf-8");*/
   }

    public static void main(String[] args) throws IOException {
       /* String map=LoginSerices(33, "3333", "3333");
        System.out.println(map);
        JSONObject object = JSON.parseObject(map);
        System.out.println(object.get("result")+":"+object.get("resultInfo")+"----");*/

       String map=toGzz();
        System.out.println(map+"参数");
        /*[{"queue_No":"1004","regDate":"2019-07-04 11:11:26.723","srvGroup_Name":"电子监控违法处理","statusTypeName":"呼叫中"}]*/
        JSONObject jsonObject = JSON.parseObject(map);
        //注意：family中的内容带有中括号[]，所以要转化为JSONArray类型的对象
        JSONArray family = jsonObject.getJSONArray("");
        for (int i = 0; i < family.size(); i++) { //提取出family中的所有
            String s1 = (String) family.get(i);
            System.out.println("currentFamily:" + s1);

        }
        /*
        System.out.println(object.get("queue_No")+":"+object.get("srvGroup_Name")+"----");*/

    }
    public static String toGzz() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/xml");
        RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                    " <soap:Body>" +
                        " <ns:listAll xmlns:ns= \"http://USService.xh.com\">" +
                        " \t </ns:listAll>" +
                    "</soap:Body>" +
                "</soap:Envelope>");
        Request request = new Request.Builder()
                .url("http://192.168.0.4:8080/services/USService/soap")
                .post(body)
                .addHeader("Content-Type", "application/xml")
                .build();
        Response response = client.newCall(request).execute();
        String s = new String(response.body().bytes(), "utf-8");
        InputStream   is   =   new ByteArrayInputStream(s.getBytes());
        SAXReader reader = new SAXReader();
        Document document = null;// 生成XML文档
        try {
            document = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document.getRootElement().elements().get(0).elements().get(0).elements().get(0).getText();
    }

}
