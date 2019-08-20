package com.API;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.util.IniUtil;
import okhttp3.*;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebServices {
    /*登录*/
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
               .url("http://"+ IniUtil.getConfig().get("ipAddress")+":8080/services/USService/soap")
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

    /*工作站*/
    public static List<Map<String, Object>> toGzz(){
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
                .url("http://"+IniUtil.getConfig().get("ipAddress")+":8080/services/USService/soap")
                .post(body)
                .addHeader("Content-Type", "application/xml")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = null;
        try {
            s = new String(response.body().bytes(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream   is   =   new ByteArrayInputStream(s.getBytes());
        SAXReader reader = new SAXReader();
        Document document = null;// 生成XML文档
        try {
            document = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        /*解析数组*/
        String map=document.getRootElement().elements().get(0).elements().get(0).elements().get(0).getText();
        List list = JSONObject.parseObject(map, List.class);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Object o : list) {
            Map<String,Object> item = (Map) o;
            //System.out.println(item);
            mapList.add(item);
        }
        return mapList;
    }
  /*呼叫下一位*/
  public static String toCallNormal(Integer WS_ID,String Pre_DataID) throws IOException {
      OkHttpClient client = new OkHttpClient();
      MediaType mediaType = MediaType.parse("application/xml");
      RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
              "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
              "    <soap:Body>\n        <ns:callNormal xmlns:ns= \"http://USService.xh.com\">" +
              "        \t<WS_ID>"+WS_ID+"</WS_ID>" +
              "        \t<Queue_No>"+Pre_DataID+"</Queue_No>" +
              "        </ns:callNormal>" +
              "   </soap:Body>" +
              "</soap:Envelope>");
      Request request = new Request.Builder()
              .url("http://"+IniUtil.getConfig().get("ipAddress")+":8080/services/USService/soap")
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
      /*解析数组*/
      /*String map=document.getRootElement().elements().get(0).elements().get(0).elements().get(0).getText();
      List list = JSONObject.parseObject(map, List.class);
      List<Map<String,Object>> mapList = new ArrayList<>();
      for (Object o : list) {
          Map<String,Object> item = (Map) o;
          //System.out.println(item);
          mapList.add(item);
      }*/
        return document.getRootElement().elements().get(0).elements().get(0).elements().get(0).getText();
  }
  /*重呼*/
   public  static String callRepeat(String WSID,String QueueId) throws IOException {
       OkHttpClient client = new OkHttpClient();

       MediaType mediaType = MediaType.parse("application/xml");
       RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
               "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
               "   <soap:Body>" +
                       " <ns:callRepeat xmlns:ns= \"http://USService.xh.com\">" +
                       " \t<WS_ID>"+WSID+"</WS_ID>" +
                       " \t<Queue_No>"+QueueId+"</Queue_No>" +
                       "</ns:callRepeat>" +
               "   </soap:Body>" +
               "</soap:Envelope>");
       Request request = new Request.Builder()
               .url("http://"+IniUtil.getConfig().get("ipAddress")+":8080/services/USService/soap")
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

/*过号*/
public  static String callArrive(String WSID,String QueueID) throws IOException {
    OkHttpClient client = new OkHttpClient();

    MediaType mediaType = MediaType.parse("application/xml");
    RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soap:Body>" +
                        "<ns:callArrive xmlns:ns= \"http://USService.xh.com\">" +
                        "\t<WS_ID>"+WSID+"</WS_ID>" +
                        "\t<Queue_No>"+QueueID+"</Queue_No>" +
                        "</ns:callArrive>" +
                "</soap:Body>" +
            "</soap:Envelope>");
    Request request = new Request.Builder()
            .url("http://"+IniUtil.getConfig().get("ipAddress")+":8080/services/USService/soap")
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
/*结束存储过程*/
    public static String closeWord(String WSID,String QueueId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/xml");
        RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "    <soap:Body>" +
                "        <ns:closeWork xmlns:ns= \"http://USService.xh.com\">" +
                "       \t<WS_ID>"+WSID+"</WS_ID>" +
                "        \t<Queue_NO>"+QueueId+"</Queue_NO>" +
                "        </ns:closeWork>" +
                "    </soap:Body>" +
                "</soap:Envelope>");
        Request request = new Request.Builder()
                .url("http://"+IniUtil.getConfig().get("ipAddress")+":8080/services/USService/soap")
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
/*确认到达*/
    public static String callConfirm(String WSID,String QueueID) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/xml");
        RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "   <soap:Body>" +
                "       <ns:callConfirm xmlns:ns= \"http://USService.xh.com\">" +
                "        \t<WS_ID>"+WSID+"</WS_ID>" +
                "        \t<Queue_No>"+QueueID+"</Queue_No>" +
                "       </ns:callConfirm>" +
                "    </soap:Body>" +
                "</soap:Envelope>");
        Request request = new Request.Builder()
                .url("http://"+IniUtil.getConfig().get("ipAddress")+":8080/services/USService/soap")
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
    /*延后*/
    public static String callDelay(String WSID,String QueueId,String delaycount) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/xml");
        RequestBody body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "   <soap:Body>" +
                "        <ns:callDelay xmlns:ns= \"http://USService.xh.com\">" +
                "        \t<WS_ID>"+WSID+"</WS_ID>" +
                "       \t<Queue_No>"+QueueId+"</Queue_No>" +
                "        \t<Delay_Count>"+delaycount+"</Delay_Count>" +
                "        </ns:callDelay>" +
                "    </soap:Body>" +
                "</soap:Envelope>");
        Request request = new Request.Builder()
                .url("http://"+IniUtil.getConfig().get("ipAddress")+":8080/services/USService/soap")
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
    public static void main(String[] args) throws IOException {
       //数组
       /* List<Map<String, Object>> map=toGzz();
        System.out.println(map+"参数");
        for (int i = 0; i < map.size(); i++) {
            System.out.println(map.get(i).get("Priority_Level"));
        }*/

       /*
        //解析数组
       List list = JSONObject.parseObject(map, List.class);
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Object o : list) {
            Map<String,Object> item = (Map) o;
            System.out.println(item);
            mapList.add(item);
        }*/
       /*解析Map*/
       /* String map = toCallNormal(2,"1");
        JSONObject object= JSON.parseObject(map);

        Object Priority_ID = object.get("Priority_ID");
        Object Queue_No = object.get("Queue_No");*/
        String maps=callDelay("2","1002","2");
        System.out.println("call===="+maps);
        JSONObject object= JSON.parseObject(maps);
        Object result = object.get("result");
        System.out.println(result);
    }
}
