package com.example.employeemangmentapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class dashboardcontroller {

    @FXML
    private Label usernamelabel;
    @FXML
    private Button full;


    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private StackPane root;
    @FXML
    private AnchorPane addpage;

    @FXML
    private AnchorPane hompage;

    void updateusername(String use){
        usernamelabel.setText(use);
    }

//    void makefull(ActionEvent event) {
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.setFullScreen(true);
//    }
//    @FXML
//    void makenofull(ActionEvent event){
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.setFullScreen(false);
//    }

    @FXML
    void navtoadd(ActionEvent event) {
    hompage.setVisible(false);
    addpage.setVisible(true);
    }

    @FXML
    void navtohome(ActionEvent event) {
        addpage.setVisible(false);
        hompage.setVisible(true);

    }




}