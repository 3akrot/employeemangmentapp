package com.example.employeemangmentapp;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
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
    private ImageView editimagebtn;

    @FXML
    private TableColumn<Employee, Date> addemplye_col_datemember;

    @FXML
    private TableColumn<Employee, String> addemplye_col_ID;

    @FXML
    private Label addemployedep;
    @FXML
    private ImageView userimage;

    @FXML
    private Label fullscreen;

    @FXML
    private TableColumn<Employee, String> addemplye_col_age;

    @FXML
    private TableColumn<Employee, Double> addemplye_col_rating;
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
    private TableColumn<?, ?> rating_col_dep;



    @FXML
    private TextField addemplyeeexperience;

    @FXML
    private ComboBox<?> addemplyeegender;

    @FXML
    private Label addemplyeeid;
    @FXML
    private ProgressBar progress;
    @FXML
    private AnchorPane loading;

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
    private Button exportexcelbtn;


    @FXML
    private Button addemplyeeup;

    @FXML
    private AnchorPane addpage;

    @FXML
    private CheckBox addotherdep;

    @FXML
    private ComboBox<String> newaddempsepc;
    @FXML
    private LineChart<String, Integer> homecahrt;
    @FXML
    private BarChart<?, ?> homecahrt1;
    @FXML
    private BarChart<?, ?> homechart3;

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

//    @FXML
//    private StackPane root;

    //------------------------------------rating------------------------------------------------------
    @FXML
    private TableColumn<?, ?> rating_col_id;

    @FXML
    private Slider rateslider;

    @FXML
    private TableColumn<?, ?> rating_col_active;

    @FXML
    private TableColumn<?, ?> rating_col_name;

    @FXML
    private TableColumn<?, ?> rating_col_ratetimes;

    @FXML
    private TableColumn<Employee, Double> rating_col_rating;
    @FXML
    private ComboBox<String> ratingactive;
    @FXML
    private Button ratingapply;

    @FXML
    private Label ratingid;

    @FXML
    private Label ratingname;

    @FXML
    private Label ratingrating;



    @FXML
    private TableView<Employee> ratingtable;

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
    private Label ratingshow;


    @FXML
    public Label usernamelabel;
    @FXML
    private Button homebtn;
    @FXML
    private Button addemplyeebtn;
    @FXML
    private Button emplyeesalarybtn;
    @FXML
    private ComboBox<String> homecombo;


    @FXML
    private Label usernamelabel1;
    private Connection connect;
    private PreparedStatement ps;
    private ResultSet queryres;
    private Statement statement;
    boolean showallemp;
    String requiredspec = "All";

    public ObservableList<Employee> getEmployeesObservableListFromDatabase() throws SQLException {
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
        employee =  new Employee(queryres.getInt("id"),
                queryres.getString("employename"),
                queryres.getInt("age"),
                queryres.getString("department"),
                queryres.getDate("datemember"),
                queryres.getInt("expyears"),
                queryres.getDouble("rating"),
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
    FilteredList<Employee> filteredList;

    @FXML
    private void ontypesearchup() throws SQLException {
        filteredList = new FilteredList<>(getEmployeesObservableListFromDatabase());


        // addlistner method
        // بستمحلك تعمل تراك لاي Property
        // يعني تقدر تجيب اي value جديد حصل
        // احنا هنا بنستعملها عشان نجيب الvalue بتاعت الtexefield علي طول
        addemplyeesearch.textProperty().addListener(
                //lamda ex
                (obsevable , oldvalue, newvalu) -> {
                    if(!newvalu.isEmpty()){
                        //الشرط بتاه ال filtered list
                        // بترجع الemployee في حاله ان اسم الموظف موجود فه مربع البحث
                        filteredList.setPredicate(e -> {
                            //لو الuser مش مختار يعرض Specialition معين هنفلتر بناء علي الاسم
                            if(seacrhcombo.getSelectionModel().getSelectedItem().equals("All")){
                                if(e.getName().toLowerCase().contains(newvalu.toLowerCase())){
                                    return true;
                                }
                                // بترجع الemployee في حاله ان id الموظف موجود فه مربع البحث
                                if(e.getId().toString().toLowerCase().contains(newvalu)){
                                    return  true;
                                }

                            }
                            //لو الuser  مختار يعرض Specialition معين هنفلتر بناء علي الاسم  id و Specialition

                            else{
                                return (e.getName().toLowerCase().contains(newvalu.toLowerCase()) && e.getSpecialition().equals(seacrhcombo.getSelectionModel().getSelectedItem())) ||
                                        e.getId().toString().toLowerCase().contains(newvalu) && e.getSpecialition().equals(seacrhcombo.getSelectionModel().getSelectedItem()) ;
                            }
                            return false;


                        });
                        try {
                            displayEmployessdataonEmployessTable(filteredList);
                            showemployessonratingtable(filteredList);
                            dataonchart();
                            displaymaincar();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    //لوالtextfield فاضي نعرض كل الموظفين من غير اي فلتره
                    else {
                        try {
                            displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
                            showemployessonratingtable(getEmployeesObservableListFromDatabase());
                            dataonchart();
                            displaymaincar();

                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }



                }
        );

        System.out.println(filteredList);


    }

    private void displayEmployessdataonEmployessTable(ObservableList<Employee> listtoshow) throws SQLException {
        addemplye_col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        addemplye_col_name.setCellValueFactory( new PropertyValueFactory<>( "name"));
        addemplye_col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        addemplye_col_div.setCellValueFactory(new PropertyValueFactory<>("div"));
        addemplye_col_datemember.setCellValueFactory(new PropertyValueFactory<>("datemem"));

        addemplye_col_experience.setCellValueFactory(new PropertyValueFactory<>("exyears"));
        addemplye_col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        addemplye_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        addemplye_col_active.setCellValueFactory(new PropertyValueFactory<>("active"));
        addemplyeed_col_specl.setCellValueFactory(new PropertyValueFactory<>("specialition"));
        addemployeetable.setItems(listtoshow);

    }
    @FXML
    private void update() throws SQLException {
        connect = DataBaseConnection.getconncetion();
        try {
            if(
                    addemplyeename.getText().isEmpty()||
                    addemplyeeage.getText().isEmpty() ||
                    addemplye_salary.getText().isEmpty()||
                    addemplyeeexperience.getText().isEmpty()||
                    addemplyeeexperience.getText().isEmpty()||
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText(null);
                alert.setContentText("update " + addemplyeeid.getText() + " ?");
                Optional<ButtonType> ans = alert.showAndWait();
                if (ans.get().equals(ButtonType.OK)){
                    if(addemplyeimageview.getImage() == null) {
                        ps = connect.prepareStatement("UPDATE employe SET employename = ? ,  age = ? ,  specialition = ? , expyears = ? ,  salary = ? WHERE id = ?");
                        ps.setString(1,addemplyeename.getText());
                        ps.setInt(2, Integer.parseInt(addemplyeeage.getText()));
                        ps.setString(3,newaddempsepc.getValue());
                        ps.setInt(4, Integer.parseInt(addemplyeeexperience.getText()));
                        ps.setDouble(5, Double.parseDouble(addemplye_salary.getText()));
                        ps.setInt(6,Integer.parseInt(addemplyeeid.getText()));
                    }
                    else{
                        ps = connect.prepareStatement("UPDATE employe SET employename = ? ,  age = ? ,  specialition = ? , expyears = ? ,  salary = ? , image = ? WHERE id = ?");
                        ps.setString(1,addemplyeename.getText());
                        ps.setInt(2, Integer.parseInt(addemplyeeage.getText()));
                        ps.setString(3,newaddempsepc.getValue());
                        ps.setInt(4, Integer.parseInt(addemplyeeexperience.getText()));
                        ps.setDouble(5, Double.parseDouble(addemplye_salary.getText()));
                        ps.setInt(7,Integer.parseInt(addemplyeeid.getText()));
                        ps.setString(6,addemplyeimageview.getImage().getUrl());
                    }
                    System.out.println(ps);
                    ps.executeUpdate();
                    displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
                    //update filtered list to match the change
                    filteredList = new FilteredList<>(getEmployeesObservableListFromDatabase());
                    //rest the search result
                    if(!addemplyeesearch.getText().isEmpty()){
                        char x = addemplyeesearch.getText().charAt(addemplyeesearch.getText().length() - 1);
                        addemplyeesearch.setText(addemplyeesearch.getText().substring(0,addemplyeesearch.getText().length() - 1));
                        addemplyeesearch.setText(addemplyeesearch.getText()+x);
                    }

                }


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dataonchart();
    }

    @FXML
    void searchfilter() throws SQLException {
        displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
        if(addotherdep.isSelected() && ! seacrhcombo.getSelectionModel().getSelectedItem().equals("All")){
            addotherdep.fire();
        }
        if(!addemplyeesearch.getText().isEmpty()){
            char x = addemplyeesearch.getText().charAt(addemplyeesearch.getText().length() - 1);
            addemplyeesearch.setText(addemplyeesearch.getText().substring(0,addemplyeesearch.getText().length() - 1));
            addemplyeesearch.setText(addemplyeesearch.getText()+x);
        }


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
        if(! addemplyeesearch.getText().isEmpty()){
            addemplyeesearch.setText("");
        }
        addemplyeesearch.setVisible(true);
        salarypage.setVisible(false);
        hompage.setVisible(false);
        addpage.setVisible(true);
        addemplyeebtn.setStyle("-fx-background-color:#594e9c");
        homebtn.setStyle("background-color:transparent");
        emplyeesalarybtn.setStyle("background-color:transparent");
    }

    @FXML
    void navtohome(ActionEvent event) {
        if(! addemplyeesearch.getText().isEmpty()){
            addemplyeesearch.setText("");
        }
        addemplyeesearch.setVisible(false);
        salarypage.setVisible(false);
        addpage.setVisible(false);
        hompage.setVisible(true);
        homebtn.setStyle("-fx-background-color:#594e9c;");
        addemplyeebtn.setStyle("background-color:transparent");
        emplyeesalarybtn.setStyle("background-color:transparent");

    }

    @FXML
    void navToRating(ActionEvent event) {
        seacrhcombo.getSelectionModel().select("All");
        if(addotherdep.isSelected()){
            addotherdep.fire();
        }
        if(!addemplyeesearch.getText().isEmpty()) {
            addemplyeesearch.setText("");
        }
            addemplyeesearch.setText("a");
            addemplyeesearch.setText("");






        seacrhcombo.getSelectionModel().select("All");
        addemplyeesearch.setVisible(true);
        addpage.setVisible(false);
        hompage.setVisible(false);
        emplyeesalarybtn.setStyle("-fx-background-color:#594e9c;");
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
            if(!seacrhcombo.getSelectionModel().getSelectedItem().equals("All")){
                seacrhcombo.getSelectionModel().select("All");
            }

            try {
                displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            showallemp = false;

            try {
                displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(!addemplyeesearch.getText().isEmpty()){
            char x = addemplyeesearch.getText().charAt(addemplyeesearch.getText().length() - 1);
            addemplyeesearch.setText(addemplyeesearch.getText().substring(0,addemplyeesearch.getText().length() - 1));
            addemplyeesearch.setText(addemplyeesearch.getText()+x);
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
            disableNodes();

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

        if(employee.getImage() == null || employee.getImage().isEmpty()){
            addemplyeimageview.setImage(null);
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
        newaddempsepc.getSelectionModel().clearSelection();
        addemployedep.setText("");
        addemplyeimageview.setImage(null);

        enablenodes();

    }
    @FXML void del() throws SQLException {
        connect = DataBaseConnection.getconncetion();
        ps = connect.prepareStatement("DELETE FROM employe WHERE id = ?");
        ps.setInt(1,Integer.parseInt(addemplyeeid.getText()));
        Alert a  = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("CONFIRMATION");
        a.setHeaderText(null);
        a.setContentText("Are You Sure You Want To Delete " + addemplyeename.getText());
        Optional<ButtonType> ans=a.showAndWait();
        if(ans.get() == ButtonType.OK){
            ps.executeUpdate();
            System.out.println(ps);
            filteredList = new FilteredList<>(getEmployeesObservableListFromDatabase());
            displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
            if(!addemplyeesearch.getText().isEmpty()){
                char x = addemplyeesearch.getText().charAt(addemplyeesearch.getText().length() - 1);
                addemplyeesearch.setText(addemplyeesearch.getText().substring(0,addemplyeesearch.getText().length() - 1));
                addemplyeesearch.setText(addemplyeesearch.getText()+x);
            }
            Alert x  = new Alert(Alert.AlertType.INFORMATION);
            x.setTitle("INFORMATION");
            x.setHeaderText(null);
            x.setContentText(" Deleted " + addemplyeename.getText());
            x.showAndWait();
            clr();
            dataonchart();
            displaymaincar();

        }


    }
    @FXML
    public void addEmployee() throws SQLException {
        java.util.Date date = new java.util.Date();
        java.sql.Date currentdate = new Date(date.getTime());
        if(
                addemplyeename.getText().isEmpty()||
                addemplyeeage.getText().isEmpty() ||
                addemplye_salary.getText().isEmpty()||
                addemplyeeexperience.getText().isEmpty()||
                addemplyeeexperience.getText().isEmpty() ||
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
            if(addemplyeimageview.getImage() != null)
            {
                String sql = "INSERT INTO employe (id, employename, age, department, specialition , datemember, expyears, rating, image, ratetimes, salary, active) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, 'True')";
                ps = connect.prepareStatement(sql);
                ps.setString(1,addemplyeename.getText());
                ps.setInt(2,Integer.parseInt(addemplyeeage.getText()));
                ps.setString(3, Admin.div);
                ps.setString(4,newaddempsepc.getValue());
                ps.setDate(5,currentdate);
                ps.setInt(6, Integer.parseInt(addemplyeeexperience.getText()));
                ps.setInt(7,0);
                ps.setString(8,addemplyeimageview.getImage().getUrl());
                ps.setDouble(9, Double.parseDouble(addemplye_salary.getText()));
            }
            else {
                String sql = "INSERT INTO employe (id, employename, age, department, specialition , datemember, expyears, rating,  ratetimes, salary, active) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, 0, ?, 'True')";
                ps = connect.prepareStatement(sql);
                ps.setString(1,addemplyeename.getText());
                ps.setInt(2,Integer.parseInt(addemplyeeage.getText()));
                ps.setString(3, Admin.div);
                ps.setString(4,newaddempsepc.getValue());
                ps.setDate(5,currentdate);
                ps.setInt(6, Integer.parseInt(addemplyeeexperience.getText()));
                ps.setInt(7,0);
                ps.setDouble(8, Double.parseDouble(addemplye_salary.getText()));
            }

            ps.executeUpdate();
            System.out.println(ps);

            displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
            //update filtered list to match the change
            filteredList = new FilteredList<>(getEmployeesObservableListFromDatabase());
            //rest the search result
            if(!addemplyeesearch.getText().isEmpty()){
                char x = addemplyeesearch.getText().charAt(addemplyeesearch.getText().length() - 1);
                addemplyeesearch.setText(addemplyeesearch.getText().substring(0,addemplyeesearch.getText().length() - 1));
                addemplyeesearch.setText(addemplyeesearch.getText()+x);
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("INFORMATION");
            a.setHeaderText(null);
            a.setContentText("Successfully Added!");
            a.showAndWait();
            dataonchart();
            displaymaincar();





        }
    }
    @FXML
    public void disableNodes(){
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
    @FXML
    void exportExcel(ActionEvent event) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");
        for(int i = 0 ; i < 10 ; i++){
            sheet.setColumnWidth(i,7000);
        }
        Row header  = sheet.createRow(0);
        header.setHeight((short) 400);

        //head cells styles
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setVerticalAlignment(VerticalAlignment.CENTER);


        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(true);
        font.setFontHeight(10);
        font.setFontName("Arial");
        style.setFont(font);
        CellStyle style1 = workbook.createCellStyle();
        style1.setAlignment(HorizontalAlignment.CENTER);
        //col 1
        org.apache.poi.ss.usermodel.Cell cell1 = header.createCell(0);
        cell1.setCellStyle(style);
        cell1.setCellValue("ID");

        //col 2
        org.apache.poi.ss.usermodel.Cell cell2 = header.createCell(1);
        cell2.setCellStyle(style);
        cell2.setCellValue("Name");
        //col 1
        org.apache.poi.ss.usermodel.Cell cell3 = header.createCell(2);
        cell3.setCellStyle(style);
        cell3.setCellValue("Age");
        //col 1
        org.apache.poi.ss.usermodel.Cell cell4 = header.createCell(3);
        cell4.setCellStyle(style);
        cell4.setCellValue("Department");
        //col 5
        org.apache.poi.ss.usermodel.Cell cell5 = header.createCell(4);
        cell5.setCellStyle(style);
        cell5.setCellValue("Specialization");
        //col 6
        org.apache.poi.ss.usermodel.Cell cell6 = header.createCell(5);
        cell6.setCellStyle(style);
        cell6.setCellValue("Date Member");
        //col 7
        org.apache.poi.ss.usermodel.Cell cell7 = header.createCell(6);
        cell7.setCellStyle(style);
        cell7.setCellValue("Experience");
        //col 8
        org.apache.poi.ss.usermodel.Cell cell8 = header.createCell(7);
        cell8.setCellStyle(style);
        cell8.setCellValue("Rating");
        //col 9
        org.apache.poi.ss.usermodel.Cell cell9 = header.createCell(8);
        cell9.setCellStyle(style);
        cell9.setCellValue("Salary");
        //col 10
        Cell cell10 = header.createCell(9);
        cell10.setCellStyle(style);
        cell10.setCellValue("Active");

        //add employees data
        ObservableList<Employee> employees  = addemployeetable.getItems();
        for(int i = 0 ; i < employees.size(); i++){
            Row row  = sheet.createRow(i + 1);
            //get id data
            org.apache.poi.ss.usermodel.Cell cellid = row.createCell(0);
            cellid.setCellValue(employees.get(i).getId());
            cellid.setCellStyle(style1);
            //get name data
            org.apache.poi.ss.usermodel.Cell cellname = row.createCell(1);
            cellname.setCellValue(employees.get(i).getName());
            cellname.setCellStyle(style1);
            //get age data
            org.apache.poi.ss.usermodel.Cell cellage = row.createCell(2);
            cellage.setCellValue(employees.get(i).getAge());
            cellage.setCellStyle(style1);
            //get dep data
            org.apache.poi.ss.usermodel.Cell celldep = row.createCell(3);
            celldep.setCellValue(employees.get(i).getDiv());
            celldep.setCellStyle(style1);
            //get spec data
            org.apache.poi.ss.usermodel.Cell cellspec = row.createCell(4);
            cellspec.setCellValue(employees.get(i).getSpecialition());
            cellspec.setCellStyle(style1);
            //get datemem data
            org.apache.poi.ss.usermodel.Cell celldatemem = row.createCell(5);
            celldatemem.setCellValue(employees.get(i).getDatemem().toString());
            celldatemem.setCellStyle(style1);
            //get experience data
            org.apache.poi.ss.usermodel.Cell cellexp = row.createCell(6);
            cellexp.setCellValue(employees.get(i).getExyears());
            cellexp.setCellStyle(style1);
            // get rating data
            org.apache.poi.ss.usermodel.Cell cellrating = row.createCell(7);
            cellrating.setCellValue(employees.get(i).getRating());
            cellrating.setCellStyle(style1);
            // get salary data
            org.apache.poi.ss.usermodel.Cell cellsal = row.createCell(8);
            cellsal.setCellValue(employees.get(i).getSalary());
            cellsal.setCellStyle(style1);
            // get active data
            org.apache.poi.ss.usermodel.Cell cellactive = row.createCell(9);
            cellactive.setCellValue(employees.get(i).getActive());
            cellactive.setCellStyle(style1);


    }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel File");
        fileChooser.setInitialFileName("employeemangmentapp.xlsx");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

        // Show save file dialog
        File file = fileChooser.showSaveDialog((Stage) addemployeetable.getScene().getWindow() );
        if(file == null) return;
        FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath());
        workbook.write(outputStream);
        workbook.close();
    }
    File selectedFile;
    int count = 0;
    double prog = 0.0;
    boolean over = false;
    @FXML
    void addFromExcel(ActionEvent event) throws IOException, InvalidFormatException, SQLException {


        FileChooser fileChooser = new FileChooser();

        // Set the extension filter to only allow Excel files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);



        // Show the file chooser dialog
        selectedFile = fileChooser.showOpenDialog(exportexcelbtn.getScene().getWindow());
        loading.setVisible(true);

        if (selectedFile != null) {
            //run thread
            addexcelthred x = new addexcelthred();
            x.start();


        }

        displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
        ontypesearchup();
        if(!addemplyeesearch.getText().isEmpty()){
            char x = addemplyeesearch.getText().charAt(addemplyeesearch.getText().length() - 1);
            addemplyeesearch.setText(addemplyeesearch.getText().substring(0,addemplyeesearch.getText().length() - 1));
            addemplyeesearch.setText(addemplyeesearch.getText()+x);
        }




    }
    class addexcelthred extends Thread {
        int excelrows;
        int cuurentrow;
        @Override
        public void run(){
            try {
                Connection connection = DataBaseConnection.getconncetion();
                PreparedStatement ps ;
                Sheet sheet;
                // Read the selected Excel file
                FileInputStream fis = new FileInputStream(selectedFile);
                Workbook workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                excelrows = sheet.getLastRowNum() + 1;
                for(Row row : sheet){
                    //skip first row
                    if(row.getRowNum() == 0){
                        continue;
                    }
                    cuurentrow = row.getRowNum() +1;
                    ps = connection.prepareStatement("INSERT INTO employe (id, employename, age, department, specialition , datemember, expyears, rating,  salary, active) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    ps.setInt(1, (int) row.getCell(0).getNumericCellValue());
                    ps.setString(2,row.getCell(1).getStringCellValue());
                    ps.setInt(3, (int) row.getCell(2).getNumericCellValue());
                    //dep
                    ps.setString(4,row.getCell(3).getStringCellValue());
                    //spec
                    ps.setString(5,row.getCell(4).getStringCellValue());
                    //datemem
                    ps.setDate(6, Date.valueOf(row.getCell(5).getStringCellValue()));
                    //exyears
                    ps.setInt(7, (int) row.getCell(6).getNumericCellValue());
                    //rating
                    ps.setDouble(8,row.getCell(7).getNumericCellValue());
                    //salary
                    ps.setInt(9, (int) row.getCell(8).getNumericCellValue());
                    //active
                    ps.setString(10,row.getCell(9).getStringCellValue());
                    //check if there is an employe with the same id
                    PreparedStatement pscheck = connection.prepareStatement("SELECT * FROM employe WHERE id = ?");
                    pscheck.setInt(1, (int) row.getCell(0).getNumericCellValue());
                    ResultSet res = pscheck.executeQuery();
                    // if there is an employe with same id we skip
                    if(res.next()){
                        progress.setProgress(prog = (double) cuurentrow / excelrows);
                        continue;
                    }
                    progress.setProgress(prog = (double) cuurentrow / excelrows);
                    System.out.println(ps);
                    ps.executeUpdate();
                    count++;


                }
                workbook.close();
                Platform.runLater(()->{
                    loading.setVisible(false);
                    try {
                        dataonchart();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        displaymaincar();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    progress.setProgress(0);

                    try {
                        showemployessonratingtable(getEmployeesObservableListFromDatabase());
                        displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("INFORMATION");

                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added " + count + " Employees" + ", Employees With Duplicated ids  are skipped");
                    alert.showAndWait();
                    count = 0;                });



            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //--------------------------------------this part is for Rating page---------------------------------------------------------------
    public void showemployessonratingtable(ObservableList<Employee> listtoshow) throws SQLException {

        //map tables cell
        rating_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        rating_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        rating_col_dep.setCellValueFactory(new PropertyValueFactory<>("div"));
        rating_col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        rating_col_ratetimes.setCellValueFactory(new PropertyValueFactory<>("ratetime"));
        rating_col_active.setCellValueFactory(new PropertyValueFactory<>("active"));
        ratingtable.setItems(listtoshow);
    }
    @FXML
    private void addemployeonselectratingtable(){
        if(ratingtable.getSelectionModel().isEmpty()){
            return;
        }
        Employee emp = ratingtable.getSelectionModel().getSelectedItem();
        ratingid.setText(emp.getId().toString());
        ratingname.setText(emp.getName());
        ratingrating.setText(emp.getRating().toString());
        ratingactive.getSelectionModel().select(emp.getActive());
    }
    @FXML
    private void apply() throws SQLException {
    if(ratingid.getText().isEmpty()){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("ERROR");
        a.setHeaderText(null);
        a.setContentText("Please Select An Employee");
        a.showAndWait();
    }
    connect = DataBaseConnection.getconncetion();
    ps = connect.prepareStatement("UPDATE employe SET active = ? WHERE id = ? ");
    ps.setString(1,ratingactive.getValue());
    ps.setInt(2, Integer.parseInt(ratingid.getText()));
    ps.executeUpdate();
    // insert rating in ratings tables
    ps = connect.prepareStatement("INSERT INTO ratings (employeeid , rating) VALUES (? , ?)");
    ps.setInt(1, Integer.parseInt(ratingid.getText()));
    ps.setDouble(2, Double.parseDouble(ratingshow.getText()));
    ps.executeUpdate();
    // get avg rating using a sql query from rating table
    ps = connect.prepareStatement("SELECT employeeid , AVG(rating) AS averagerating FROM ratings WHERE employeeid = ? ");
    ps.setInt(1, Integer.parseInt(ratingid.getText()));
    queryres = ps.executeQuery();
    queryres.next();
    Double avgrate = queryres.getDouble("averagerating");
    ps = connect.prepareStatement("SELECT * FROM employe WHERE id = ?");
    ps.setInt(1, Integer.parseInt(ratingid.getText()));
    queryres = ps.executeQuery();
    queryres.next();
    int ratetimes = queryres.getInt("ratetimes");

    //finally insert it into employe rating colum
        System.out.println(avgrate);
    ps = connect.prepareStatement("UPDATE employe SET rating = ? ,  ratetimes = ? WHERE id = ? ");
    ps.setDouble(1,avgrate);
    ps.setInt(2,++ratetimes);
    ps.setInt(3, Integer.parseInt(ratingid.getText()));
    ps.executeUpdate();


    showemployessonratingtable(getEmployeesObservableListFromDatabase());
    displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
    displaymaincar();
    ontypesearchup();
        if(!addemplyeesearch.getText().isEmpty()){
            char x = addemplyeesearch.getText().charAt(addemplyeesearch.getText().length() - 1);
            addemplyeesearch.setText(addemplyeesearch.getText().substring(0,addemplyeesearch.getText().length() - 1));
            addemplyeesearch.setText(addemplyeesearch.getText()+x);
        }
    }
//----------------------------------------------Home--------------------------------------------------------------------------
    @FXML
    private void dataonchart() throws SQLException {
        homecahrt.getData().clear();
        homecahrt1.getData().clear();
        homechart3.getData().clear();


        connect = DataBaseConnection.getconncetion();
            //SELECT datemember, COUNT(id) FROM employe GROUP BY datemember ORDER BY datemember ASC LIMIT 7;
            //this query display the date and the total employes that joined and this date
            //example
            //datemember 	COUNT(id)
            //2024-04-08 	135
            //2024-04-09 	1
            //2024-04-10 	2
            //2024-04-11 	1
            //chart
            ps = connect.prepareStatement("SELECT datemember, COUNT(id) FROM employe WHERE department = ? GROUP BY datemember ORDER BY datemember ASC LIMIT 10; ");
            ps.setString(1,Admin.div);
            XYChart.Series chart1 = new XYChart.Series<String,Integer>();
            queryres = ps.executeQuery();
            while (queryres.next()){
                chart1.getData().add(new XYChart.Data(queryres.getString(1),queryres.getInt(2)));
            }

            //chart1
            ps = connect.prepareStatement("SELECT specialition , AVG(salary) FROM employe WHERE department = ? GROUP BY specialition");
            ps.setString(1,Admin.div);
            XYChart.Series chart2 = new XYChart.Series<String,Number>();
            queryres = ps.executeQuery();
            while (queryres.next()){
                chart2.getData().add(new XYChart.Data(queryres.getString(1),queryres.getInt(2)));
            }
            //chart3
            XYChart.Series chart3 = new XYChart.Series<String,Integer>();
            ps = connect.prepareStatement("SELECT specialition , COUNT(id) AS empnumber FROM employe WHERE department = ? GROUP BY specialition");
            ps.setString(1,Admin.div);
            queryres = ps.executeQuery();
            while (queryres.next()){
                chart3.getData().add(new XYChart.Data<>(queryres.getString(1),queryres.getInt(2)));
            }






                homecahrt.getData().add(chart1);
                homecahrt1.getData().add(chart2);
                homechart3.getData().add(chart3);

            if( homecombo.getSelectionModel().getSelectedItem() == "Employes By Their joining Date"){
                homecahrt.setVisible(true);
                homecahrt1.setVisible(false);
                homechart3.setVisible(false);

            }
            else if (homecombo.getSelectionModel().getSelectedItem() == "AVG Salary BY Specialization") {
                homecahrt.setVisible(false);
                homecahrt1.setVisible(true);
                homechart3.setVisible(false);
            }
            else {
                homecahrt.setVisible(false);
                homecahrt1.setVisible(false);
                homechart3.setVisible(true);
            }



    }
    private void displaymaincar() throws SQLException {


        connect = DataBaseConnection.getconncetion();
        ps = connect.prepareStatement("SELECT COUNT(id) AS total FROM employe WHERE department = ?");
        ps.setString(1,Admin.div);
        queryres = ps.executeQuery();
        queryres.next();
        hometotalemployee.setText(String.valueOf(queryres.getInt("total")));
        ps = connect.prepareStatement("SELECT COUNT(id) AS totalactive FROM employe WHERE active = 'True' and  department = ?");
        ps.setString(1,Admin.div);
        queryres = ps.executeQuery();
        queryres.next();
        int x =queryres.getInt("totalactive");
        hometotalpresnts.setText(String.valueOf(queryres.getInt("totalactive")));
        hometotalinactive.setText(String.valueOf(Integer.parseInt(hometotalemployee.getText()) - x));
        homecombo.setItems(new ComboBoxLists().getChartlist());
        homecombo.getSelectionModel().select("Employes By Their joining Date");



    }
    @FXML
    private void minimze(){
      Stage stage = (Stage)homecahrt.getScene().getWindow();
      stage.setIconified(true);

    }
    @FXML
    void makefullscreen(MouseEvent event) {
        Stage st = (Stage) fullscreen.getScene().getWindow();
        st.setFullScreen(true);
    }
    @FXML
    void changeimage(MouseEvent event) throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please Choose An Image");
        File file = fileChooser.showOpenDialog(addemplyeesearch.getScene().getWindow());
        if (file == null){
            return;
        }
        Connection con = DataBaseConnection.getconncetion();
        PreparedStatement ps = con.prepareStatement("UPDATE admin SET image = ? WHERE username = ?");
        ps.setString(1,file.toURI().toString());
        ps.setString(2,Admin.adminusername);
        ps.executeUpdate();
        Image image = new Image(file.toURI().toString());

        userimage.setImage(image);
        Rectangle clip = new Rectangle(115, 115);
        clip.setArcWidth(170); // Adjust the arc width to control the border radius
        clip.setArcHeight(170); // Adjust the arc height to control the border radius
        clip.setFill(Color.WHITE);

        // Apply the mask to the ImageView
        userimage.setClip(clip);

        // Apply blend mode to remove the corners
        userimage.setBlendMode(BlendMode.SRC_ATOP);



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (Admin.image != null){
            Image img = new Image(Admin.image);
            userimage.setFitWidth(198);
            userimage.setFitHeight(118);

            // Create a rectangle to serve as the mask
            Rectangle clip = new Rectangle(115, 115);
            clip.setArcWidth(170); // Adjust the arc width to control the border radius
            clip.setArcHeight(170); // Adjust the arc height to control the border radius
            clip.setFill(Color.WHITE);

            // Apply the mask to the ImageView
            userimage.setClip(clip);

            // Apply blend mode to remove the corners
            userimage.setBlendMode(BlendMode.SRC_ATOP);

            System.out.println(Admin.image);
            Image image = new Image(Admin.image);
            userimage.setImage(image);
        }



        try {
            ontypesearchup();
            showemployessonratingtable(getEmployeesObservableListFromDatabase());
            displaymaincar();
            dataonchart();
            displayEmployessdataonEmployessTable(getEmployeesObservableListFromDatabase());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ratingactive.setItems(new ComboBoxLists().getVactionlist());
        rateslider.valueProperty().addListener((obser,oldval,newval) -> {
            ratingshow.setText(newval.toString());
        });

        newaddempsepc.setItems(new ComboBoxLists().getSpecialitionlist(Admin.div));
        ObservableList<String> combolist = new ComboBoxLists().getSpecialitionlist(Admin.div);
        combolist.add("All");
        seacrhcombo.setItems(combolist);
        seacrhcombo.getSelectionModel().selectLast();
        homebtn.setStyle("-fx-background-color:#594e9c");
        usernamelabel.setText(Admin.adminname);
        usernamelabel1.setText(Admin.div);


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





