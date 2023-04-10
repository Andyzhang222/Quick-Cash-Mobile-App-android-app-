package com.example.quickcash.JobEmployer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JobEmployer implements Serializable {

    private String employerId;
    private String jobType;
    private String description;
    private String date;
    private String duration;
    private String place;
    private String status;
    private Boolean urgency;
    private int salary;
    private String jobId;

    private String employeeID;


    public JobEmployer() {

    }

    public JobEmployer(String employerId, String jobType, String description, String date, String duration, String place, Boolean urgency, int salary, String status, String jobId,String JobEmployer) {
        this.employerId = employerId;
        this.jobType = jobType;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.place = place;
        this.urgency = urgency;
        this.salary = salary;
        this.status = status;
        this.jobId = jobId;
        this.employeeID =employerId;
    }

    public JobEmployer(String employerId, String jobType, String description, String date, String duration, String place, Boolean urgency, int salary, String status, String jobId) {
        this.employerId = employerId;
        this.jobType = jobType;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.place = place;
        this.urgency = urgency;
        this.salary = salary;
        this.status = status;
        this.jobId = jobId;
    }

    public JobEmployer(String jobType, String description, String date, String duration, String place, Boolean urgency, int salary, String status) {
        this.jobType = jobType;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.place = place;
        this.urgency = urgency;
        this.salary = salary;
        this.status = status;
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

    public Boolean getUrgency() {
        return urgency;
    }

    public int getSalary() {
        return salary;
    }

    public String getStatus() {
        return status;
    }


    public String getEmployeeID() {


        return employeeID;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public void setUrgency(Boolean urgency) {
        this.urgency = urgency;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public boolean validateJobType(String jobType){
        return (!jobType.equals("")) && (!jobType.trim().isEmpty());
    }


    public boolean validateDescription(String description){
        int minLength = 10;
        int maxLength = 1000;
        return description != null && description.length() >= minLength && description.length() <= maxLength;
    }


    public boolean validateDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
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

    public boolean validateSalary(int salary){
        int minSalary = 0;
        int maxSalary = 1000000;
        return salary >= minSalary && salary <= maxSalary;
    }

    public boolean validateJobStatus(String status){
        String str1 = "Open";
        String str2 = "Close";

        return status.equals(str1) || status.equals(str2);
    }

    public boolean validateEmployerId(String employerId) {
        if (employerId == null) {
            return false;
        }

        return employerId.length() < 8;
    }


}
