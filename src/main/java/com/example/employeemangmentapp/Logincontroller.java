package com.example.employeemangmentapp;

import java.sql.Connection;

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

public class Logincontroller {

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
    private AnchorPane signup;

    @FXML
    private TextField username;
    @FXML
    private TextField addpasswordfield;

    @FXML
    private TextField addusernamefield;

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
    public void addAdmin(){
        c = DataBaseConnection.getconncetion();
        try {
            //frist we want to make sure that there is no admin with the same username
            ps = c.prepareStatement("SELECT * FROM admin WHERE username = ?");
            ps.setString(1,addusernamefield.getText());
            queryrs =  ps.executeQuery();
            //if there is a admin with same usenam alert
            if(queryrs.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("There is a user with the same username");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            //else we add the admin
            else{
                if(addusernamefield.getText().isEmpty() ||addpasswordfield.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Fill The Blank Fields");
                    alert.showAndWait();
                }
                else {
                    c = DataBaseConnection.getconncetion();
                    ps = c.prepareStatement("INSERT INTO admin (username , password) VALUES (?,?)");
                    ps.setString(1,addusernamefield.getText());
                    ps.setString(2,addpasswordfield.getText());
                    ps.executeUpdate();
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Admin added");
                    a.setHeaderText(null);
                    a.setContentText("the user name " +addusernamefield.getText() + " was added to database" );
                    a.showAndWait();
                }


            }
        }
        catch (Exception e){
            System.out.println("error adding to database");
            e.printStackTrace();
        }
    }
    public void switchtoadd(){
        main_form.setVisible(false);
        signup.setVisible(true);
    }
    public void switchtologin(){
        main_form.setVisible(true);
        signup.setVisible(false);
    }


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

                    FXMLLoader loader = new FXMLLoader (getClass().getResource("dash.fxml"));
                    Parent root = loader.load();
                    dashboardcontroller dashcontroll = loader.getController();
                    dashcontroll.updateusername(username.getText());
                    System.out.println(dashcontroll);

                    Stage stage = (Stage) login.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);


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
                    app.centerstage(root);
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