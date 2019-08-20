package com.controller;

import com.entity.DisplayInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LSDisplay implements Initializable {
    @FXML
    private Label labelleft;
    @FXML
    private Label labelright;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label labelnext;
private ArrayList<DisplayInfo> display=new ArrayList<DisplayInfo>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //showDisplayInfo(List<DisplayInfo> arrayList);
    }
    public Label getLabel(String labelNum) {
        switch (labelNum) {
            case "1":
                return label1;
            case "2":
                return label2;
            case "3":
                return label3;
            case "4":
                return label4;
            case "5":
                return label5;
            default:
                return new Label();
        }
    }
    public void showDisplayInfo(List<DisplayInfo> arrayList) {

    }
}
