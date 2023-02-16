package com.example.quick_cash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.Toast;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith(AndroidJUnit4.class)
public class LoginPageEspressoTest {
    @Rule
    public ActivityScenarioRule<LoginPage> myRule = new ActivityScenarioRule<>(LoginPage.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void checkIfLandingPageIsVisible() {
        onView(withId(R.id.EmailTextBox)).check(matches(withText("")));
        onView(withId(R.id.PasswordTextBox)).check(matches(withText("")));
    }

    @Test
    public void checkIfEmailIsEmpty() {
        onView(withId(R.id.EmailTextBox)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.LoginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_EMAIL)));
    }

    @Test
    public void checkIfEmailIsValid() {
        onView(withId(R.id.EmailTextBox)).perform(typeText("abc.123@gmail.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.LoginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }



    @After
    public void tearDown() throws Exception {
        System.gc();
    }
}