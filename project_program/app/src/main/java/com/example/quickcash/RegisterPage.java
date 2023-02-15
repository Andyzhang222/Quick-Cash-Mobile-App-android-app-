package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;
import androidx.annotation.NonNull;

public class RegisterPage extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference emailRef;
    DatabaseReference credCardRef;
    DatabaseReference passwordRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Button registerButton = findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener((View.OnClickListener) this);

        database = FirebaseDatabase.getInstance();
        emailRef = database.getReference("email");
        credCardRef = database.getReference("credit card");
        passwordRef = database.getReference("password");
    }

    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.emailTextBox);
        return emailAddress.getText().toString().trim();
    }

    protected String getCreditCardNumber() {
        EditText creditCardNumber = findViewById(R.id.CardTextBox);
        return creditCardNumber.getText().toString().trim();
    }

    protected String getPassword() {
        EditText password = findViewById(R.id.passwordTextBox);
        return password.getText().toString().trim();
    }
    protected String getPasswordRepeat() {
        EditText passwordRepeat = findViewById(R.id.passwordRepeatTextBox);
        return passwordRepeat.getText().toString().trim();
    }

    protected Task<Void> saveEmailToFirebase(String emailAddress) {
        //buggy method, fix it.
        return emailRef.setValue(emailAddress);
    }
    protected Task<Void> saveCreditCardToFirebase(String creditCardNumber) {
        //buggy method, fix it.
        return credCardRef.setValue(creditCardNumber);
    }
    protected Task<Void> savePasswordToFirebase(String password) {
        //buggy method, fix it.
        return passwordRef.setValue(password);
    }

    public void onClick(View view) {
        String emailAddress = getEmailAddress();
        String creditCardNumber = getCreditCardNumber();
        String password = getPassword();
        String passwordRepeat = getPasswordRepeat();
        String errorMessage = new String();

        saveEmailToFirebase(emailAddress);
        saveCreditCardToFirebase(creditCardNumber);
        savePasswordToFirebase(password);

    }
}