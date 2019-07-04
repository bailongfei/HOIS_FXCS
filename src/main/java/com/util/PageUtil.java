package com.util;

import com.controller.LoginPaneController;
import com.controller.WorkStationController;
import com.entity.BasicMainDisplay;
import com.entity.BasicSrvGroup;
import com.entity.BasicStaffInfo;
import com.entity.Item;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PageUtil {

    /*
     登录
    * */
    public static void toLogin(Stage stage){
        LoginPaneController.stage=stage;
        FXMLLoader fxmlloader=new FXMLLoader();
        fxmlloader.setLocation(PageUtil.class.getClass().getResource("/fxml/LoginPane.fxml"));
        try {
            AnchorPane anchorPane=fxmlloader.load();//布局面板加载fxml路经
            Scene scene=new Scene(anchorPane);//布局添加到场景
            stage.setScene(scene);//场景添加到舞台
            stage.setResizable(false);//窗口不可变
            stage.getIcons().add(new Image(PageUtil.class.getResourceAsStream("/static/xhIcon.png")));//添加窗口图标
            stage.setTitle("登录器");
            stage.setOnCloseRequest(event -> Platform.runLater(() -> System.exit(0)));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * 工作站
    * */
    public static void toWorkStation(Stage stage, List<BasicMainDisplay> displayInfos, BasicStaffInfo staffInfo, BasicSrvGroup basicSrvGroup) {
        WorkStationController.stage=stage;
        WorkStationController.displayInfos=displayInfos;
        WorkStationController.StaffInfo=staffInfo;
        WorkStationController.basicSrvGroup=basicSrvGroup;
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(PageUtil.class.getResource("/fxml/WorkStationPane.fxml"));
        try {
            AnchorPane anchorpane=fxmlLoader.load();
            Scene scene=new Scene(anchorpane);
            stage.setScene(scene);
            stage.setTitle("工作站");
            stage.setAlwaysOnTop(true);
            //setSide(stage, null);
            stage.setOnCloseRequest(event -> Platform.runLater(() -> {
                System.exit(0);
            }));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
