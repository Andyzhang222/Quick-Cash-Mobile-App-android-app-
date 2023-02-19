package com.example.quickcash;


import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterPageValidationTest {

    @Rule
    public ActivityScenarioRule<RegisterPage> myRule = new ActivityScenarioRule<>(RegisterPage.class);


    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.quickcash", appContext.getPackageName());
    }


    @Test
    public void checkIfEmailIsValid() {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

    @Test
    public void checkInvalidEmail() {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("dsd.sd.sd"));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_EMAIL)));
    }

    @Test
    public void checkEmptyNullEmail () {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText(""));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_EMAIL)));
    }



    @Test
    public void checkValidPwd() {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

    @Test
    public void checkEmptyNullPwd () {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_PASSWORD)));
    }

    @Test
    public void checkEmptyNullCC () {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_CREDITCARD)));
    }
    @Test
    public void checkWrongFormatCC () {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("111111"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_CREDITCARD_FORMAT)));
    }

    @Test
    public void checkWrongLengthPwd () {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("WangZiyue_12345667721334"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("WangZiyue_12345667721334"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_LENGTH_PASSWORD)));
    }

    @Test
    public void checkWrongFormatPwd () {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("WangZiyue123"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("WangZiyue123"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText(R.string.WRONG_FORMAT_PASSWORD)));
    }

    @Test
    public void checkMatchPwd() {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

    @Test
    public void checkNotMatchPwd () {
        Espresso.onView(withId(R.id.emailTextBox)).perform(typeText("abcdef@dal.ca"));
        Espresso.onView(withId(R.id.CardTextBox)).perform(typeText("1234567891234567"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordTextBox)).perform(typeText("Wang_Ziyue12"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Wang_Ziyue1"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.RegisterButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText(R.string.PASSWORD_DOES_NOT_MATCH)));
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }


}




