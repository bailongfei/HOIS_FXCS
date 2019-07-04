package com.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @Package: com.util
 * @ClassName: IPUtils
 * @Description: TODO
 * @Author: LaoShiRen
 * @CreateDate: 2019-06-06 14:36
 * @Version: 1.0
 */
public class IPUtils {

    /**
     * 获取本机的Mac 地址
     *
     * @return mac
     * @throws SocketException ex
     */
    public static String getMACAddress() {
        byte[] mac = new byte[0];
        try {
            mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        //把mac地址拼装成String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append(":");
            }
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        //大写
        return sb.toString().toUpperCase();
    }

    public static  String getIPAddress(){
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

}
