/**
 * Author: Ziyue Wang
 * Description: This class is for validating the registers' passwords
 */
package com.example.quickcash;

public class ValidateRegisterPwd {
    public boolean validatePwd (String pwd) {
        return true;
    }

    public boolean validateCreditCard (String credit) {return true;}
}


/*
* Author:Qianrong Yang
* Description: for validate the email(check whether it follows the rule, has the righ symbol and format)
* */

//import androidx.appcompat.app.AppCompatActivity;
// import android.os.Bundle;
public class ValidateEmail {

    // Validate using regex
    public static boolean validateEmail(String email)
    {
        //String emailRegex format= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailRegex = "^([a-zA-Z0-9]*[-_]?[.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return email.matches(emailRegex);
    }



}