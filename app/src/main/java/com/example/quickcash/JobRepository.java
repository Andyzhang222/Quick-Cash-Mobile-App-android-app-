package com.example.quickcash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JobRepository implements JobInterface, Serializable {
    private List<Job> jobList = new ArrayList<>();

    public JobRepository() {
    }

    public JobRepository(List<Job> jobList) {
        this.jobList = jobList;
    }

    public List<Job> getJobList(){
        return jobList;
    }

    @Override
    public void addJob(Job job) {
        jobList.add(job);
    }

    /*
        如果employerId匹配，则存入过滤过的Jobs里面
     */
    @Override
    public List<Job> getJobsByUserId(String userId) {
        List<Job> filteredJobs = new ArrayList<>();
        for (Job job : jobList) {
            if (job.getEmployerId().equals(userId)) {
                filteredJobs.add(job);
            }
        }
        return filteredJobs;
    }
}
