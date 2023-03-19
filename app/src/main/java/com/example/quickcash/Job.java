package com.example.quickcash;

/**
 * This class is for create a job Class for employee page
 * Editor: Ziyue Wang, Guanxiang Wang
 */
public class Job {
    private String jobTitle;
    public Job(String jobTitle){
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
