package com.example.quickcash;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Job {

    private String jobType, description,date, duration, place, urgency;
    private int salary;


    public Job() {

    }

    public Job(String jobType, String description, String date, String duration, String place, String urgency, int salary) {
        this.jobType = jobType;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.place = place;
        this.urgency = urgency;
        this.salary = salary;
    }

    public String getJobType() {
        return jobType;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getPlace() {
        return place;
    }

    public String getUrgency() {
        return urgency;
    }

    public int getSalary() {
        return salary;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }


    public boolean validateJobType(String jobType){
        return jobType != "" && !jobType.trim().isEmpty();
    }


    public boolean validateDescription(String description){
        int minLength = 10;
        int maxLength = 1000;
        return description != null && description.length() >= minLength && description.length() <= maxLength;
    }


    public boolean validateDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    public boolean validateDuration(String duration){
        int minDuration = 1;
        int maxDuration = 24;
        try {
            int hours = Integer.parseInt(duration);
            return hours >= minDuration && hours <= maxDuration;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateUrgency(String urgency){
        String yes, no;
        yes = "YES";
        no = "NO";
        if (urgency.equals("YES") || urgency.equals("NO")){
            return true;
        }
        return false;
    }

    public boolean validateSalary(int salary){
        int minSalary = 0;
        int maxSalary = 1000000;
        return salary >= minSalary && salary <= maxSalary;
    }
}
