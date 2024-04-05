module com.example.employeemangmentapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeemangmentapp to javafx.fxml;
    exports com.example.employeemangmentapp;
}