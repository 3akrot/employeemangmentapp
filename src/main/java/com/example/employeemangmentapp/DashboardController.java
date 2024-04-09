package com.example.employeemangmentapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private TableView<Employee> addemployeetable;
    @FXML
    private ComboBox<String> seacrhcombo;

    @FXML
    private TableColumn<Employee, Date> addemplye_col_datemember;

    @FXML
    private TableColumn<Employee, String> addemplye_col_ID;

    @FXML
    private Label addemployedep;


    @FXML
    private TableColumn<Employee, String> addemplye_col_age;

    @FXML
    private TableColumn<Employee, String> addemplye_col_rating;
    @FXML
    private TableColumn<Employee, String>  addemplyeed_col_specl;

    @FXML
    private TableColumn<Employee, String> addemplye_col_div;

    @FXML
    private TableColumn<Employee, String> addemplye_col_experience;

    @FXML
    private TableColumn<Employee, String> addemplye_col_active;


    @FXML
    private TableColumn<Employee, String> addemplye_col_name;
    @FXML
    private TableColumn<?, ?> addemplye_col_salary;

    @FXML
    private Button addemplyeeadd;

    @FXML
    private TextField addemplyeeage;

    @FXML
    private Button addemplyeeclr;

    @FXML
    private TextField addemplyeedatemember;

    @FXML
    private Button addemplyeedl;

    @FXML
    private TextField addemplyeeexperience;

    @FXML
    private ComboBox<?> addemplyeegender;

    @FXML
    private TextField addemplyeeid;

    @FXML
    private AnchorPane addemplyeeimage;

    @FXML
    private Button addemplyeeimportbtn;
    @FXML
    private ComboBox<String> addemplyeedvac;

    @FXML
    private TextField addemplyeename;
    @FXML TextField addemplye_salary;
    @FXML
    private Label warn;
    @FXML
    private TextField addemplyeesearch;
    @FXML
    ImageView addemplyeimageview;


    @FXML
    private Button addemplyeeup;

    @FXML
    private AnchorPane addpage;

    @FXML
    private CheckBox addotherdep;

    @FXML
    private ComboBox<String> newaddempsepc;
    @FXML
    private BarChart<?, ?> homecahrt;

    @FXML
    private Label hometotalemployee;

    @FXML
    private Label hometotalinactive;

    @FXML
    private Label hometotalpresnts;

    @FXML
    private AnchorPane hompage;

    @FXML
    private Button logoutbtn;

    @FXML
    private StackPane root;

    @FXML
    private TableColumn<?, ?> salary_div_col;

    @FXML
    private TableColumn<?, ?> salary_id_col;

    @FXML
    private TableColumn<?, ?> salary_name_col;

    @FXML
    private TableColumn<?, ?> salary_rating_col;

    @FXML
    private TableColumn<?, ?> salary_slary_col;

    @FXML
    private Button salaryclr;

    @FXML
    private Label salarydivtion;

    @FXML
    private TextField salaryid;

    @FXML
    private Label salaryname;

    @FXML
    private AnchorPane salarypage;

    @FXML
    private TextField salaryrateinp;

    @FXML
    private Label salaryrating;

    @FXML
    private Label salarysalary;

    @FXML
    private TextField salarysalryinp;


    @FXML
    private TableView<?> salarytable;

    @FXML
    private Button salaryupdate;

    @FXML
    public Label usernamelabel;
    @FXML
    private Button homebtn;
    @FXML
    private Button addemplyeebtn;
    @FXML
    private Button emplyeesalarybtn;


    @FXML
    private Label usernamelabel1;
    private Connection connect;
    private PreparedStatement ps;
    private ResultSet queryres;
    private Statement statement;
    boolean showallemp;
    String requiredspec = "All";

    public ObservableList<Employee> addEmployessdata() throws SQLException {
    ObservableList<Employee> listofemployees = FXCollections.observableArrayList();
    connect = DataBaseConnection.getconncetion();
    try{
       if(showallemp){
           if(seacrhcombo.getValue() == null){
               ps = connect.prepareStatement("SELECT * FROM employe ");

           }
           else {
               if(seacrhcombo.getValue().equals("All")) {
                   ps = connect.prepareStatement("SELECT * FROM employe ");
               }
               else {
                   ps = connect.prepareStatement("SELECT * FROM employe  WHERE specialition = ?");
                   ps.setString(1,seacrhcombo.getValue());

               }

           }


       }
       else {
           if(seacrhcombo.getValue() == null) {
               ps = connect.prepareStatement("SELECT * FROM employe WHERE department = ?");
               ps.setString(1,Admin.div);

           }
           else {
               if(seacrhcombo.getValue().equals("All")){
                   ps = connect.prepareStatement("SELECT * FROM employe WHERE department = ? ");
                   ps.setString(1,Admin.div);
               }
               else {
                   ps = connect.prepareStatement("SELECT * FROM employe WHERE department = ? and  specialition = ?");
                   ps.setString(1,Admin.div);
                   ps.setString(2,seacrhcombo.getValue());

               }

           }

       }
    }
    catch (SQLException e) {
        System.out.println("error in query");
    }

    queryres = ps.executeQuery();
    System.out.println(ps);
    while (queryres.next()){
        Employee employee;
        employee =  new Employee(queryres.getInt("employeid"),
                queryres.getString("employename"),
                queryres.getInt("age"),
                queryres.getString("department"),
                queryres.getDate("datemember"),
                queryres.getInt("expyears"),
                queryres.getInt("rating"),
                queryres.getString("image"),
                queryres.getInt("ratetimes"),
                queryres.getDouble("salary"),
                queryres.getString("active"),
                queryres.getString("specialition")

        );

        listofemployees.add(employee);
    }
    return listofemployees;

    }

    private ObservableList<Employee> Employees;

    private void showEmployessdata() throws SQLException {
        Employees = addEmployessdata();
        addemplye_col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        addemplye_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        addemplye_col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        addemplye_col_div.setCellValueFactory(new PropertyValueFactory<>("div"));
        addemplye_col_datemember.setCellValueFactory(new PropertyValueFactory<>("datemem"));

        addemplye_col_experience.setCellValueFactory(new PropertyValueFactory<>("exyears"));
        addemplye_col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        addemplye_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        addemplye_col_active.setCellValueFactory(new PropertyValueFactory<>("active"));
        addemplyeed_col_specl.setCellValueFactory(new PropertyValueFactory<>("specialition"));
        addemployeetable.setItems(Employees);

    }

    @FXML
    void searchfilter() throws SQLException {
        addEmployessdata();
        showEmployessdata();

    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void updateusername(String use) {
        usernamelabel.setText(use);
    }
    @FXML
    void setrequiredspec(){
        requiredspec = seacrhcombo.getValue();
    }

    @FXML
    void navtoadd(ActionEvent event) {
        salarypage.setVisible(false);
        hompage.setVisible(false);
        addpage.setVisible(true);
        addemplyeebtn.setStyle("-fx-background-color:#3b7dd4;");
        homebtn.setStyle("background-color:transparent");
        emplyeesalarybtn.setStyle("background-color:transparent");
    }

    @FXML
    void navtohome(ActionEvent event) {
        salarypage.setVisible(false);
        addpage.setVisible(false);
        hompage.setVisible(true);
        homebtn.setStyle("-fx-background-color:#3b7dd4;");
        addemplyeebtn.setStyle("background-color:transparent");
        emplyeesalarybtn.setStyle("background-color:transparent");

    }

    @FXML
    void navtosalary(ActionEvent event) {
        addpage.setVisible(false);
        hompage.setVisible(false);
        emplyeesalarybtn.setStyle("-fx-background-color:#3b7dd4;");
        homebtn.setStyle("background-color:transparent");
        addemplyeebtn.setStyle("background-color:transparent");
        salarypage.setVisible(true);

    }

    @FXML
    void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conform");
        alert.setHeaderText(null);
        alert.setContentText("Are Sure You Want To Log out");
        Optional<ButtonType> choic = alert.showAndWait();
        if (choic.get().equals(ButtonType.OK)) {
            Stage stage = (Stage) logoutbtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            try {
                Parent root = loader.load();
                Scene sc = new Scene(root);
                stage.setScene(sc);
                App.centerstage(root);
                Admin.adminname = null;
                Admin.div = null;
            } catch (IOException e) {

            }
        }


    }

    @FXML
    void showadmonname() {
        System.out.println(Admin.adminname);
    }
    @FXML
    void showornotshow(ActionEvent event) {

        if (addotherdep.isSelected()){
            showallemp = true;
            try {
                addEmployessdata();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                showEmployessdata();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            showallemp = false;
            try {
                addEmployessdata();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                showEmployessdata();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void importimage(){
         Image image;

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(addemployeetable.getScene().getWindow());
        if (file != null){
            image =  new Image(file.toURI().toString(),132,134,false,true);
            addemplyeimageview.setImage(image);

        }
    }
    @FXML
    public void addEmployeonselct() {
        if(addemployeetable.getSelectionModel().isEmpty()){
            return;
        }

        Employee employee = addemployeetable.getSelectionModel().getSelectedItem();
        if(employee.getDiv().equals(Admin.div) ){
            enablenodes();
        }
        else{
            disablenodes();

        }
        addemplyeeid.setText(String.valueOf(employee.getId()));
        addemplyeename.setText(String.valueOf(employee.getName()));
        addemplyeeage.setText(String.valueOf(employee.getAge()));
        addemplyeeexperience.setText(String.valueOf(employee.getExyears()));
        addemplye_salary.setText(String.valueOf(employee.getSalary()));
        addemployedep.setText(employee.getDiv());
        newaddempsepc.setItems(new ComboBoxLists().getSpecialitionlist(employee.getDiv()));
        newaddempsepc.getSelectionModel().select(employee.getSpecialition());

        System.out.println(employee.getSpecialition());

        if(employee.getImage().isEmpty()){
            return;
        }
        Image image = new Image(employee.getImage(),132,134,false,true);
        addemplyeimageview.setImage(image);
        System.out.println(employee.getImage());


    }
    @FXML
    public void clr(){
        addemplyeeid.setText("");
        addemplyeename.setText("");
        addemplyeeage.setText("");
        addemplye_salary.setText("");
        addemplyeeexperience.setText("");
        newaddempsepc.setItems(new ComboBoxLists().getSpecialitionlist(Admin.div));
        addemployedep.setText("");
        enablenodes();

    }
    @FXML void del() throws SQLException {
        connect = DataBaseConnection.getconncetion();
        ps = connect.prepareStatement("DELETE FROM employe WHERE employeid = ?");
        ps.setInt(1,Integer.parseInt(addemplyeeid.getText()));
        Alert a  = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("CONFIRMATION");
        a.setHeaderText(null);
        a.setContentText("Are You Sure You Want To Delete " + addemplyeename.getText());
        Optional<ButtonType> ans=a.showAndWait();
        if(ans.get() == ButtonType.OK){
            ps.executeUpdate();
            System.out.println(ps);
            showEmployessdata();
            Alert x  = new Alert(Alert.AlertType.INFORMATION);
            x.setTitle("INFORMATION");
            x.setHeaderText(null);
            x.setContentText(" Deleted " + addemplyeename.getText());
            x.showAndWait();

        }


    }
    @FXML
    public void addEmployee() throws SQLException {
        java.util.Date date = new java.util.Date();
        java.sql.Date currentdate = new Date(date.getTime());
        String sql = "INSERT INTO employe (id, employeid, employename, age, department, specialition , datemember, expyears, rating, image, ratetimes, salary, active) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, 'True')";
        if(addemplyeeid.getText().isEmpty()||
                addemplyeename.getText().isEmpty()||
                addemplyeeage.getText().isEmpty() ||
                addemplye_salary.getText().isEmpty()||
                addemplyeeexperience.getText().isEmpty()||
                addemplyeeexperience.getText().isEmpty()||
                addemplyeimageview.getImage() == null ||
                newaddempsepc.getValue() == null
        ){
            //
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("Please Fill All Fields");
            a.showAndWait();
        }
        else{

            connect = DataBaseConnection.getconncetion();
            ps = connect.prepareStatement("SELECT * FROM employe WHERE employeid = ?");
            ps.setInt(1, Integer.parseInt(addemplyeeid.getText()));
            queryres = ps.executeQuery();
            if(queryres.next()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText(null);
                a.setContentText("There is a employee with same id");
                a.showAndWait();
            }
            else{
                ps = connect.prepareStatement(sql);
                ps.setInt(1,Integer.parseInt(addemplyeeid.getText()));
                ps.setString(2,addemplyeename.getText());
                ps.setInt(3,Integer.parseInt(addemplyeeage.getText()));
                ps.setString(4, Admin.div);
                ps.setString(5,newaddempsepc.getValue());
                ps.setDate(6,currentdate);
                ps.setInt(7, Integer.parseInt(addemplyeeexperience.getText()));
                ps.setInt(8,0);
                ps.setString(9,addemplyeimageview.getImage().getUrl());
                ps.setDouble(10, Double.parseDouble(addemplye_salary.getText()));
                ps.executeUpdate();
                System.out.println(ps);
                showEmployessdata();
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("INFORMATION");
                a.setHeaderText(null);
                a.setContentText("Successfully Added!");
                a.showAndWait();
            }




        }
    }
    @FXML
    public void disablenodes(){
        addemplyeeid.setDisable(true);
        addemplyeename.setDisable(true);
        addemplyeeage.setDisable(true);

        newaddempsepc.setDisable(true);
        addemplyeeexperience.setDisable(true);
        addemplyeimageview.setDisable(true);
        addemplye_salary.setDisable(true);
        addemplyeeimportbtn.setDisable(true);
        addemplyeedl.setDisable(true);
        addemplyeeup.setDisable(true);
        addemplyeeadd.setDisable(true);
        warn.setVisible(true);
    }
    public void enablenodes(){
        addemplyeeid.setDisable(false);
        addemplyeename.setDisable(false);
        addemplyeeage.setDisable(false);

        newaddempsepc.setDisable(false);
        addemplyeeexperience.setDisable(false);
        addemplyeimageview.setDisable(false);
        addemplye_salary.setDisable(false);
        addemplyeeimportbtn.setDisable(false);

        addemplyeedl.setDisable(false);
        addemplyeeup.setDisable(false);
        addemplyeeadd.setDisable(false);
        warn.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newaddempsepc.setItems(new ComboBoxLists().getSpecialitionlist(Admin.div));
        ObservableList<String> combolist = new ComboBoxLists().getSpecialitionlist(Admin.div);
        combolist.add("All");
        seacrhcombo.setItems(combolist);
//        addemplyespecialition.setItems(new ComboBoxLists().getSpecialitionlist(Admin.div));
        homebtn.setStyle("-fx-background-color:#3b7dd4;");
        usernamelabel.setText(Admin.adminname);
        usernamelabel1.setText(Admin.div);
        try {
            addEmployessdata();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            showEmployessdata();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        Timer timer = new Timer();
//        Liveupdate  live = new Liveupdate();
//        timer.schedule(live,0,300);

    }
//    class Liveupdate extends TimerTask {
//
//        @Override
//        public void run() {
//            try {
//                addEmployessdata();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                showEmployessdata();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }




}
