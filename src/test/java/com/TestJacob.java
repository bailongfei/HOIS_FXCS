package com;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class TestJacob {
    public static void main(String[] args) {
        // 创建与微软应用程序的新连接。传入的参数是注册表中注册的程序的名称。
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");

        try {

// 音量 0-100

            sap.setProperty("Volume", new Variant(100));

// 语音朗读速度 -10 到 +10

            sap.setProperty("Rate", new Variant(-4));

// 获取执行对象

            Dispatch sapo = sap.getObject();

// 执行朗读

            Dispatch.call(sapo, "Speak", new Variant("请,A10012,到四号窗口就诊."));

// 关闭执行对象

            sapo.safeRelease();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

// 关闭应用程序连接

            sap.safeRelease();

        }

    }

}
