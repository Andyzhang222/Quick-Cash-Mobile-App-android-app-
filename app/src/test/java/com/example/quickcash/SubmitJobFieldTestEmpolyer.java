package com.example.quickcash;

import com.example.quickcash.JobEmployer.JobEmployer;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class SubmitJobFieldTestEmpolyer extends TestCase {
    JobEmployer jobEmployer;

    @Before
    public void setup() {

    }

    @AfterClass
    public void tearDown() {
        System.gc();
    }

    @Test
    public void testCreateJobSuccessfully() {
        jobEmployer = new JobEmployer("123456",
                "babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "University Street",
                false,
                30,
                "",
                "111"
                );


        assertFalse(jobEmployer.equals(null));
    }

    @Test
    public void testGetJobFiledSuccessfully() {
        jobEmployer = new JobEmployer("123456",
                "Babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "6225 University Street",
                false,
                30,
                "",
                "111"
        );

        assertEquals("123456", jobEmployer.getEmployerId());
        assertEquals("Babysitter", jobEmployer.getJobType());
        assertEquals("Babysitting and playing with baby", jobEmployer.getDescription());
        assertEquals("2023-05-20", jobEmployer.getDate());
        assertEquals("More than two hours", jobEmployer.getDuration());
        assertEquals("6225 University Street", jobEmployer.getPlace());
        assertFalse(jobEmployer.getUrgency());
        assertEquals(30, jobEmployer.getSalary());
    }

    @Test
    public void testCompareJob(){
        jobEmployer = new JobEmployer("123456",
                "Babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "6225 University Street",
                false,
                30,
                "",
                "111"
        );

        JobEmployer jobEmployer1 = new JobEmployer("123455",
                "Truck Drivers",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "6225 University Street",
                false,
                30,
                "",
                "111"
        );

        assertFalse(jobEmployer1.equals(jobEmployer));
    }

    @Test
    public void testJobIsNotCorrect(){
        jobEmployer = new JobEmployer("",
                "",
                "",
                "",
                "",
                "",
                false,
                0,
                "",
                "111"
        );

        assertFalse(jobEmployer.validateJobType(jobEmployer.getJobType()));
    }

    @Test
    public void testJobIsCorrect(){
        jobEmployer = new JobEmployer("",
                "Driver",
                "",
                "",
                "",
                "",
                false,
                0,
                "",
                "111"
        );

        assertTrue(jobEmployer.validateJobType(jobEmployer.getJobType()));
    }

    @Test
    public void testDescriptionIsCorrect(){
        jobEmployer = new JobEmployer("123456",
                "",
                "As a truck transporter, you'll be responsible for safely and efficiently transporting goods," +
                        " following delivery schedules, and communicating with dispatchers and customers. " +
                        "Strong knowledge of transportation regulations, safety standards, and good communication skills are essential.",
                "",
                "",
                "",
                false,
                0,
                "",
                "111"
        );

        assertTrue(jobEmployer.validateDescription(jobEmployer.getDescription()));

    }

    @Test
    public void testDescriptionIsNotCorrect(){
        jobEmployer = new JobEmployer("123456",
                "",
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
                false,
                0,
                "",
                "111"
        );

        assertFalse(jobEmployer.validateDescription(jobEmployer.getDescription()));

    }

    @Test
    public void testDateIsCorrect(){
        jobEmployer = new JobEmployer("123456",
                "",
                "",
                "2023-01-20",
                "",
                "",
                false,
                0,
                "",
                "111"
        );

        assertTrue(jobEmployer.validateDate(jobEmployer.getDate()));
    }

    @Test
    public void testDateIsNotCorrect(){
        jobEmployer = new JobEmployer("123456",
                "",
                "",
                "20-2020-20",
                "",
                "",
                false,
                0,
                "",
                "111"
        );

        assertFalse(jobEmployer.validateDate(jobEmployer.getDate()));
    }
    @Test
    public void testDurationIsCorrect(){
        jobEmployer = new JobEmployer("123456",
                "",
                "",
                "",
                "23",
                "",
                false,
                0,
                "",
                "111"
        );

        assertTrue(jobEmployer.validateDuration(jobEmployer.getDuration()));
    }
    @Test
    public void testDurationIsNotCorrect(){
        jobEmployer = new JobEmployer("123456",
                "",
                "",
                "",
                "25",
                "",
                false,
                0,
                "",
                "111"
        );

        assertFalse(jobEmployer.validateDuration(jobEmployer.getDuration()));
    }

    @Test
    public void testSalaryIsCorrect(){
        jobEmployer = new JobEmployer("123456",
                "",
                "",
                "",
                "",
                "",
                false,
                0,
                "",
                "111"
        );

        JobEmployer jobEmployer1 = new JobEmployer("123456",
                "",
                "",
                "",
                "",
                "",
                false,
                2500,
                "",
                "111"
        );

        assertTrue(jobEmployer.validateSalary(jobEmployer.getSalary()));
        assertTrue(jobEmployer1.validateSalary(jobEmployer1.getSalary()));
    }

    @Test
    public void testSalaryIsNotCorrect(){
        jobEmployer = new JobEmployer("123456",
                "",
                "",
                "",
                "",
                "",
                false,
                -1,
                "",
                "111"
        );

        JobEmployer jobEmployer1 = new JobEmployer("123456",
                "",
                "",
                "",
                "",
                "",
                false,
                99999999,
                "",
                "111"
        );

        assertFalse(jobEmployer.validateSalary(jobEmployer.getSalary()));
        assertFalse(jobEmployer1.validateSalary(jobEmployer1.getSalary()));
    }


    @Test
    public void testStatusIsCorrect(){
        jobEmployer = new JobEmployer("",
                "",
                "",
                "",
                "",
                "",
                false,
                -1,
                "Open",
                "111"
        );

        JobEmployer jobEmployer1 = new JobEmployer("123456",
                "",
                "",
                "",
                "",
                "",
                false,
                0,
                "Close",
                "111"
        );

        assertTrue(jobEmployer.validateJobStatus(jobEmployer.getStatus()));
        assertTrue(jobEmployer1.validateJobStatus(jobEmployer1.getStatus()));
    }
    @Test
    public void testStatusIsNotCorrect(){
        jobEmployer = new JobEmployer("",
                "",
                "",
                "",
                "",
                "",
                false,
                -1,
                "222",
                "111"
        );

        JobEmployer jobEmployer1 = new JobEmployer("123456",
                "",
                "",
                "",
                "",
                "",
                false,
                0,
                "Clasdose",
                "111"
        );

        assertFalse(jobEmployer.validateJobStatus(jobEmployer.getStatus()));
        assertFalse(jobEmployer1.validateJobStatus(jobEmployer1.getStatus()));
    }

}