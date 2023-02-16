package com.example.quickcash;

import android.content.Context;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterPageTest {

    @Rule
    public ActivityScenarioRule<RegisterPage> myRule = new ActivityScenarioRule<>(RegisterPage.class);
    public IntentsTestRule<RegisterPage> myIntentRule = new IntentsTestRule<>(RegisterPage.class);


    @BeforeClass
    public static void setup() {
        Intents.init();
    }


    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.quickcash", appContext.getPackageName());
    }


    @Test
    public void checkValidEmail() {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    @Test
    public void checkInvalidEmail() {
        onView(withId(R.id.emailTextBox)).perform(typeText("Abcd2023.fdsa.sss"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_EMAIL)));
    }


    @Test
    public void checkValidCC() {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    @Test
    public void checkWrongLengthCC() {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("0000000000000000000"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_CREDIT_CARD_LENGTH)));
    }

    @Test
    public void checkWrongFormatCC() {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("aaaaaaaaaaaaaaaa"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_CREDIT_CARD_FORMAT)));
    }

    @Test
    public void checkValidPwd() {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    @Test
    public void checkEmptyNullPwd () {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText(""));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText(""));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_PASSWORD)));
    }

    @Test
    public void checkWrongLengthPwd () {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("WangZiyue_12345667721334"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("WangZiyue_12345667721334"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_LENGTH_PASSWORD)));
    }

    @Test
    public void checkWrongFormatPwd () {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("WangZiyue12345667721334"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("WangZiyue12345667721334"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_FORMAT_PASSWORD)));
    }

    @Test
    public void checkMatchPwd() {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    @Test
    public void checkNotMatchPwd () {
        onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue1"));
        onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.PASSWORD_DOES_NOT_MATCH)));
    }

}





