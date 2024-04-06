package com.example.employeemangmentapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class dashboardcontroller {

    @FXML
    private Label usernamelabel;

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private StackPane root;

    @FXML
    void on(){
        usernamelabel.setText(admin.username);
    }

//    void on(ActionEvent event) {
//        System.out.println(usernamelabel);
//
//    }


}