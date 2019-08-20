package com.controller;

import com.API.LSDisplaySocketClient;
import com.API.SocketService;
import com.API.WebServices;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.entity.*;
import com.node.Alert;
import com.util.ConstructorBuilder;
import com.util.IniUtil;
import com.util.JacobUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class WorkStationController {
    public static Stage stage;
    private BasicWorkStation workStation = null;//工作站窗口
    public static BasicStaffInfo StaffInfo = null;//员工
    public static List<BasicMainDisplay> displayInfos = null;//显示设备信息
    public static BasicSrvGroup basicSrvGroup;//项目
    private Timer queueTimer = new Timer();
    private static CallInfo callInfo=new CallInfo();

    @FXML
    private Label infoLabel;
    @FXML
    private Label WSIDWord;
    @FXML
    private Label currentQueueNoLabel;
    @FXML
    private Label currentNameLabel;
    @FXML
    private Button callNextButton;
    @FXML
    private Button callAppointedButton;
    @FXML
    private Button confirmArrivedButton;
    @FXML
    private Button hangUpButton;
    @FXML
    private Button delayButton;
    @FXML
    private Button checkFinishButton;
    @FXML
    private TableView<QueueItem> queueTable;
    @FXML
    private Label queueNumberLabel;
    @FXML
    private TableColumn<QueueItem, Label> queueNoColumn;
    @FXML
    private TableColumn<QueueItem, Label> queueNameColumn;
    @FXML
    private TableColumn<QueueItem, Label> queueTimeColumn;
    @FXML
    private TableColumn<QueueItem, Label> queueStartColumn;
    @FXML
    private TableView<QueueItem> calledQueueTable;
    @FXML
    private Label calledQueueNumberLabel;
    @FXML
    private TableColumn<QueueItem, Label> calledQueueNoColumn;
    @FXML
    private TableColumn<QueueItem, Label> calledQueueNameColumn;
    @FXML
    private TableColumn<QueueItem, Label> calledTimeColumn;
    @FXML
    private TableColumn<QueueItem, Label> calledStartColumn;
    @FXML
    private TableView<QueueItem> yhjTable;
    @FXML
    private Label yhjLabel;
    @FXML
    private TableColumn<QueueItem, Label> yhjNoColumn;
    @FXML
    private TableColumn<QueueItem, Label> yhjQueueNameColumn;
    @FXML
    private TableColumn<QueueItem, Label> yhjTimeColumn;
    @FXML
    private TableColumn<QueueItem, Label> yhjStartColumn;
    @FXML
    private void initialize() {
        init();
        bindTableColumn();
        bindMouseEvent();
        handleAutoUpdateQueue();
        WSIDWord.setText(IniUtil.getConfig().get("WSID")+"号诊室");
    }


    /**
     * 初始化
     */
    private void init() {
        getWorkStationInfo();
        // ip
        /*String info = IPUtils.getIPAddress() + "   " +
                IPUtils.getMACAddress() + "   " +
                workStation.getWsName() + "  " +
                StaffInfo.getStaffLoginName() + "  ";

        infoLabel.setText(info);*/
    }

    /**
     * 绑定tableView
     */
    private void bindTableColumn() {

        queueNoColumn.setCellValueFactory(cell -> {
            Label label = new Label("A"+cell.getValue().getQueueNo());
            return new SimpleObjectProperty<>(label);
        });
        queueNameColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getSrvGroupName());
            return new SimpleObjectProperty<>(label);
        });
        queueTimeColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getRegDate());
            return new SimpleObjectProperty<>(label);
        });
        queueStartColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getStatusTypeName());
            return new SimpleObjectProperty<>(label);
        });


        calledQueueNoColumn.setCellValueFactory(cell -> {
            Label label = new Label("A"+cell.getValue().getQueueNo());
            return new SimpleObjectProperty<>(label);
        });
        calledQueueNameColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getSrvGroupName());
            return new SimpleObjectProperty<>(label);
        });
        calledTimeColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getRegDate());
            return new SimpleObjectProperty<>(label);
        });
        calledStartColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getStatusTypeName());
            return new SimpleObjectProperty<>(label);
        });


        yhjNoColumn.setCellValueFactory(cell -> {
            Label label = new Label("A"+cell.getValue().getQueueNo());
            return new SimpleObjectProperty<>(label);
        });
        yhjQueueNameColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getSrvGroupName());
            return new SimpleObjectProperty<>(label);
        });
        yhjTimeColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getRegDate());
            return new SimpleObjectProperty<>(label);
        });
        yhjStartColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getStatusTypeName());
            return new SimpleObjectProperty<>(label);
        });
    }
    /**
     * 定时更新队列列表
     * 延迟30秒，每30秒更新
     */
    private void handleAutoUpdateQueue() {
        queueTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> updateQueue());
            }
        }, 1, 1000 * 30);
    }
    /**
     * 更新当前病人的信息
     *
     * @param queueNo 排队号
     * @param name    名字
     */
    private void updateCurrentCustomer(String queueNo, String name) {
        Platform.runLater(() -> {
            currentQueueNoLabel.setText(queueNo);
            currentNameLabel.setText(name);
        });
    }
/*更新工作站*/
    private void updateQueue() {
        /*获取接口数据*/
        List<Map<String, Object>> maps = WebServices.toGzz();

        ObservableList<QueueItem> queueItems = FXCollections.observableArrayList();
        /*把数据添加到FX容器中*/
        queueItems.addAll(
                        maps.stream().map(it ->
                                new ConstructorBuilder<QueueItem, String>().builderObject(new QueueItem(), it))
                                .collect(Collectors.toList()));
        ObservableList<QueueItem> queueItem = FXCollections.observableArrayList();
        ObservableList<QueueItem> queueItem2 = FXCollections.observableArrayList();
        ObservableList<QueueItem> queueItem3 = FXCollections.observableArrayList();
        for (int i = 0; i < queueItems.size(); i++) {
            String statusTypeName = queueItems.get(i).getStatusTypeName();
            //System.out.println("状态:"+statusTypeName);
            if(statusTypeName.equals("排队中")){
                queueItem.addAll(queueItems.get(i));

                queueTable.setItems(queueItem);
            }else if(statusTypeName.equals("过号")){
                queueItem2.addAll(queueItems.get(i));
                calledQueueTable.setItems(queueItem2);
            }else if (statusTypeName.equals("结束")){
                queueItem3.addAll(queueItems.get(i));
                yhjTable.setItems(queueItem3);
            }
        }
        queueNumberLabel.setText(queueTable.getItems().size() + "");
        calledQueueNumberLabel.setText(calledQueueTable.getItems().size()+"");
        yhjLabel.setText(yhjTable.getItems().size()+"");

    }
    /**
     * 设置tableView item
     */
   /* private void setTableViewItem(List<Map<String, Object>> mapList) {

        // 待呼叫列表
        queueTable.setItems(FXCollections.observableArrayList(
                mapList.stream()
                        .map(QueueItem::new)
                        .sorted()
                        .collect(Collectors.toList())));
        // 已呼叫列表
        calledQueueTable.setItems(FXCollections.observableArrayList(
                mapList.stream()
                        .map(QueueItem::new)
                        .sorted()
                        .collect(Collectors.toList())));

        queueNumberLabel.setText(queueTable.getItems().size() + "");
        calledQueueNumberLabel.setText(calledQueueTable.getItems().size() + "");
    }*/
    private void bindMouseEvent() {
        /*指定呼叫*/
        queueTable.setOnMouseClicked(e->{
            QueueItem selectedItem = queueTable.getSelectionModel().getSelectedItem();
            if (MouseButton.SECONDARY.equals(e.getButton())){
               /* boolean display = AlertWindow.display("新窗口", "发送的消息");
                if (display){
                    stage.close();
                }*/
                AppointController.stage=stage;
                AppointController.Id=selectedItem.getQueueNo();
                AppointController.Name=selectedItem.getSrvGroupName();
                AppointController.Dates=selectedItem.getRegDate();
                AppointController.Start=selectedItem.getStatusTypeName();
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(WorkStationController.class.getClass().getResource("/fxml/AppointPane.fxml"));
                try {
                    AnchorPane anchorPane=fxmlLoader.load();
                    Scene scene=new Scene(anchorPane);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.getIcons().add(new Image(WorkStationController.class.getResourceAsStream("/static/xhIcon.png")));
                    stage.setTitle("指定呼叫");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setAlwaysOnTop(true);
                    stage.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println(selectedItem.getQueueNo());
                System.out.println(selectedItem.getSrvGroupName());
                System.out.println(selectedItem.getStatusTypeName());
                System.out.println(selectedItem.getRegDate());
            }
        });
         // 呼叫下一位
        callNextButton.setOnMouseClicked(event -> {
            if (MouseButton.PRIMARY.equals(event.getButton())) {
                QueueItem queueInfo = queueTable.getSelectionModel().getTableView().getItems().get(0);
                QueueItem queueInfo2 = queueTable.getSelectionModel().getTableView().getItems().get(1);
                updateCurrentCustomer("A"+queueInfo.getQueueNo(),"A"+queueInfo2.getQueueNo());
                /*currentQueueNoLabel.setText("A"+queueInfo.getQueueNo());
                currentNameLabel.setText("A"+queueInfo2.getQueueNo());*/
                callAppointed(queueInfo);
                DisplayInfo displayInfo=new DisplayInfo();
                List list=new ArrayList();
                int p=5;
                for (int i = 0; i < (p<queueTable.getItems().size()?p:queueTable.getItems().size()); i++) {
                    QueueItem queueItem = queueTable.getSelectionModel().getTableView().getItems().get(i);
                    list.add(queueItem.getQueueNo());
                    displayInfo.setDisplayName(queueItem.getQueueNo());
                }
                try {
                    /*发送Socket*/
                    SocketService socketService = new SocketService(displayInfo);
                   /* socketService.sendJson(JsonUtil.mapToParamJson(map));*/
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("显示数据:"+list);
                try {
                    LSDisplaySocketClient.SocketClient(displayInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setButtonDisableCallNext(callNextButton);
            }
        });



        // 重新呼叫
        callAppointedButton.setOnMouseClicked(event -> {
            //CallInfo callInfo=new CallInfo();
            if (currentQueueNoLabel.getText()!=null&&currentQueueNoLabel.getText()!=""){
            callInfo.setQueueID(currentQueueNoLabel.getText().substring(1));
                if (MouseButton.PRIMARY.equals(event.getButton())) {
                    if (callInfo == null) {
                        return;
                    }
                    QueueItem q = new QueueItem();
                    q.setQueueNo(callInfo.getQueueID());
                    System.out.println("重新呼叫:"+q.getQueueNo());
                    callRepeat(q);
                    setButtonDisable(callAppointedButton);
                }
            }
                return;

        });

        // 过号挂起
        hangUpButton.setOnMouseClicked(event -> {
            if (MouseButton.PRIMARY.equals(event.getButton())) {
                hangUp();
                setButtonDisable(hangUpButton);
            }
        });

        // 检查结束
        checkFinishButton.setOnMouseClicked(event -> {
            if (MouseButton.PRIMARY.equals(event.getButton())) {
                checkFinish();
                setButtonDisable(checkFinishButton);
            }
        });


        // 确认到达
        confirmArrivedButton.setOnMouseClicked(event -> {
            if (MouseButton.PRIMARY.equals(event.getButton())) {
                confirmArrival();
                setButtonDisable(confirmArrivedButton);
            }
        });

// 延后
        delayButton.setOnMouseClicked(event -> {
            if (MouseButton.PRIMARY.equals(event.getButton())) {
                delay();

                setButtonDisable(delayButton);
            }
        });
/*
        *







        // 指定呼叫
        queueTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && MouseButton.PRIMARY.equals(event.getButton())) {
                QueueInfo selectedItem = queueTable.getSelectionModel().getSelectedItem();
                callAppointed(selectedItem);
                updateQueue();
            }
        });

        // 关闭工作站
        stage.setOnCloseRequest(event -> closeRequest());*/
    }

    private void delay() {
        if (currentQueueNoLabel.getText()!=null&&currentQueueNoLabel.getText()!=""){
            callInfo.setQueueID(currentQueueNoLabel.getText().substring(1));
            try {
                String mapdelay = WebServices.callDelay(IniUtil.getConfig().get("WSID"), callInfo.getQueueID(),IniUtil.getConfig().get("Queue_Pos"));
                JSONObject object= JSON.parseObject(mapdelay);
                Integer result = (Integer) object.get("result");
                if (result==1){
                    Alert.notifyWarning("提示","成功!", stage);
                    updateCurrentCustomer("", "");
                    getButtonDisableCallNext(callNextButton);
                    updateQueue();
                } else {
                    Alert.notifyWarning("提示","失败!", stage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            Alert.notifyWarning("提示","暂无叫人!", stage);
        }
    }

    private void confirmArrival() {
        if (currentQueueNoLabel.getText()!=null&&currentQueueNoLabel.getText()!=""){
            callInfo.setQueueID(currentQueueNoLabel.getText().substring(1));
            try {
                String mapss = WebServices.callConfirm(IniUtil.getConfig().get("WSID"), callInfo.getQueueID());
                JSONObject object= JSON.parseObject(mapss);
                Integer result = (Integer) object.get("result");
                System.out.println("结束返回参数："+result);
                if (result==1){
                    Alert.notifyWarning("提示","成功!", stage);
                } else {
                    String resultInfo = (String) object.get("resultInfo");
                    Alert.notifyWarning("提示","失败!"+resultInfo, stage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            Alert.notifyWarning("提示","暂无叫人!", stage);
        }
    }

    private void checkFinish() {
        if (currentQueueNoLabel.getText()!=null&&currentQueueNoLabel.getText()!=""){
            callInfo.setQueueID(currentQueueNoLabel.getText().substring(1));
            try {
                String maps = WebServices.closeWord(IniUtil.getConfig().get("WSID"), callInfo.getQueueID());
                JSONObject object= JSON.parseObject(maps);
                Integer result = (Integer) object.get("result");
                System.out.println("结束返回参数："+result);
                if (result==1){
                    Alert.notifyWarning("提示","成功!", stage);
                    updateCurrentCustomer("", "");
                    getButtonDisableCallNext(callNextButton);
                } else {
                    String resultInfo = (String) object.get("resultInfo");
                    Alert.notifyWarning("提示","失败!"+resultInfo, stage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            Alert.notifyWarning("提示","暂无叫人!", stage);
        }
    }

    private void hangUp() {

        if (currentQueueNoLabel.getText()!=null&&currentQueueNoLabel.getText()!=""){
            callInfo.setQueueID(currentQueueNoLabel.getText().substring(1));
            try {
                String wsid = WebServices.callArrive(IniUtil.getConfig().get("WSID"), callInfo.getQueueID());
                JSONObject object= JSON.parseObject(wsid);
                Integer result = (Integer) object.get("result");
                if (result==1){
                    Alert.notifyWarning("提示","过号成功!", stage);
                    updateCurrentCustomer("", "");
                    getButtonDisableCallNext(callNextButton);
                } else {
                    Alert.notify("error","过号失败!", stage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            Alert.notifyWarning("提示","暂无叫人!", stage);
        }
    }
    /*下一位按钮触发*/
    /**
     * 4秒内不能重复点击
     *
     * @param button btn
     */
    private void setButtonDisable(Button button) {
        Platform.runLater(() -> button.setDisable(true));
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> button.setDisable(false));
        });
        thread.start();
    }
    private void setButtonDisableCallNext(Button button) {
        Platform.runLater(() -> button.setDisable(true));
    }
   private void   getButtonDisableCallNext(Button button) {
        Platform.runLater(() -> button.setDisable(false));
    }
    private void callRepeat(QueueItem queueInfo){
        if (queueInfo == null) {
            return;
        }
        JacobUtil jacobUtil=new JacobUtil();
        if (queueInfo.getQueueNo()!=null&&queueInfo.getQueueNo()!="") {
            jacobUtil.JacobJob("请,A" + queueInfo.getQueueNo() + ",到" + IniUtil.getConfig().get("WSID") + "号窗口就诊.");
        }
        try {
            String wsidRepeat = WebServices.callRepeat(IniUtil.getConfig().get("WSID"), String.valueOf(queueInfo.getQueueNo()));
            if(wsidRepeat!=null&&wsidRepeat!=""){
                JSONObject object= JSON.parseObject(wsidRepeat);
                Object result = object.get("result");
                if ("0".equals(result)){
                    //提示
                    String resultInfo = (String) object.get("resultInfo");
                    Alert.notifyWarning("提示",resultInfo, stage);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 指定呼叫 列表选中项
     * 下一位   列表中第一位
     * 重呼    当前内存中的queueInfo
     * <p>
     * 发送socket
     *
     * @param queueInfo queueInfo
     */
    private void callAppointed(QueueItem queueInfo) {
        if (queueInfo == null) {
            return;
        }
        System.out.println("第一条："+queueInfo.getQueueNo());
        //"请,A10012,到四号窗口就诊."
        JacobUtil jacobUtil=new JacobUtil();
        if (queueInfo.getQueueNo()!=null&&queueInfo.getQueueNo()!="") {
            jacobUtil.JacobJob("请,A" + queueInfo.getQueueNo() + ",到" + IniUtil.getConfig().get("WSID") + "号窗口就诊.");
        }
        try {
            String map;
            Object priority_id = null;
           /* if(priority_id==null){
               map = WebServices.toCallNormal(Integer.valueOf(IniUtil.getConfig().get("WSID")),"");
                System.out.println("入参1："+priority_id);
            }else {
               map = WebServices.toCallNormal(Integer.valueOf(IniUtil.getConfig().get("WSID")),String.valueOf(priority_id));

               System.out.println("入参2："+priority_id);
            }*/
            map = WebServices.toCallNormal(Integer.valueOf(IniUtil.getConfig().get("WSID")),String.valueOf(queueInfo.getQueueNo()));

            System.out.println("入参2："+priority_id);
            if(map!=""&&map!=null){
                JSONObject object= JSON.parseObject(map);
                Object queue_no = object.get("Data_ID");
                priority_id = object.get("Queue_No");
                System.out.println(queue_no+"::"+priority_id);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取工作站信息
     */
    private void getWorkStationInfo() {

    }
}
