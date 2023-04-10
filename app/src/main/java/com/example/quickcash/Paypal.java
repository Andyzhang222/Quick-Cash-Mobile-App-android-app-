package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;


public class Paypal extends AppCompatActivity {

    private static final String TAG = Paypal.class.getName();

    //launching a previously-prepared call to start the process of executing an ActivityResultContract.
    private ActivityResultLauncher<Intent> activityResultLauncher;

    //for using Paypal related methods
    private PayPalConfiguration payPalConfig;

    //UI Elements
    private EditText enterAmtET;
    private Button payNowBtn;
    private TextView paymentStatusTV;

    private String employerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        //get the employerId from the MyjobViewHolder activity through the payBtn
        this.employerId = getIntent().getStringExtra("employerId");
        init();
        configPayPal();
        initActivityLauncher();
        setListeners();
    }


    private void init() {
        //initializing ui elements
        enterAmtET = findViewById(R.id.input_amount);
        payNowBtn = findViewById(R.id.pay_btn2);
        paymentStatusTV = findViewById(R.id.paymentStatusTV);
    }

    private void configPayPal() {
        //configuring paypal i.e defining we're using SANDBOX Environment and setting the paypal client id
        payPalConfig = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(Config.PAYPAL_CLIENT_ID);
    }


    private void initActivityLauncher() {
        // Registering a request to start an activity for result, designated by the given contract
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //when operation succeeded
                    if (result.getResultCode() == RESULT_OK) {
                        //getting the resulting datas and checking an additional confirmation required by paypal
                        final PaymentConfirmation confirmation = result.getData().getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                        //if confirmed
                        if (confirmation != null) {
                            try {

                                // Get the payment details
                                String paymentDetails = confirmation.toJSONObject().toString(4);
                                Log.i(TAG, paymentDetails);

                                // Extract json response and display it in a text view.
                                JSONObject payObj = new JSONObject(paymentDetails);

                                String payID = payObj.getJSONObject("response").getString("id");
                                String state = payObj.getJSONObject("response").getString("state");

                                paymentStatusTV.setText(String.format("Payment %s%n with payment id is %s", state, payID));
                            } catch (JSONException e) {

                                Log.e("Error", "an extremely unlikely failure occurred: ", e);
                            }
                        }

                    } else if (result.getResultCode() == PaymentActivity.RESULT_EXTRAS_INVALID) {

                        //returned invalid result - sometimes when the user decides to not go through with the payment
                        Log.d(TAG, "Launcher Result Invalid");

                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {

                        //when transaction is cancelled
                        Log.d(TAG, "Launcher Result Cancelled");
                    }
                });
    }

    private void setListeners() {
        //setting event listeners for the pay button
        payNowBtn.setOnClickListener(v -> processPayment());
    }

    private void processPayment() {

        //getting the amount from the user
        final String amount = enterAmtET.getText().toString();

        //setting the parameters for payment i.e the amount, the currency, intent of the sale
        final PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(
                amount), "CAD", "Purchase Goods", PayPalPayment.PAYMENT_INTENT_SALE);


        // Create Paypal Payment activity intent
        final Intent intent = new Intent(this, PaymentActivity.class);

        // Adding paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfig);

        // Adding paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);

        // Starting Activity Request launcher
        activityResultLauncher.launch(intent);
    }
}