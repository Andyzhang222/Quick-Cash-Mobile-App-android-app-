package com.example.quickcash;

public class Employee extends User{

    public String name, identity, currentStatus;
    int totalIncome, age;

    public Employee() {

    }

    public Employee(String name, int age, String email, String creditCard, String password, String identity, String currentStatus, int totalIncome) {
        super(email, creditCard, password);
        this.identity = identity;
        this.currentStatus = currentStatus;
        this.totalIncome = totalIncome;
        this.name = name;
        this.age = age;
    }

    public String getIdentity() {
        return identity;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }
}
