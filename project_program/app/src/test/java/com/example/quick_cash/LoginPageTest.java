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

    @Test
    public void checkPasswordCaseTest(){
        Validator v1 = new Validator("a123456","zh448118@dal.ca"); // all lower cases
        Validator v2 = new Validator("A123456","zh448118@dal.ca"); // all upper cases
        Validator v3 = new Validator("Aa123456","zh448118@dal.ca"); // At lease one upper and one lower case
        Validator v4 = new Validator("","zh448118@dal.ca"); // Blank String

        assertFalse(v1.checkPasswordCase());
        assertFalse(v2.checkPasswordCase());
        assertTrue(v3.checkPasswordCase());
        assertFalse(v4.checkPasswordCase());

    }

    @Test
    public void checkPasswordSpecialCharTest(){
        Validator v1 = new Validator("password","zh448118@dal.ca"); // No Special Charater
        Validator v2 = new Validator("Java2blog@","zh448118@dal.ca"); // one Special Character
        Validator v3 = new Validator("Password#","zh448118@dal.ca"); // one special character # which is not allowed as special character

        assertTrue(v1.checkPasswordSpecialChar());
        assertTrue(v2.checkPasswordSpecialChar());
        assertFalse(v3.checkPasswordSpecialChar());
    }



}