package com.example.employeemangmentapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
//

public class HelloApplication extends Application {
    //will be needed to make stage draggable
    private double x = 0;
    private  double y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        //load the scenebuilder fxml file
        Parent root =  FXMLLoader.load(getClass().getResource("hello-view.fxml"));
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
        stage.initStyle(StageStyle.TRANSPARENT);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}