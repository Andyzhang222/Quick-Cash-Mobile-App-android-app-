package com.example.quickcash;

public class Employer extends User{

    public String name, identity, companyName;

    public Employer() {
    }

    public Employer(String name, String email, String creditCard, String password, String identity, String companyName) {
        super(email, creditCard, password);
        this.name = name;
        this.identity = identity;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public String getIdentity() {
        return identity;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
