package com.example.employeemangmentapp;

import java.sql.Connection;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Button close;

    @FXML
    private Button login;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label usernamelabel;
    @FXML
    private Button logout;



    @FXML
    private TextField password;

    @FXML
    private TextField username;
    //this method for closing the app
    public  void close(){

        System.exit(0);
    }
    //login for admin
    Connection c;
    PreparedStatement ps;
    ResultSet queryrs;
    private double x;
    private double y;


    public void adminlog(){
        String query = "SELECT * FROM admin WHERE username = ? and password = ?";
        c = DataBaseConnection.getconncetion();
        try {
            ps = c.prepareStatement(query);
            ps.setString(1,username.getText());
            ps.setString(2,password.getText());
            admin.username = username.getText();
            queryrs = ps.executeQuery();
            Alert alert;
            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill The Blank Fields");
                alert.showAndWait();
            }
            else {
                if(queryrs.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully login in");
                    alert.showAndWait();
                    login.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dash.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                    stage.initStyle(StageStyle.TRANSPARENT);
                    root.setOnMousePressed((MouseEvent e) -> {
                        x = e.getSceneX();
                        y = e.getSceneY();

                        //get مكان الماوس في السين

                    });
                    root.setOnMouseDragged((MouseEvent e) -> {
                        //getScreen بتجيب مكان الماوس في الشاشه
                        stage.setX(e.getScreenX() - x);
                        stage.setY(e.getScreenY() - y);
                        stage.setOpacity(0.7);
                        // خليه مكان الستيج = مكان الماوس في الشاشه ناقص مكان الماوس في السين

                    });
                    root.setOnMouseReleased((MouseEvent e) ->{
                        stage.setOpacity(1);
                    } );

                    stage.show();

                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong UserName or Password");
                    alert.showAndWait();
                }
            }
        }
        catch (Exception e){
            System.out.println("Error in query");
            e.printStackTrace();
        }


    }
}