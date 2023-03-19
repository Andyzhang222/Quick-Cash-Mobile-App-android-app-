package com.example.quickcash;
/**
 * This activity is for filter the correct job that the employee search
 * Editor: Ziyue Wang, Guanxiang Wang
 */

import java.util.ArrayList;
import java.util.List;
//used the O of the SOLID principle
public class Filter {

    /**
     * Filter the job that the user want to see
     * @param text the text that user type
     * @param jobList1 the list of all jobs
     * @return the list that contain all the jobs that employees want
     */
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