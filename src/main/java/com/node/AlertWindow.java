package com.node;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertWindow {
    public static boolean ansver;
    public static boolean display(String tiitle,String mg){
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Label label=new Label();
        label.setText(mg);
        Button button=new Button("是");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ansver=true;
                stage.close();
            }
        });
        Button button2=new Button("否");
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ansver=false;
                stage.close();
            }
        });
        VBox vBox=new VBox();
        vBox.getChildren().addAll(label,button,button2);
        vBox.setAlignment(Pos.CENTER);
        Scene scene=new Scene(vBox,300,300);
        stage.setScene(scene);
        stage.setTitle(tiitle);
        stage.showAndWait();
        return ansver;
    }
}
