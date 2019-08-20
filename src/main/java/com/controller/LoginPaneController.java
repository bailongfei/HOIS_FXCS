package com.controller;

import com.API.WebServices;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entity.BasicMainDisplay;
import com.entity.BasicSrvGroup;
import com.entity.BasicStaffInfo;
import com.entity.BasicWorkStation;
import com.node.Alert;
import com.util.IniUtil;
import com.util.PageUtil;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class LoginPaneController {
    public static Stage stage;
    private static BasicWorkStation workStation=null;//工作站窗口
    private static BasicStaffInfo StaffInfo=null;//员工
    private static List<BasicMainDisplay> displayInfos = null;//显示设备信息
    //账号框
    @FXML   //私有属性加@FXML
    private TextField staffIdTextField;
    //密码框
    @FXML
    private PasswordField staffPasswordField;
    //下拉框 项目
    public ComboBox<BasicSrvGroup> itemComboBox;
    //点击登录
    public AnchorPane loginButtonPane;
    @FXML
    private Label timeLabel;

    //private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
    private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private String time;
    private int ONE_SECOND = 1000;
    @FXML
    public void initialize(){
        System.out.println("javafx初始化");
        bindMouseEvent();
        bindKeyEvent();
        showTime();
        //timeLabel.setText(sdf.format(new Date()));

        /*configTimeArea();*/
        /*LoginPaneController df2=new LoginPaneController();
        Thread thread1=new Thread(df2);
        thread1.start();*/
    }
/*    @Override
    public void run() {
        while(true)
        {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
            System.out.println(dateFormatter.format(
                    Calendar.getInstance().getTime()));
            timeLabel.setText(dateFormatter.format(Calendar.getInstance().getTime()));
            try
            {
                Thread.sleep(ONE_SECOND);
            }
            catch(Exception e)
            {
                timeLabel.setText("");

            }
        }
    }*/
    /*
    绑定鼠标事件
    * */
    public void bindMouseEvent() {
        /*loginButtonPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("事假处理函数...new内部类");
            }
        });*/
        System.out.println("绑定鼠标事件");
        loginButtonPane.setOnMouseClicked(event -> {
            if (MouseButton.PRIMARY.equals(event.getButton())) {
                try {
                    onLogin(staffIdTextField.getText(), staffPasswordField.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(staffIdTextField.getText()+":"+staffPasswordField.getText());
            }
        });
    }
    /*
    * 绑定键盘事件
    * */
       public void bindKeyEvent(){
           //账号框
           staffIdTextField.setOnKeyPressed(event -> {
              switch (event.getCode()){
                  case ENTER: // 回车键
                      //getStaffInfo(staffIdTextField.getText());
                      break;
                  case ESCAPE://esc键清空
                      staffIdTextField.setText("");
                      break;
              }
           });

           staffIdTextField.focusedProperty().addListener(event->{
               if (!staffIdTextField.focusedProperty().get()) {
                   //getStaffInfo(staffIdTextField.getText());
               }
           });

           staffPasswordField.setOnKeyPressed(event -> {
               switch (event.getCode()) {
                   case ESCAPE:
                       staffPasswordField.setText("");
                       break;
                   case ENTER:
                       try {
                           onLogin(staffIdTextField.getText(),
                                   staffPasswordField.getText());
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       break;
               }
           });

       }

    /*
     验证用户密码是否为空
    * */
    private void onLogin(String staffId, String staffPass) throws IOException {
        BasicSrvGroup basicSrvGroup = (BasicSrvGroup) itemComboBox.getSelectionModel().getSelectedItem();
        if("".equals(staffId)){
         //提示没有账号
            Alert.notifyWarning("提示", "没有输入账号", stage);
        }else if("".equals(staffPass)){
            //提示
            Alert.notifyWarning("提示", "没有输入密码", stage);

        }/*else if (basicSrvGroup==null){
            Alert.notifyWarning("提示", "没有选择项目", stage);
        }*/else{
            //获取工作站信息
            getWorkStationInfo();
            //执行登录方法
           /* Login(workStation,staffId,staffPass,basicSrvGroup.getSrvGroupId());*/
            Login(workStation,staffId,staffPass,1);
        }
    }
    /*
    * 执行登录方法
    *
    * */
    private  void Login(BasicWorkStation workStationInfo, String loginName, String passwork, Integer basicSrvGroup) throws IOException {
        String s = WebServices.LoginSerices(Integer.valueOf(IniUtil.getConfig().get("WSID")), loginName, passwork);
        JSONObject object= JSON.parseObject(s);
        Object result = object.get("result");
       /* ProgressForm progressForm = ProgressForm.getInstance(stage);
        progressForm.activateProgressBar();*/
        if(result.equals(1)){
            //提示
            String resultInfo = (String) object.get("resultInfo");
            Alert.notifyWarning("提示",resultInfo, stage);
               PageUtil.toWorkStation(stage,displayInfos,StaffInfo,
                       itemComboBox.getSelectionModel().getSelectedItem());

           }else if (result.equals(2)){
            //提示
            String resultInfo = (String) object.get("resultInfo");
            Alert.notifyWarning("提示",resultInfo, stage);
           }else {
            Alert.notifyWarning("提示","连接超时!", stage);
        }
    }
    /*
    * //获取工作站信息
    * */
    private void getWorkStationInfo() {

    }

    /*时间显示*/
    private void showTime(){
        //label2
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(JavaFxScheduler.platform())
                .subscribe(it->{
                    Platform.runLater(()->{
                        timeLabel.setText(sdf.format(new Date()));
                    });
                });
    }
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
