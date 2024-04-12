package com.example.employeemangmentapp;

import java.io.FileInputStream;
import java.sql.Connection;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
//

public class App extends Application {
    //will be needed to make stage draggable
    private double x = 0;
    private  double y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        //load the scenebuilder fxml file
        Parent root =  FXMLLoader.load(getClass().getResource("login.fxml"));
        root.setOnMousePressed((MouseEvent e) -> {
            x = e.getSceneX();
            y = e.getSceneY();
            //get مكان الماوس في السين
        });

        root.setOnMouseDragged((MouseEvent e) -> {
            //getScreen بتجيب مكان الماوس في الشاشه
            //e.getScreen الهدف منها انها نجيب مكان الضغطه في الشاشه كلها
            // الاكس و الواي الي بيطرح منه هنا هوا مكان الضغطه الي ضغطها في السين
            // لما بطرح الاتنين كاني بيقي معايا الحافه بتاعت stage لما بعملها drag
            stage.setX(e.getScreenX() - x);
            stage.setY(e.getScreenY() - y);
            stage.setOpacity(0.7);

            // خليه مكان stage = مكان الماوس في الشاشه ناقص مكان الماوس في السين

        });
        root.setOnMouseReleased((MouseEvent e) ->{
            stage.setOpacity(1);
        } );
        FileInputStream file = new FileInputStream("G:\\sleeping dogs\\team.png");
        Image image = new Image(file);
        stage.getIcons().add(image);
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root, 600, 400);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();



    }
    static  void centerstage(Parent root){
        Stage stage =(Stage) root.getScene().getWindow();
        Scene scene = root.getScene();
        //getPrimary بترجع متغير من نوع Screen بيمثل الشاشه الاساسيه
        //getVisualBounds بترجع متغير من نوع Rectangle2D بيمثل الابعاد
        //نحزن ابعاد الشاشه في متغير من نوع Rectangle2D
        Rectangle2D Screenbound = Screen.getPrimary().getVisualBounds();
        //عشان نوسط الstage بالعرض هنحط المحور x يساوي عرض الشاشه علي 2 نطرح منها نص عرض السين
        stage.setX((Screenbound.getWidth() / 2) - (scene.getWidth() / 2));
        //عشان نوسط الstage بالعرض هنحط المحور Y يساوي طول الشاشه علي 2 نطرح منها نص طول السين
        stage.setY((Screenbound.getHeight() / 2) - (scene.getHeight() / 2));
    }
    public static void main(String[] args) {
        Connection connection = DataBaseConnection.getconncetion();
        if ((connection == null)) {
            System.out.println("No Connection");
        } else {
            System.out.println("Connected");
        }
        launch();
    }
}
