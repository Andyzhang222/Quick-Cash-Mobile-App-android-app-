package com.example.quick_cash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private String passWord;
    private String email;

    public Validator(String passWord,String email) {
        this.passWord = passWord;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean checkPasswordLength(){
        if (passWord.length() >= 8){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkPasswordCase() {

        boolean isUpperCase = false;
        boolean isLowerCase = false;
        char currentChar;
        for (int i = 0; i < passWord.length(); i++) {
            currentChar = passWord.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                isUpperCase = true;
            } else if(Character.isLowerCase(currentChar)) {
                isLowerCase = true;
            }
        }
        if(isUpperCase && isLowerCase)
            return true;
        else
            return false;
    }

    public boolean checkPasswordSpecialChar() {

        String regex = "[a-zA-Z0-9_@$!]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passWord);
        return matcher.matches();

    }

    protected boolean isValidEmailAddress(String emailAddress) {
        //buggy method, fix it.
        Boolean result = true;
        Pattern pat = Pattern.compile("^^(.+)@(.+)$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pat.matcher(emailAddress);

        if (!matcher.matches()){
            result = false;
        }
        return result;
    }

}
