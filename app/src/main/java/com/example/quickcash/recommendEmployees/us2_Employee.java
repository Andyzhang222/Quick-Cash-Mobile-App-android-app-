package com.example.quickcash.recommendEmployees;

/**
 * This file is for a employee class for user2 story
 * Editor: Ziyue Wang, Guanxiang Wang
 */
public class us2_Employee {
    private String name;
    private double star;
    private String employeeTitle;

    private String emial;

    public us2_Employee (String name, double star,String title, String UID) {
        this.name = name;
        this.star = star;
        employeeTitle = title;
        this.emial = UID;
    }

    public String getName() {
        return name;
    }

    public double getStar() {
        return star;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public void setEmployeeTitle(String title) {
        this.employeeTitle = title;
    }

    public String getEmployeeTitle () {
        return employeeTitle;
    }

    public String getEmial() { return emial;}
}
