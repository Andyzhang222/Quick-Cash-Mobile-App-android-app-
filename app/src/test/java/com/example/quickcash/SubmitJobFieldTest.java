package com.example.quickcash;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubmitJobFieldTest extends TestCase {
    Employee employee;
    Employer employer;

    @Before
    public void setup() {

    }

    @AfterClass
    public void tearDown() {
        System.gc();
    }

    @Test
    public void testCreateEmployee() {
        employee = new Employee("Jack",90,"zh447118@dal.ca","1111111111111111",
                "Aq1234567.","employee","free",888);

        assertFalse(employee.equals(null));
    }

}
