module com.example.employeemangmentapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;


    opens com.example.employeemangmentapp to javafx.fxml;
    exports com.example.employeemangmentapp;
}