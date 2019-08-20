package com.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.util
 * @ClassName: JsonUtil
 * @Description: 转换json字符串工具
 * @Author: LaoShiRen
 * @CreateDate: 2019-05-01 23:01
 * @Version: 1.0
 */
public class JsonUtil {

    /**
     * json转换Map
     * 第一个Map为消息状态信息
     *
     * @param jsonString jsonString
     * @return list
     */
    public static List<Map<String, Object>> jsonParseMap(String jsonString,String key) {
        List<Map<String, Object>> list = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("resultCode", jsonObject.getString("resultCode"));
        resultMap.put("resultInfo", jsonObject.getString("resultInfo"));
        list.add(resultMap);
        // 实际参数
        JSONObject data = jsonObject.getJSONObject("data");
        if (data != null) {
            List params = data.getObject(key, List.class);
            if (params != null) {
                list.addAll(params);
            }
        }
        return list;
    }

    public static List<Map<String, Object>> jsonParseMap(String jsonString,String key,Map map) {
        List<Map<String, Object>> list = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("resultInfo", jsonObject.getString("resultInfo"));
        resultMap.put("resultCode", jsonObject.getString("resultCode"));

        list.add(resultMap);
        // 实际参数
        JSONObject data = jsonObject.getJSONObject("data");
        if (data != null) {
            Map<String, Object> params =(Map) data.getObject(key, Map.class);
            list.add(params);
        }
        return list;
    }

    /**
     * 第一个map 放基本信息
     * 剩下的map 会存放到params中
     *
     * @param maps info
     * @return json
     */
    public static String mapsParseJson(List<Map<String, String>> maps) {
        Map<String, Object> map = new LinkedHashMap<>();
        // 基本信息
        Map<String, String> infoMap = maps.get(0);
        for (String key : infoMap.keySet()) {
            map.put(key, infoMap.get(key));
        }
        // 存放数组信息
        maps.remove(0);
        map.put("params", maps);
        return JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 创建webservices 入参
     *
     * @param map 对象
     * @return String
     */
    public static String mapToParamJson(LinkedHashMap<String, Object> map) {
        return JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }


}
