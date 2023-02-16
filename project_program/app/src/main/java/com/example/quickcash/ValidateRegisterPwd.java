/**
 * Author: Ziyue Wang
 * Description: This class is for validating the registers' passwords
 */
package com.example.quickcash;

public class ValidateRegisterPwd {
    /**
     * The method that check if the register password is validated or not
     * @param pwd the password that read from register format
     * @return true if the password contain at least one Capital letter, one lower case, one symbol,
     *          and the length is less than 13 more than 8
     */
    public boolean validatePwd (String pwd) {
        boolean bool = true;
        if (pwd == null) {
            bool = false;
        }
        else if (pwd == "") {
            bool = false;
        }
        else if (pwd.length() < 8 || pwd.length() > 13) {
            bool = false;
        }
        else if (!pwd.matches(".*[A-Z].*")) {
            bool = false;
        }
        else if (!pwd.matches(".*[a-z].*")) {
            bool = false;
        }
        else if (!pwd.matches(".*[0-9].*")) {
            bool = false;
        }
        else if (!pwd.matches(".*[-+_!@#$%^&*.,?].*")) {
            bool = false;
        }
        else if (pwd.matches("^([A-Z]+|[a-z]+|[0-9]+|[-+_!@#$%^&*.,?]+){8,13}$")){
            bool = true;
        }
        return bool;
    }

    /**
     * The method that check if the credit card is validated or not
     * @param credit the credit number
     * @return true if the foramat are all numbers, and the length equal to 16
     *          false if the format and length is wrong
     */
    public boolean validateCreditCard (String credit) {
        if (credit == null || credit == "") return false;
        if (!credit.matches("[0-9]{16}")) {
            return false;
        }
        else if (credit.length() != 16) {
            return false;
        }
        return true;
    }



/*
* Author:Qianrong Yang
* Description: for validate the email(check whether it follows the rule, has the righ symbol and format)
* */

//import androidx.appcompat.app.AppCompatActivity;
// import android.os.Bundle;

    // Validate using regex
    public static boolean validateEmail(String email)
    {
        //String emailRegex format= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailRegex = "^([a-zA-Z0-9]*[-_]?[.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return email.matches(emailRegex);
    }



}