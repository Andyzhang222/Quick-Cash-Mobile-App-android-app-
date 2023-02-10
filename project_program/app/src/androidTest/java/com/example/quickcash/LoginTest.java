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

import static org.junit.Assert.*;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityScenarioRule<LoginPage> myRule = new ActivityScenarioRule<>(LoginPage.class);
    public IntentsTestRule<LoginPage> myIntentRule = new IntentsTestRule<>(LoginPage.class);


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
    public void checkIfLoginPageIsVisible() {
        onView(withId(R.id.loginPageText1)).check(matches(withText("LOGIN PAGE")));
        onView(withId(R.id.emailTextField)).check(matches(withText("")));
        onView(withId(R.id.passwordTextField)).check(matches(withText("")));
        onView(withId(R.id.registerLink)).check(matches(withText("Click to Register now!")));
    }

    @Test
    public void checkIfEmailIsEmpty() {
        onView(withId(R.id.emailTextField)).perform(typeText(""));
        onView(withId(R.id.passwordTextField)).perform(typeText("12345678"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Please Enter Your Email!")));
    }

    @Test
    public void checkIfPasswordIsEmpty() {
        onView(withId(R.id.emailTextField)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText(""));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Please Enter Your Password!")));
    }

    @Test
    public void checkIfALLIsEmpty() {
        onView(withId(R.id.emailTextField)).perform(typeText(""));
        onView(withId(R.id.passwordTextField)).perform(typeText(""));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Please Enter Your Email and Password!")));
    }

    @Test
    public void checkIfALLIsValid() {
        onView(withId(R.id.emailTextField)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("Zh1234567"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

    @Test
    public void checkIfEmailIsInvalid() {
        onView(withId(R.id.emailTextField)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("Zh1234567"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Invalid Email")));
    }
    @Test
    public void checkIfPasswordIsInvalid() {
        onView(withId(R.id.emailTextField)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("12345678"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("Invalid Password")));
    }

    @Test
    public void checkIfSwitched2LandingPage() {
        onView(withId(R.id.emailTextField)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("Zh1234567"));
        onView(withId(R.id.loginButton)).perform(click());
        intended(hasComponent(LandingPage.class.getName()));
    }

    @Test
    public void checkIfSwitched2RegisterPage() {
        onView(withId(R.id.registerLink)).perform(click());
        intended(hasComponent(RegisterPage.class.getName()));
    }


}
