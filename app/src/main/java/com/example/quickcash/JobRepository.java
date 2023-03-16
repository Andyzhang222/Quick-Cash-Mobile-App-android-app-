package com.example.quickcash;

import java.util.ArrayList;
import java.util.List;

public class JobRepository implements JobInterface{
    private List<Job> jobList = new ArrayList<>();

    public JobRepository() {
    }

    public JobRepository(List<Job> jobList) {
        this.jobList = jobList;
    }

    @Override
    public void addJob(Job job) {
        jobList.add(job);
    }

    /*
        如果employerId匹配，则存入过滤过的Jobs里面
     */
    @Override
    public List<Job> getJobsByEmployer(String employerId) {
        List<Job> filteredJobs = new ArrayList<>();
        for (Job job : jobList) {
            if (job.getEmployerId().equals(employerId)) {
                filteredJobs.add(job);
            }
        }
        return filteredJobs;
    }
}
