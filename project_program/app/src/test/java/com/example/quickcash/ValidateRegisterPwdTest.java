package com.example.quickcash;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class ValidateRegisterPwdTest extends TestCase {
    private ValidateRegisterPwd pwd = new ValidateRegisterPwd();
    static RegisterPage registerPage;

    @BeforeClass
    public static void setup() {
        registerPage = new RegisterPage();
    }

    @AfterClass
    public  void tearDown() {
        System.gc();
    }


    /**
     * Test with the correct password
     */
    @Test
    public void testCorrectPwd () {
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
    }

    /**
     * Test with the wrong password that without CapitalLetter
     */
    @Test
    public void testPwdWithoutCapitalLetter () {
        String pwdWihoutCapitalLetter = "wang_ziyue12";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwd(pwdWihoutCapitalLetter));
    }

    /**
     * Test with the wrong password that without lowerCase
     */
    @Test
    public void testPwdWithoutLowerCase () {
        String pwdWithoutLowerCase = "WANGZIYUE_12";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwd(pwdWithoutLowerCase));
    }

    /**
     * Test with the wrong password that without number
     */
    @Test
    public void testPwdWithoutNumber () {
        String pwdWithoutNumber = "Wang_ziyue";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwd(pwdWithoutNumber));
    }

    /**
     * Test with the wrong password that without symbol
     */
    @Test
    public void testPwdWithoutSymbol () {
        String pwdWithoutSymbol = "Wangziyue123";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwd(pwdWithoutSymbol));
    }

    /**
     * Test with the wrong password that length less than Eight
     */
    @Test
    public void testPwdLengthLessEight () {
        String pwdLessEight = "a_W1";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwd(pwdLessEight));
    }

    /**
     * Test with the wrong password that length more than thirteen
     */
    @Test
    public void testPwdLengthMoreThanThirteen () {
        String pwdMoreThanThirteen = "Wang_ziyue123123123123123";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwd(pwdMoreThanThirteen));
    }

    /**
     * Test with the wrong password that the input is NUll
     */
    @Test
    public void testPwdIsNull () {
        String nullPwd = null;
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwd(nullPwd));
    }

    /**
     * Test with the wrong password that the input is blank
     */
    @Test
    public void testPwdIsBlank () {
        String blankPwd = "";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwd(blankPwd));
    }

    /**
     * Test the blank or null credit card
     */
    public void creditIsBlankOrNull () {
        String credit = "";
        String creditNull = null;
        assertFalse(pwd.validateCreditCard(credit));
        assertFalse(pwd.validateCreditCard(creditNull));
    }

    /**
     * Test the length of credit card that not equal to 16
     */
    @Test
    public void testCreditCarWrongLength () {
        String credit = String.valueOf(123456);
        String longCredit = String.valueOf(0000000000000000);

        assertFalse(pwd.validateCreditCard(credit));
        assertFalse(pwd.validateCreditCard(longCredit));
    }

    /**
     * Test the correct length and format credit card
     */
    @Test
    public void testCorrectCreditCard () {
        String credit = "1111111111111111";
        assertTrue(pwd.validateCreditCard(credit));
    }

    /**
     * Test with wrong format credit card
     */
    @Test
    public void testCreditCardWrongFormat (){
        String credit = "Adsdd";
        assertFalse(pwd.validateCreditCard(credit));
    }



    @Test
    public void checkEmailHasAtSymbolTest() {
        ValidateRegisterPwd v1 = new ValidateRegisterPwd();
        ValidateRegisterPwd v2 = new ValidateRegisterPwd();
        ValidateRegisterPwd v3 = new ValidateRegisterPwd();
        assertFalse(v1.validateEmail("Abcd2023.fdsa.sss"));// upper case & lower case+ . +letters+ . +letters
        assertFalse(v2.validateEmail("abjshd.com"));// without @ symbol
        assertFalse(v3.validateEmail("abjshd@@dal.com"));// extra at symbol

    }


    @Test
    public void checkEmailHasDotSymbolTest() {
        ValidateRegisterPwd v1 = new ValidateRegisterPwd();
        ValidateRegisterPwd v2 = new ValidateRegisterPwd();
        ValidateRegisterPwd v3 = new ValidateRegisterPwd();

        assertTrue(v1.validateEmail("asdf@com"));// without the dot symbol
        assertTrue(v2.validateEmail("asdf@dal.ca"));// right format
        assertTrue(v3.validateEmail("asdf@dal..ca"));// extra dot symbol
    }


    @Test
    public void checkEmailSequenceTest() {
        ValidateRegisterPwd v1 = new ValidateRegisterPwd();
        ValidateRegisterPwd v2 = new ValidateRegisterPwd();

        assertTrue(v1.validateEmail("asdf.dal@com"));// dot symbol is in front of at symbol
        assertTrue(v2.validateEmail("asdf@dal.ca"));// right format
    }



}