package com.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    public Button btn;
    public Label lable;

    public void onClick(ActionEvent actionEvent) {
        Button btnSource = (Button) actionEvent.getSource();
        btnSource.setText("I am clicked!");
        lable.setText("点击了按钮");
    }
}
