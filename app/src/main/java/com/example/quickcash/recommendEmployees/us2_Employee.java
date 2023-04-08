package com.example.quickcash.recommendEmployees;

public class us2_Employee {
    String name;
    double star;
    String employeeTitle;

    public us2_Employee (String name, double star,String title) {
        this.name = name;
        this.star = star;
        employeeTitle = title;
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
}
