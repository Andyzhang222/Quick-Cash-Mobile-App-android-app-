/*
*Author : Qianrong Yang
* Description: This class is meant to validate the registers' email
*/

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
    }


    // Validate using regex
    public boolean validateEmail(String email){

        return true;
    }





}