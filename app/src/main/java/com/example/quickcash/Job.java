package com.example.quickcash;

/**
 * This class is for create a job Class for employee page
 * Editor: Ziyue Wang, Guanxiang Wang
 */
public class Job {
    private String jobTitle;
    private String jobId;
    public Job(String jobTitle, String jobId){
        this.jobTitle = jobTitle;
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
