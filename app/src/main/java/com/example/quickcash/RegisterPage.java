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
    private EditText editTextCreditCard, editTextEmail, editTextPassword, editTextPasswordRepeat;
    private Button registerUser;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.RegisterButton);
        registerUser.setOnClickListener(this);

        editTextCreditCard = (EditText) findViewById(R.id.CardTextBox) ;
        editTextEmail = (EditText) findViewById(R.id.emailTextBox);
        editTextPassword = (EditText) findViewById(R.id.passwordTextBox);
        editTextPasswordRepeat = (EditText) findViewById(R.id.passwordRepeatTextBox);

        TextView loginLink = findViewById(R.id.loginLink);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected String getEmail() {
        return editTextEmail.getText().toString().trim();
    }

    protected String getCreditCard() {
        return editTextCreditCard.getText().toString().trim();
    }

    protected String getPwd() {
        return editTextPassword.getText().toString().trim();
    }

    protected String getRepeatPwd() {
        return editTextPasswordRepeat.getText().toString().trim();
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

    public boolean validateCCFormat (String cc) {
        if (validate.validateCCFormat(cc) && validate.validateCCLength(cc)) return true;
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
        else if (creditCard.isEmpty()) {
            ccErrorMessage = getResources().getString(R.string.EMPTY_CREDITCARD).trim();
            setStatusMessage(ccErrorMessage);
        }
        else if (!validateCCFormat(creditCard)) {
            ccErrorMessage = getResources().getString(R.string.WRONG_CREDITCARD_FORMAT).trim();
            setStatusMessage(ccErrorMessage);
        }
        else if (!validateRepeatPwd(password,repeatPwd)) {
            pwdErrorMessage = getResources().getString(R.string.PASSWORD_DOES_NOT_MATCH).trim();
            setStatusMessage(pwdErrorMessage);
        }else {
            switch (view.getId()){
                case R.id.RegisterButton:
                    registerUser();
                    break;
            }
        }

    }

    private void registerUser() {
        String email= editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();
        String creditCard = editTextCreditCard.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

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

                }else{
                    Toast.makeText(RegisterPage.this, "This Email has been registered! Please change the email!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
class User {

    public String email, creditCard, password;

    public User(){

    }

    public User(String email, String creditCard, String password) {
        this.email = email;
        this.creditCard = creditCard;
        this.password = password;
    }
}

