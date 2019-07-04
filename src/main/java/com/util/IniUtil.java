package com.util;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IniUtil {

    public static Map<String,String> getIPConfig(){
        Map<String, String> map;
        try {
            File file = new File("IPConfig.ini");
            if (!file.exists() && !file.isDirectory()) {
                return null;
            } else {
                map = new HashMap<>();
                Ini.Section section = new Ini(file).get("section");

                if (section!=null){
                    String ipAddress = section.get("ipAddress");
                    map.put("ipAddress", ipAddress);

                } else {
                    return null;
                }
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(getIPConfig());
        System.out.println(getIPConfig().get("loginName")+":"+getIPConfig().get("ipAddress"));
    }

}
