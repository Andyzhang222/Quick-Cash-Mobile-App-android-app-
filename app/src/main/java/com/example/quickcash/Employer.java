package com.example.quickcash;

public class Employer extends User{

    public String identity;

    public Employer() {
    }

    public Employer(String email, String creditCard, String password, String identity) {
        super(email, creditCard, password);
        this.identity = identity;
    }
}
