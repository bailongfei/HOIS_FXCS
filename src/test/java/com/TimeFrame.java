package com;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeFrame  extends JFrame {
    /*
     * Variables
     */
    private JPanel timePanel;
    private JLabel timeLabel;
    private JLabel displayArea;
    private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private String time;
    private int ONE_SECOND = 1000;

    public TimeFrame() {
        timePanel = new JPanel();
        timeLabel = new JLabel("CurrentTime: ");
        displayArea = new JLabel();

        configTimeArea();

        timePanel.add(timeLabel);
        timePanel.add(displayArea);
        this.add(timePanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(200, 80));
        this.setLocationRelativeTo(null);
    }

    /**
     * 这个方法创建 a timer task 每秒更新一次 the time
     */
    private void configTimeArea() {
        Timer tmr = new Timer();
        tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), ONE_SECOND);
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
            displayArea.setText(time);
        }
    }

    public static void main(String arg[]) {
        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setVisible(true);
    }
}
