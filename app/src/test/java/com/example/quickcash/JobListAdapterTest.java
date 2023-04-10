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

        jobList.add(new Job("aaaadddd","asdsa"));
        jobList.add(new Job("aacc","asdsa"));
        jobList.add(new Job("word","asdsa"));
        jobList.add(new Job("salary no","asdsa"));
        jobList.add(new Job("free","asdsa"));
        jobList.add(new Job("oops","asdsa"));
        jobList.add(new Job("ijijijddslsld","asdsa"));
        jobList.add(new Job("aaaaapotato","asdsa"));

        filteredList = Filter.filter("a", jobList);

        assertEquals(4, filteredList.size());



    }

}