package com.example.employeemangmentapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComboBoxLists {
    ObservableList<String> Departmentslist = FXCollections.observableArrayList();
    ObservableList<String> Vactionlist = FXCollections.observableArrayList();
    ObservableList<String> CompyterScienceSpecialition = FXCollections.observableArrayList();
    ObservableList<String> CyberSecuritypecialition = FXCollections.observableArrayList();
    ObservableList<String> WebDevlopmentpecialition = FXCollections.observableArrayList();
    ObservableList<String> SoftwareDevpecialition = FXCollections.observableArrayList();
    ObservableList<String> Chartlist = FXCollections.observableArrayList();


    public ObservableList<String> getChartlist() {
        return Chartlist;
    }

    public ComboBoxLists() {
        //Departments
        Departmentslist.add("Computer Science");
        Departmentslist.add("Cyber Security");
        Departmentslist.add("Software Engineering");
        Departmentslist.add("Web Development");

        //vaction list
        Vactionlist.add("True");
        Vactionlist.add("On Vaction");
        //CompyterScienceSpecializations
        CompyterScienceSpecialition.add("Artificial Intelligence");
        CompyterScienceSpecialition.add("Machine Learning");
        CompyterScienceSpecialition.add("Data Science");
        //CyberSecirity
        CyberSecuritypecialition.add("Ethical Hacking");
        CyberSecuritypecialition.add("Network Security");
        CyberSecuritypecialition.add("Information Security");
        CyberSecuritypecialition.add("Management Cybersecurity");
        CyberSecuritypecialition.add("Analytics");
        CyberSecuritypecialition.add("Malware analyst");
        //Software Enginering
        SoftwareDevpecialition.add("Mobile App Development");
        SoftwareDevpecialition.add("Game Development");
        SoftwareDevpecialition.add("Embedded Systems Development");
        SoftwareDevpecialition.add("DevOps");
        //web Devlopment
        WebDevlopmentpecialition.add("Frontend Web Development");
        WebDevlopmentpecialition.add("Backend Web Development");
        WebDevlopmentpecialition.add("Full Stack Web Development");
        WebDevlopmentpecialition.add("Web Design");
        WebDevlopmentpecialition.add("Responsive Web Development");
        //chartlis
        Chartlist.add("Employes By Their joining Date");
        Chartlist.add("AVG Salary BY Specialization");
        Chartlist.add("Number of Employees In Each Specialization");



    }

    public ObservableList<String> getVactionlist() {
        return Vactionlist;
    }

    public ObservableList<String> getDepartmentslist() {
        return Departmentslist;
    }
    public ObservableList<String> getSpecialitionlist(String Department){
        if (Department.equals("Computer Science")){
            return CompyterScienceSpecialition;
        }
        else if (Department.equals("Cyber Security")){
            return CyberSecuritypecialition;
        }
        else if(Department.equals("Software Engineering")){
            return SoftwareDevpecialition;
        }

        else {
            return WebDevlopmentpecialition;
        }
    }
}
