package com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class AppointController {
    public static Stage stage;
   public static String Id;
   public static String Name;
   public static String Dates;
   public static String Start;
   @FXML
   private TextField pdId;
   @FXML
    private TextField pdName;
   @FXML
    private TextField pdDate;
   @FXML
    private TextField pdStart;
   @FXML
   private Button btn1;
   @FXML
    public void initialize(){
       init();
       bindMouseEvent();
   }

    private void init(){
       pdId.setText(Id);
       pdName.setText(Name);
       pdDate.setText(Dates);
       pdStart.setText(Start);
    }
    private void bindMouseEvent() {
        btn1.setOnMouseClicked(event -> {
            if(MouseButton.PRIMARY.equals(event.getButton())){

            }

        });
    }

}
