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
    public void testCreateEmployee1() {
        employee = new Employee("Jack",90,"zh447118@dal.ca","1111111111111111",
                "Aq1234567.","employee","free",888);

        assertFalse(employee.equals(null));
    }

    @Test
    public void testCreateEmployee2() {
        employee = new Employee("Jack",90,"zh447118@dal.ca","1111111111111111",
                "Aq1234567.","employee","free",888);

        assertEquals("Jack",employee.getName());
        assertEquals(90,employee.getAge());
        assertEquals("zh447118@dal.ca",employee.getEmail());
        assertEquals("1111111111111111",employee.getCreditCard());
        assertEquals("Aq1234567.",employee.getPassword());
        assertEquals("employee",employee.getIdentity());
        assertEquals("free",employee.getCurrentStatus());
        assertEquals(888,employee.getTotalIncome());
    }

    @Test
    public void testCompareEmployee(){

        employee = new Employee("Jack",90,"zh447118@dal.ca","1111111111111111",
                "Aq1234567.","employee","free",888);

        Employee employer1 = new Employee("Mike",88,"zh447118@dal.ca","1111111111111111",
                "Aq1234567.","employee","free",888);


        assertFalse(employee.equals(employer1));
    }

    @Test
    public void testCreateEmployer1() {
        employer = new Employer("Bob","zh447118@dal.ca","1111111111111111",
                "Aq1234567.","employer","Apple");

        assertFalse(employer.equals(null));
    }

    @Test
    public void testCreateEmployer2() {
        employer = new Employer("Bob","zh447118@dal.ca","1111111111111111",
                "Aq1234567.","employer","Apple");

        assertEquals("Bob",employer.getName());
        assertEquals("zh447118@dal.ca",employer.getEmail());
        assertEquals("1111111111111111",employer.getCreditCard());
        assertEquals("Aq1234567.",employer.getPassword());
        assertEquals("employer",employer.getIdentity());
        assertEquals("Apple",employer.getCompanyName());
    }

    @Test
    public void testCompareEmployer() {
        employer = new Employer("Bob","zh447118@dal.ca","1111111111111111",
                "Aq1234567.","employer","Apple");

        Employer employer1 = new Employer("Marry","zh4125118@dal.ca","1111111111111111",
                "Aq1asdsa4567.","employer","Dal");

        assertFalse(employer.equals(employer1));
    }

}
