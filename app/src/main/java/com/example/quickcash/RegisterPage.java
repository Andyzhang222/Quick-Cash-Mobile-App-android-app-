/**
 * This activity is used for register activity
 * Editor: Guangxiang Wang, Haoran Zhang, Qianrong Yang, Ziyue Wang
 * Code Reviewer:Xinxin Yu, Zhiqiang Yu
 */

package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{
    ValidateRegisterPwd validate = new ValidateRegisterPwd();


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        //connect the Firebase
        mAuth = FirebaseAuth.getInstance();
        //implement register button function
        Button registerUser = (Button) findViewById(R.id.RegisterButton);
        registerUser.setOnClickListener(this);

        //this is th login link for transfer to the login page
        TextView loginLink = findViewById(R.id.loginLink);
        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterPage.this, LoginPage.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * This method used to give a hint for user input
     * @param message hint for user input
     */
    protected void setStatusMessage (String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    /**
     * This method used to get string of email
     * @return the string of email
     */
    protected String getEmail () {
        EditText editTextEmail = (EditText) findViewById(R.id.emailTextBox);
        return editTextEmail.getText().toString().trim();
    }

    /**
     * This method used to get string of credit card
     * @return the string of credit card
     */
    protected String getCreditCard () {
        EditText editTextCreditCard = (EditText) findViewById(R.id.CardTextBox) ;
        return editTextCreditCard.getText().toString().trim();
    }

    /**
     * This method used to get string of password
     * @return the string of password
     */
    protected String getPwd () {
        EditText editTextPassword = (EditText) findViewById(R.id.passwordTextBox);
        return editTextPassword.getText().toString().trim();
    }

    /**
     * This method used to get string of repeat password
     * @return the string of repeat password
     */
    protected String getRepeatPwd () {
        EditText editTextPasswordRepeat;
        editTextPasswordRepeat = (EditText) findViewById(R.id.passwordRepeatTextBox);
        return editTextPasswordRepeat.getText().toString().trim();
    }

    /**
     * This method used to check the length of password
     * @param pwd the string of password
     * @return true if the length of password is correct,otherwise false
     */
    protected boolean validatePwdLength (String pwd) {
        return validate.validatePwdLength(pwd);
    }

    /**
     * This method used to check the format of password
     * @param pwd the string of password
     * @return true if the format of password is correct,otherwise false
     */
    protected boolean validatePwdFormat (String pwd) {
        return validate.validatePwdFormat(pwd);
    }

    /**
     * This method used to check the password if is empty
     * @param pwd the string of password
     * @return true if the password is not empty, otherwise false
     */
    protected boolean validatePwdEmptyNull (String pwd) {
        return !pwd.isEmpty();
    }

    /**
     * This method used to check the password and repeat password if is equal
     * @param pwd the string of password
     * @param repeatPwd the string of repeat of password
     * @return true if both password match, otherwise false
     */
    protected boolean validateRepeatPwd (String pwd, String repeatPwd) {
        return pwd.equals(repeatPwd);
    }

    /**
     * This method used to check the email if is empty
     * @param email the string of email
     * @return true if the email is not empty, otherwise false
     */
    protected boolean isNullEmptyEmail (String email) {
        return !email.isEmpty();
    }

    /**
     * This method used to check the format of email
     * @param email the string of email
     * @return true if the format of email is correct,otherwise false
     */
    protected boolean isValidEmail (String email) {
        return validate.validateEmail(email);
    }

    /**
     * This method used to check the format of credit card
     * @param cc the string of credit card
     * @return true if the format of credit card is correct,otherwise false
     */
    public boolean validateCCFormat (String cc) {
        return validate.validateCCFormat(cc) && validate.validateCCLength(cc);
    }

    /**
     * This method that check the different error message
     * @param view the information that read from register format
     * to check whether each fits the require format
     */
    @Override
    public void onClick (View view) {
        //get email,password, repeat password, credit card
        String email = getEmail();
        String password = getPwd();
        String creditCard = getCreditCard();
        String repeatPwd = getRepeatPwd();


        //this part for checking the error of user input
        //if user input is all fine,we should start register
        if (!isNullEmptyEmail(email)) {
            setStatusMessage(getResources().getString(R.string.EMPTY_EMAIL).trim());
        }
        else if (!isValidEmail(email)) {
            setStatusMessage(getResources().getString(R.string.WRONG_EMAIL).trim());
        }
        else if (!validatePwdEmptyNull(password)) {
            setStatusMessage( getResources().getString(R.string.EMPTY_PASSWORD).trim());
        }
        else if (!validatePwdLength(password)) {
            setStatusMessage(getResources().getString(R.string.WRONG_LENGTH_PASSWORD).trim());
        }
        else if (!validatePwdFormat(password)) {
            setStatusMessage( getResources().getString(R.string.WRONG_FORMAT_PASSWORD).trim());
        }
        else if (creditCard.isEmpty()) {
            setStatusMessage( getResources().getString(R.string.EMPTY_CREDITCARD).trim());
        }
        else if (!validateCCFormat(creditCard)) {
            setStatusMessage(getResources().getString(R.string.WRONG_CREDITCARD_FORMAT).trim());
        }
        else if (!validateRepeatPwd(password,repeatPwd)) {
            setStatusMessage(getResources().getString(R.string.PASSWORD_DOES_NOT_MATCH).trim());
        }else {
            setStatusMessage("");
            switch (view.getId()){
                case R.id.RegisterButton:
                    registerUser(email, password, creditCard);
                    break;
            }
        }
    }

    /**
     * This method is used for user register
     * @param email the string of user's email
     * @param password the string of user's password
     * @param creditCard the string of  user's credit card
     */
    private void registerUser(String email, String password, String creditCard) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                User user = new User(email, creditCard, password);
                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterPage.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(RegisterPage.this, "Fail to register! Try again!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
            else{
                Toast.makeText(RegisterPage.this, "This Email has been registered! Please change the email!", Toast.LENGTH_LONG).show();
            }
        });
    }
}

/**
 * This class for register with user information
 */
class User {

    private String email;
    private String creditCard;
    private String password;

    public User(){

    }

    public String getEmail() {
        return email;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String email, String creditCard, String password) {
        this.email = email;
        this.creditCard = creditCard;
        this.password = password;
    }
}

