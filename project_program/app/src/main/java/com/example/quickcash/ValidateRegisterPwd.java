/**
 * Author: Ziyue Wang
 * Description: This class is for validating the registers' passwords
 */
package com.example.quickcash;

public class ValidateRegisterPwd {
    /**
     * These methods that check if the register password is validated or not
     * @param pwd the password that read from register format
     * @return true if the password contain at least one Capital letter, one lower case, one symbol,
     *          and the length is less than 13 more than 8
     */
    public boolean validatePwdNullEmpty (String pwd) {
        boolean bool = true;
        if (pwd == null) {
            bool = false;
        }
        else if (pwd == "") {
            bool = false;
        }
        return bool;
    }
    public boolean validatePwdLength (String pwd) {
        boolean bool = true;

        if (pwd.length() < 8 || pwd.length() > 13) {
            bool = false;
        }

        return bool;
    }

    public boolean validatePwdFormat (String pwd) {
        Boolean bool = true;
        if (!pwd.matches(".*[A-Z].*")) {
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
        return bool;
    }

    public boolean validatePwd (String pwd) {
        boolean bool = false;
        boolean correct = validatePwdLength(pwd) && validatePwdFormat(pwd) && validatePwdNullEmpty(pwd);
        if (correct && pwd.matches("^([A-Z]+|[a-z]+|[0-9]+|[-+_!@#$%^&*.,?]+){8,13}$")){
            bool = true;
        }
        return bool;
    }






/*
* Author:Qianrong Yang
* Description: for validate the email(check whether it follows the rule, has the righ symbol and format)
* */

//import androidx.appcompat.app.AppCompatActivity;
// import android.os.Bundle;

    // Validate using regex
    public boolean validateEmail(String email)
    {
        //String emailRegex format= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailRegex = "^([a-zA-Z0-9]*[-_]?[.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return email.matches(emailRegex);
    }

    public boolean validateEMailNullEmpty (String email) {
        boolean bool = true;
        if (email == null) {
            bool = false;
        }
        else if (email == "") {
            bool = false;
        }
        return bool;
    }


}