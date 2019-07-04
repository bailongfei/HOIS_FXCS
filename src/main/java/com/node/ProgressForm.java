package com.node;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @Package: com.node
 * @ClassName: ProgressForm
 * @Description: 异步操作界面
 * @Author: LaoShiRen
 * @CreateDate: 2019-05-02 20:29
 * @Version: 1.0
 */
public class ProgressForm {

    private static Stage dialogStage;

    private static ProgressForm progressForm;

    private ProgressForm(Stage primaryStage ) {
        dialogStage = new Stage();
        // 进度指示
        ProgressIndicator progressIndicator = new ProgressIndicator();
        // 设置stage 父节点
        dialogStage.initOwner(primaryStage);
        // 设置初始属性
        dialogStage.initStyle(StageStyle.TRANSPARENT);
//        dialogStage.initModality(Modality.APPLICATION_MODAL);
        // 获取父面板宽高和位置
        dialogStage.setHeight(primaryStage.getHeight());
        dialogStage.setWidth(primaryStage.getWidth());
        dialogStage.setX(primaryStage.getX());
        dialogStage.setY(primaryStage.getY());
        primaryStage.xProperty().addListener(e ->
                Platform.runLater(() ->
                        dialogStage.setX(primaryStage.getX())));
        primaryStage.yProperty().addListener(e ->
                Platform.runLater(() ->
                        dialogStage.setY(primaryStage.getY())));
        // 设置信息
        Label label = new Label("请稍后......");
        label.setFont(new Font("Microsoft YaHei",30));
        progressIndicator.setProgress(-1);
        // 绑定任务进度属性
        VBox vBox = new VBox();

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setBackground(Background.EMPTY);
//        设置控件放进一个vBox
        vBox.getChildren().addAll(progressIndicator,label);
        vBox.setStyle("-fx-background-color: rgba(195,195,195,0.7);");
        Scene scene = new Scene(vBox);
        scene.setFill(null);
        dialogStage.setScene(scene);
        // 永远在顶层
        dialogStage.setAlwaysOnTop(true);
    }
    /**
     * 激活加载动画
     */
    public void activateProgressBar() {
        dialogStage.show();
    }

    public void cancelProgressBar() {
        dialogStage.close();
        progressForm = null;
    }

    public static ProgressForm getInstance(Stage stage) {
        if (progressForm==null ){
            progressForm = new ProgressForm(stage);
        }
        return progressForm;
    }
}
