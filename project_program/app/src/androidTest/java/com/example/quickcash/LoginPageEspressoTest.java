package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

//@RunWith(AndroidJUnit4.class)
public class LoginPageEspressoTest {
    @Rule
    public ActivityScenarioRule<LoginPage> myRule = new ActivityScenarioRule<>(LoginPage.class);
    @Rule
    public IntentsTestRule<LoginPage> mActivityIntentsTestRule = new IntentsTestRule<LoginPage>(LoginPage.class);


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void checkIfLandingPageIsVisible() {
        onView(withId(R.id.emailTextField)).check(matches(withText("")));
        onView(withId(R.id.passwordTextField)).check(matches(withText("")));
    }

    @Test
    public void checkIfEmailIsEmpty() {
        Espresso.onView(withId(R.id.emailTextField)).perform(typeText(""));
        Espresso.onView(withId(R.id.passwordTextField)).perform(typeText("Aa123321@"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Espresso.onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }
}

//    @After
//    public void tearDown() throws Exception {
//        System.gc();
//    }
