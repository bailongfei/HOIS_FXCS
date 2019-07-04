package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TimerTasks {
 public static void main(String[] args) {
     new TimerTasks().configTimeArea();
   }
    private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private String time=null;
    private int ONE_SECOND = 1000;
    /**
     * 这个方法创建 a timer task 每秒更新一次 the time
     */
    public String configTimeArea() {
        Timer tmr = new Timer();
        tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), ONE_SECOND);

        return time;
    }

    /**
     * Timer task 更新时间显示区
     *
     */
    protected class JLabelTimerTask extends TimerTask {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                DEFAULT_TIME_FORMAT);

        @Override
        public void run() {
            time = dateFormatter.format(Calendar.getInstance().getTime());

            System.out.println(time);
        }
    }
}
