package com.example.employeemangmentapp;

import java.sql.Date;

public class Employee {
    private Integer id;
    private String div;
    private Date datemem;
    private Integer exyears;
    private Double rating;
    private Integer ratetime;
    String image;
    private String name;
    private Integer age;
    private double salary;
    String active;
    String specialition;

    public String getSpecialition() {
        return specialition;
    }

    public void setSpecialition(String specialition) {
        this.specialition = specialition;
    }



    public double getSalary() {
        return salary;
    }

    public String getActive() {
        return active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(Integer id, String name, Integer age, String div, Date datemem, Integer exyears, Double rating, String image, Integer ratetime , Double salary , String active , String specialition) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.div = div;
        this.datemem = datemem;
        this.exyears = exyears;
        this.rating = rating;
        this.ratetime = ratetime;
        this.image = image;
        this.salary = salary;
        this.active = active;
        this.specialition = specialition;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public Date getDatemem() {
        return datemem;
    }

    public void setDatemem(Date datemem) {
        this.datemem = datemem;
    }

    public Integer getExyears() {
        return exyears;
    }

    public void setExyears(Integer exyears) {
        this.exyears = exyears;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatetime() {
        return ratetime;
    }

    public void setRatetime(Integer ratetime) {
        this.ratetime = ratetime;
    }

}
