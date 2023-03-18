package com.example.quickcash;

import java.util.ArrayList;
import java.util.List;
//used the O of the SOLID principle
public class Filter {
    public static List<Job> filter(String text, List<Job> jobList1) {
        List<Job> filteredJobList = new ArrayList<>();
        for (Job job : jobList1) {
            if (job.getJobTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredJobList.add(job);
            }
        }
        return filteredJobList;
    }
}