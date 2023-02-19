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
        assertFalse(pwd.validatePwdFormat(pwdWihoutCapitalLetter));
    }

    /**
     * Test with the wrong password that without lowerCase
     */
    @Test
    public void testPwdWithoutLowerCase () {
        String pwdWithoutLowerCase = "WANGZIYUE_12";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertTrue(pwd.validatePwdLength(pwdWithoutLowerCase));
        assertFalse(pwd.validatePwdFormat(pwdWithoutLowerCase));
    }

    /**
     * Test with the wrong password that without number
     */
    @Test
    public void testPwdWithoutNumber () {
        String pwdWithoutNumber = "Wang_ziyue";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertTrue(pwd.validatePwdLength(pwdWithoutNumber));
        assertFalse(pwd.validatePwdFormat(pwdWithoutNumber));
    }

    /**
     * Test with the wrong password that without symbol
     */
    @Test
    public void testPwdWithoutSymbol () {
        String pwdWithoutSymbol = "Wangziyue123";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertTrue(pwd.validatePwdLength(pwdWithoutSymbol));
        assertFalse(pwd.validatePwdFormat(pwdWithoutSymbol));
    }

    /**
     * Test with the wrong password that length less than Eight
     */
    @Test
    public void testPwdLengthLessEight () {
        String pwdLessEight = "a_W1";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertTrue(pwd.validatePwdFormat(pwdLessEight));
        assertFalse(pwd.validatePwdLength(pwdLessEight));
    }

    /**
     * Test with the wrong password that length more than thirteen
     */
    @Test
    public void testPwdLengthMoreThanThirteen () {
        String pwdMoreThanThirteen = "Wang_ziyue123123123123123";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertTrue(pwd.validatePwdFormat(pwdMoreThanThirteen));
        assertFalse(pwd.validatePwdLength(pwdMoreThanThirteen));
    }

    /**
     * Test with the wrong password that the input is NUll
     */
    @Test
    public void testPwdIsNull () {
        String nullPwd = null;
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwdNullEmpty(nullPwd));
    }

    /**
     * Test with the wrong password that the input is blank
     */
    @Test
    public void testPwdIsBlank () {
        String blankPwd = "";
        String correctPassword = "Wang_Ziyue12";
        assertTrue(pwd.validatePwd(correctPassword));
        assertFalse(pwd.validatePwdNullEmpty(blankPwd));
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

    /**
     * test validate cc
     */
    public void testValidateCC () {
        String cc = "1234567891234567";
        assertTrue(pwd.validateCCFormat(cc));
    }

    /**
     * Test wrong format cc
     */
    public void testWrongFormatCC () {
        String cc = "123456789123456y";
        assertFalse(pwd.validateCCFormat(cc));
    }

    /**
     * Test wrong length cc
     */
    public void testWrongLengthCC () {
        String shortCC = "123";
        String longCC = "123456789123456789123";
        assertFalse(pwd.validateCCLength(shortCC));
        assertFalse(pwd.validateCCLength(longCC));
    }



}
