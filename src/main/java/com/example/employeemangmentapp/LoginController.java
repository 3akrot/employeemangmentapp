package com.example.employeemangmentapp;

import java.net.URL;
import java.sql.Connection;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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
    private TextField adminnameinp;

    @FXML
    private TextField username;

    @FXML
    private TextField addpasswordfield;

    @FXML
    private TextField addusernamefield;

    @FXML
    private ComboBox<String> dep;

    Connection c;

    PreparedStatement ps;

    ResultSet queryrs;

    private double x;

    private double y;

    //this method for closing the app
    //login for admin

    public  void close(){

        System.exit(0);
    }

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
                    if(dep.getValue() == null){
                        System.out.println("missing dep");
                        //TODO
                    }
                    else{
                        c = DataBaseConnection.getconncetion();
                        ps = c.prepareStatement("INSERT INTO admin (username , password , adminname , department) VALUES (?,?,?,?)");
                        ps.setString(1,addusernamefield.getText());
                        ps.setString(2,addpasswordfield.getText());
                        ps.setString(3,adminnameinp.getText());
                        ps.setString(4,dep.getValue());
                        System.out.println(ps);
                        ps.executeUpdate();
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Admin added");
                        a.setHeaderText(null);
                        a.setContentText("the username " +addusernamefield.getText() + " was added to database" );
                        a.showAndWait();
                    }

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
        String query = "SELECT * FROM admin WHERE username = ? ";
        c = DataBaseConnection.getconncetion();
        try {
            ps = c.prepareStatement(query);
            ps.setString(1,username.getText());



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
                    if(queryrs.getString(2).equals(username.getText()) && queryrs.getString(3).equals(password.getText())){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMATION");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully login in");
                        alert.showAndWait();
                        System.out.println(ps);
                        Admin.adminname = queryrs.getString(4);
                        Admin.div = queryrs.getString(5);

                        FXMLLoader loader = new FXMLLoader (getClass().getResource("dash.fxml"));
                        Parent root = loader.load();


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
                        App.centerstage(root);
                        stage.show();

                    }
                    else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Password Or UserName");
                        alert.showAndWait();
                    }


                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("User Not Found");
                    alert.showAndWait();


                }
            }
        }
        catch (Exception e){
            System.out.println("Error in query");
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBoxLists c = new ComboBoxLists();
        dep.setItems(c.getDepartmentslist());

    }
}