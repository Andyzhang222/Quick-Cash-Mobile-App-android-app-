package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.*;
import static androidx.test.espresso.intent.matcher.IntentMatchers.*;
import static org.hamcrest.Matchers.allOf;

import com.paypal.android.sdk.payments.PaymentActivity;

public class PayPayTest {

    @Rule
    public ActivityScenarioRule<Paypal> myRule = new ActivityScenarioRule<>(Paypal.class);

    @Before
    public void setUp() throws Exception {
        // Initialize Espresso Intents
        Intents.init();
    }

    @After
    public void tearDown() throws Exception {
        // Clean up Espresso Intents
        Intents.release();
    }

    @Test
    public void testInputAmount() {
        // Input amount
        onView(withId(R.id.input_amount)).perform(replaceText("10.0"), closeSoftKeyboard());

        // Assert that the input amount is set correctly
        onView(withId(R.id.input_amount)).check(matches(withText("10.0")));
    }

    @Test
    public void testPayButtonClick() {
        // Input amount
        Espresso.onView(withId(R.id.input_amount))
                .perform(replaceText("10.0"), closeSoftKeyboard());

        // Click pay button
        Espresso.onView(withId(R.id.pay_btn2))
                .perform(click());

        // Check if the PaymentActivity is launched
        intended(allOf(
                hasComponent(PaymentActivity.class.getName()),
                toPackage("com.example.quickcash")
        ));
    }
}
