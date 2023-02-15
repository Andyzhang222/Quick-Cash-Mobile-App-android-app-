package com.example.quick_cash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
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


    @After
    public void tearDown() throws Exception {
        System.gc();
    }
}