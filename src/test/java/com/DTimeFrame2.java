package com;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DTimeFrame2 implements Runnable{
    private String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    private int ONE_SECOND = 1000;
    @Override
    public void run() {
        while(true)
        {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
            System.out.println(dateFormatter.format(
                    Calendar.getInstance().getTime()));
            try
            {
                Thread.sleep(ONE_SECOND);
            }
            catch(Exception e)
            {
                /*displayArea.setText("Error!!!");*/
            }
        }
    }
    public static void main(String arg[])
    {
        DTimeFrame2 df2=new DTimeFrame2();
        Thread thread1=new Thread(df2);
        thread1.start();
    }

}
