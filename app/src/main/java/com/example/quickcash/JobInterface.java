package com.example.quickcash;


import java.util.List;

public interface JobInterface {

    void addJob(Job job);
    List<Job> getJobsByUserId(String userId);
}
