package com.controller;

import com.entity.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;
import java.util.Timer;

public class WorkStationController {
    public static Stage stage;
    private  BasicWorkStation workStation=null;//工作站窗口
    public static BasicStaffInfo StaffInfo=null;//员工
    public static List<BasicMainDisplay> displayInfos = null;//显示设备信息
    public static BasicSrvGroup basicSrvGroup;//项目
    private Timer queueTimer = new Timer();
    @FXML
    private Label infoLabel;
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
    private TableView<QueueInfo> queueTable;
    @FXML
    private Label queueNumberLabel;
    @FXML
    private TableColumn<QueueInfo, Label> queueNoColumn;
    @FXML
    private TableColumn<QueueInfo, Label> queueNameColumn;
    @FXML
    private TableColumn<QueueInfo, Label> queueTimeColumn;
    @FXML
    private TableColumn<QueueInfo, Label> queueStartColumn;
    @FXML
    private TableView<QueueInfo> calledQueueTable;
    @FXML
    private Label calledQueueNumberLabel;
    @FXML
    private TableColumn<QueueInfo, Label> calledQueueNoColumn;
    @FXML
    private TableColumn<QueueInfo, Label> calledQueueNameColumn;
    @FXML
    private TableColumn<QueueInfo, Label> calledTimeColumn;
    @FXML
    private TableColumn<QueueInfo, Label> calledStartColumn;
    @FXML
    private void initialize() {
        init();
        bindTableColumn();
        bindMouseEvent();
        handleAutoUpdateQueue();
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
            Label label = new Label(cell.getValue().getQueue_No());
            return new SimpleObjectProperty<>(label);
        });
        queueNameColumn.setCellValueFactory(cell -> {
            Label label = new Label(cell.getValue().getSrvGroup_Name());
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
    }
    private void handleAutoUpdateQueue() {
    }

    private void bindMouseEvent() {
    }
    /**
     * 获取工作站信息
     */
    private void getWorkStationInfo() {

    }
}
