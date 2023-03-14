package com.example.quickcash;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubmitJobFieldTest extends TestCase {
    Job job;

    @Before
    public void setup() {

    }

    @AfterClass
    public void tearDown() {
        System.gc();
    }

    @Test
    public void testCreateJobSuccessfully() {
        job = new Job("babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "University Street",
                "YES",
                30
                );


        assertFalse(job.equals(null));
    }

    @Test
    public void testGetJobFiledSuccessfully() {
        job = new Job("Babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "6225 University Street",
                "YES",
                30
        );

        assertEquals("Babysitter",job.getJobType());
        assertEquals("Babysitting and playing with baby",job.getDescription());
        assertEquals("2023-05-20",job.getDate());
        assertEquals("More than two hours",job.getDuration());
        assertEquals("6225 University Street",job.getPlace());
        assertEquals("YES",job.getUrgency());
        assertEquals(30,job.getSalary());
    }

    @Test
    public void testCompareJob(){
        job = new Job("Babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "6225 University Street",
                "YES",
                30
        );

        Job job1 = new Job("Truck Drivers",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "6225 University Street",
                "YES",
                30
        );

        assertFalse(job1.equals(job));
    }

    @Test
    public void testJobIsNotCorrect(){
        job = new Job("",
                "",
                "",
                "",
                "",
                "",
                0
        );

        assertFalse(job.validateJobType(job.getJobType()));
    }

    @Test
    public void testJobIsCorrect(){
        job = new Job("Driver",
                "",
                "",
                "",
                "",
                "",
                0
        );

        assertTrue(job.validateJobType(job.getJobType()));
    }


}