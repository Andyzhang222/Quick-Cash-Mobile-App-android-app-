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
}
