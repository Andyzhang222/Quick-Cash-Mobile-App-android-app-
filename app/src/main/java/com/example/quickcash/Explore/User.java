package com.example.quickcash.Explore;

public class User {
    private String uid;
    private String creditCard;
    private String email;
    private Double incomeEarned;
    private String password;
    private String preference;
    private Double rating;
    private String userType;
    private String username;

    public User() {
    }

    public User(String creditCard, String email, Double incomeEarned, String password, String preference, Double rating, String userType, String username) {
        this.creditCard = creditCard;
        this.email = email;
        this.incomeEarned = incomeEarned;
        this.password = password;
        this.preference = preference;
        this.rating = rating;
        this.userType = userType;
        this.username = username;
    }

    // Getter and Setter methods
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getIncomeEarned() {
        return incomeEarned;
    }

    public void setIncomeEarned(Double incomeEarned) {
        this.incomeEarned = incomeEarned;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
