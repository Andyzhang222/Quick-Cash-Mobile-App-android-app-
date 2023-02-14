package com.example.quick_cash;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class LoginPageTest{
    @Test
    public void addition_isCorrect() {
        assertEquals(3, 1+2);
    }

    // Password Length : Password should be of minimum 8 characters
    @Test
    public void checkPasswordLengthTest(){

        Validator v1 = new Validator("bigPigSmallDog","zh448118@dal.ca");
        Validator v2 = new Validator("pig","zh448118@dal.ca");
        Validator v3 = new Validator("Aa123321","zh448118@dal.ca");

        assertTrue(v1.checkPasswordLength());
        assertFalse(v2.checkPasswordLength());
        assertTrue(v3.checkPasswordLength());
    }


}