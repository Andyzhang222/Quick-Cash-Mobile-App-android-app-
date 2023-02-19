/**
 * Author: Ziyue Wang,Qianrong Yang
 * Description: ValidateRegisterPwd this class is for validating the registers' passwords.
 * ValidateEmail this class: for validate the email (check whether it follows the rule
 * has the right symbol and format)
 * Code Reviewer: Zihao Liu
 **/
package com.example.quickcash;

public class ValidateRegisterPwd {
    /**
     * These methods that check if the register password is validated or not
     * @param pwd the password that read from register format
     * @return true if the password contain at least one Capital letter, one lower case, one symbol,
     *          and the length is less than 13 more than 8
     */
    public boolean validatePwdNullEmpty (String pwd) {
        if (pwd == null) {
            return false;
        }
        return !pwd.equals("");
    }


    /**
     * This method that check if the register password has the right length
     * @param pwd the password that read from register format
     * @return true if the password is between 8 -13, false if less than 8 or more than 13
     */
    public boolean validatePwdLength (String pwd) {

        return pwd.length() >= 8 && pwd.length() <= 13;
    }


    /**
     * This method that check if the register password's format
     * @param pwd the password that read from register format
     * @return true if the password is following the right format(match the format: has capital and lower letters in the same time,also with numbers and symbols)
     * false if lose any of the requires
     */
    public boolean validatePwdFormat (String pwd) {
        if (!pwd.matches(".*[A-Z].*")) {
            return false;
        }
        else if (!pwd.matches(".*[a-z].*")) {
            return false;
        }
        else if (!pwd.matches(".*[0-9].*")) {
            return false;
        }
        else if (!pwd.matches(".*[-+_!@#$%^&*.,?].*")) {
            return false;
        }
        return true;
    }


    /**
     * This method that check if the register password's format
     * @param pwd the password that read from register format
     * @return true if the password is following the right format(match the format in the same time: has capital and lower letters in the same time,also with numbers and symbols)
     * else return false
     */
    public boolean validatePwd (String pwd) {
        boolean correct = validatePwdLength(pwd) && validatePwdFormat(pwd) && validatePwdNullEmpty(pwd);
        return correct && pwd.matches("^([A-Z]+|[a-z]+|[0-9]+|[-+_!@#$%^&*.,?]+){8,13}$");
    }


    /**
     * This method that check the register email's format
     * @param email the email that read from register format
     * @return true if the email fits the format that has a @ symbol in string and and dot symbol at last
     */
    public boolean validateEmail (String email)
    {
        //String emailRegex format= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailRegex = "^([a-zA-Z0-9]*[-_]?[.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return email.matches ( emailRegex );
    }


    /**
     * This method that check if the register email's format right or not
     * @param email the email that read from register format
     * @return true if the email id not null, false if the email is null
     */
    public boolean validateEMailNullEmpty (String email) {
        if (email == null) {
            return false;
        }
        else return !email.equals("");
    }


    /**
     * This method that check if the register credit card's format right or not
     * @param credit the credit card number that read from register format
     * @return true if the credit number is all numbers
     */
    public boolean validateCCFormat (String credit) {
        if (credit == null || credit.equals("")) return false;
        return credit.matches("[0-9]{16}");
    }


    /**
     * This method that check if the register credit card's format right or not
     * @param credit the credit card number that read from register format
     * @return true if the numbers' count is 16
     */
    public boolean validateCCLength (String credit) {
        return credit.length() == 16;
    }

}