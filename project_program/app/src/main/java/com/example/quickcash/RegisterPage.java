package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{

    ValidateRegisterPwd validate = new ValidateRegisterPwd();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Button registerButton = findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(this);
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected String getEmail() {
        EditText email = findViewById(R.id.emailTextBox);
        return email.getText().toString().trim();
    }

    protected String getCreditCard() {
        EditText cc = findViewById(R.id.CardTextBox);
        return cc.getText().toString().trim();
    }

    protected String getPwd() {
        EditText password = findViewById(R.id.passwordTextBox);
        return password.getText().toString().trim();
    }

    protected String getRepeatPwd() {
        EditText password = findViewById(R.id.passwordRepeatTextBox);
        return password.getText().toString().trim();
    }

    protected boolean validatePwdLength(String pwd) {
        if (validate.validatePwdLength(pwd)) {
            return true;
        }
        return false;
    }

    protected boolean validatePwdFormat(String pwd) {
        if (validate.validatePwdFormat(pwd)) {
            return true;
        }
        return false;
    }

    protected boolean validatePwdEmptyNull(String pwd) {
        return !pwd.isEmpty();
    }

    protected boolean validateRepeatPwd (String pwd, String repeatPwd) {
        if (pwd.equals(repeatPwd)) {
            return true;
        }
        return false;
    }


    protected boolean isNullEmptyEmail(String email) {
        return !email.isEmpty();
    }


    protected boolean isValidEmail(String email) {
        if (validate.validateEmail(email)) {
            return true;
        }
        return false;
    }


    protected boolean validCCLength(String cc) {
        if (validate.validateCCLength(cc)) {
            return true;
        }
        return false;
    }

    protected boolean validCCFormat(String cc) {
        if (validate.validateCCFormat(cc)) {
            return true;
        }
        return false;
    }


    @Override
    public void onClick (View view) {
        String email = getEmail();
        String password = getPwd();
        String creditCard = getCreditCard();
        String repeatPwd = getRepeatPwd();
        String emailErrorMessage = new String();
        String pwdErrorMessage = new String();
        String ccErrorMessage = new String();

        if (!isNullEmptyEmail(email)) {
            pwdErrorMessage = getResources().getString(R.string.EMPTY_EMAIL).trim();
            setStatusMessage(pwdErrorMessage);
        }
        else if (!isValidEmail(email)) {
            emailErrorMessage = getResources().getString(R.string.WRONG_EMAIL).trim();
            setStatusMessage(emailErrorMessage);
        }
        else if (!validCCLength(creditCard)) {
            ccErrorMessage = getResources().getString(R.string.WRONG_CREDIT_CARD_LENGTH).trim();
            setStatusMessage(ccErrorMessage);
        }
        else if (!validCCFormat(creditCard)) {
            ccErrorMessage = getResources().getString(R.string.WRONG_CREDIT_CARD_FORMAT).trim();
            setStatusMessage(ccErrorMessage);
        }
        else if (!validatePwdEmptyNull(password)) {
            pwdErrorMessage = getResources().getString(R.string.EMPTY_PASSWORD).trim();
            setStatusMessage(pwdErrorMessage);
        }
        else if (!validatePwdLength(password)) {
            pwdErrorMessage = getResources().getString(R.string.WRONG_LENGTH_PASSWORD).trim();
            setStatusMessage(pwdErrorMessage);
        }
        else if (!validatePwdFormat(password)) {
            pwdErrorMessage = getResources().getString(R.string.WRONG_FORMAT_PASSWORD).trim();
            setStatusMessage(pwdErrorMessage);
        }
        else if (!validateRepeatPwd(password,repeatPwd)) {
            pwdErrorMessage = getResources().getString(R.string.PASSWORD_DOES_NOT_MATCH).trim();
            setStatusMessage(pwdErrorMessage);
        }



    }

}