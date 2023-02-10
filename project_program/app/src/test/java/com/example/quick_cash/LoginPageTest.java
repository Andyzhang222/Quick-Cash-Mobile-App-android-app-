package com.example.quick_cash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginPageTest extends TestCase {
    static LoginPage loginPageActivity;

    @BeforeClass
    public static void setup() {
        loginPageActivity = new LoginPage();
    }

    @AfterClass
    public  void tearDown() {
        System.gc();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkIfEmailIsValid() {
        assertTrue(loginPageActivity.isValidEmailAddress("abc123@dal.ca"));
    }
}