package com.example.quickcash;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JobListAdapterTest {

    List<Job> jobList;
    Filter filter = new Filter();

    @Test
    public void jobFilterTest () {
        List<Job> filteredList;

        jobList = new ArrayList<>();

        jobList.add(new Job("aaaadddd"));
        jobList.add(new Job("aacc"));
        jobList.add(new Job("word"));
        jobList.add(new Job("salary no"));
        jobList.add(new Job("free"));
        jobList.add(new Job("oops"));
        jobList.add(new Job("ijijijddslsld"));
        jobList.add(new Job("aaaaapotato"));

        filteredList = Filter.filter("a", jobList);

        assertEquals(4, filteredList.size());



    }

}