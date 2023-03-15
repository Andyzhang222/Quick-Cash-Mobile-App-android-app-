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

    @Test
    public void testDescriptionIsCorrect(){
        job = new Job("",
                "As a truck transporter, you'll be responsible for safely and efficiently transporting goods," +
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential.",
                "",
                "",
                "",
                "",
                0
        );

        assertTrue(job.validateDescription(job.getDescription()));

    }

    @Test
    public void testDescriptionIsNotCorrect(){
        job = new Job("",
                "As a truck transporter, you'll be responsible for safely and efficiently transporting goods," +
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential."+
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential."+
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential."+
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential."+
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential."+
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential."+
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential."+
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential.",
                "",
                "",
                "",
                "",
                0
        );

        assertFalse(job.validateDescription(job.getDescription()));

    }

    @Test
    public void testDateIsCorrect(){
        job = new Job("",
                "",
                "2023-01-20",
                "",
                "",
                "",
                0
        );

        assertTrue(job.validateDate(job.getDate()));
    }

    @Test
    public void testDateIsNotCorrect(){
        job = new Job("",
                "",
                "20-2020-20",
                "",
                "",
                "",
                0
        );

        assertFalse(job.validateDate(job.getDate()));
    }
    @Test
    public void testDurationIsCorrect(){
        job = new Job("",
                "",
                "",
                "23",
                "",
                "",
                0
        );

        assertTrue(job.validateDuration(job.getDuration()));
    }
    @Test
    public void testDurationIsNotCorrect(){
        job = new Job("",
                "",
                "",
                "25",
                "",
                "",
                0
        );

        assertFalse(job.validateDuration(job.getDuration()));
    }

    @Test
    public void testUrgencyIsCorrect(){
        job = new Job("",
                "",
                "",
                "",
                "",
                "YES",
                0
        );

        Job job1 = new Job("",
                "",
                "",
                "",
                "",
                "NO",
                0
        );

        assertTrue(job.validateUrgency(job.getUrgency()));
        assertTrue(job.validateUrgency(job1.getUrgency()));
    }

    @Test
    public void testUrgencyIsNotCorrect(){
        job = new Job("",
                "",
                "",
                "",
                "",
                "AAA",
                0
        );

        assertFalse(job.validateUrgency(job.getUrgency()));
    }

}