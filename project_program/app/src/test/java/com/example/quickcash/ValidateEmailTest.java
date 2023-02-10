package com.example.quickcash;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class ValidateEmailTest extends TestCase {
    /**
     * Example local unit test, which will execute on the development machine (host).
     *
     * @see < a href=" ">Testing documentation</ a>
     */
    public static class ValidateRegisterEmailTest {

        public void addition_isCorrect() {
            assertEquals(4, 2 + 2);
        }


        private ValidateEmail email = new ValidateEmail();

        static RegisterPage registerPage;

        @BeforeClass
        public static void setup() {
            registerPage = new RegisterPage();
        }

        @AfterClass
        public  void tearDown() {
            System.gc();
        }

        // Email format : letters,numbers,symbols+@+letters+"."+letters



        @Test
        public void checkEmailHasAtSymbolTest() {
            ValidateEmail v1 = new ValidateEmail();
            ValidateEmail v2 = new ValidateEmail();
            ValidateEmail v3 = new ValidateEmail();
            assertFalse(v1.validateEmail("Abcd2023.fdsa.sss"));// upper case & lower case+ . +letters+ . +letters
            assertFalse(v2.validateEmail("abjshd.com"));// without @ symbol
            assertFalse(v3.validateEmail("abjshd@@dal.com"));// extra at symbol

        }


        @Test
        public void checkEmailHasDotSymbolTest() {
            ValidateEmail v1 = new ValidateEmail();
            ValidateEmail v2 = new ValidateEmail();
            ValidateEmail v3 = new ValidateEmail();

            assertTrue(v1.validateEmail("asdf@com"));// without the dot symbol
            assertTrue(v2.validateEmail("asdf@dal.ca"));// right format
            assertTrue(v3.validateEmail("asdf@dal..ca"));// extra dot symbol
        }


        @Test
        public void checkEmailSequenceTest() {
            ValidateEmail v1 = new ValidateEmail();
            ValidateEmail v2 = new ValidateEmail();

            assertTrue(v1.validateEmail("asdf.dal@com"));// dot symbol is in front of at symbol
            assertTrue(v2.validateEmail("asdf@dal.ca"));// right format
        }
    }
}