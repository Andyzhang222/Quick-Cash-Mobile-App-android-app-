package com.example.quickcash;
import android.content.Context;

import androidx.test.espresso.Espresso;
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
import static androidx.test.espresso.action.ViewActions.replaceText;
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
        onView(withId(R.id.loginPageText1)).check(matches(withText("Login")));
        onView(withId(R.id.emailTextField)).check(matches(withText("")));
        onView(withId(R.id.passwordTextField)).check(matches(withText("")));
        onView(withId(R.id.registerLink)).check(matches(withText("Click to Register now!")));
    }

    @Test
    public void checkIfALLIsEmpty() {
        onView(withId(R.id.emailTextField)).perform(replaceText(""));
        onView(withId(R.id.passwordTextField)).perform(replaceText(""));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_All)));
    }

    @Test
    public void checkIfEmailIsEmpty() {
        onView(withId(R.id.emailTextField)).perform(replaceText(""));
        onView(withId(R.id.passwordTextField)).perform(replaceText("123456789"));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_EMAIL)));
    }

    @Test
    public void checkIfPasswordIsEmpty() {
        onView(withId(R.id.emailTextField)).perform(replaceText("abc123@dal.ca"));
        onView(withId(R.id.passwordTextField)).perform(replaceText(""));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_PASSWORD)));
    }


    @Test
    public void checkIfEmailIsInValid() {
        onView(withId(R.id.emailTextField)).perform(typeText("abc.123gmail.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("Aa123321."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL_ADDRESS)));
    }

    @Test
    public void checkIfPassWordIsValid(){
        onView(withId(R.id.emailTextField)).perform(typeText("abc.123@gmail.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("Aa123321."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.LOGIN_NOW)));
    }

    @Test
    public void checkIfEmailIsValid() {
        onView(withId(R.id.emailTextField)).perform(typeText("abc.123@gmail.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("Aa123321."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.LOGIN_NOW)));
    }

    @Test
    public void checkIfPassWordIsInValid(){
        //less than 8 characters
        onView(withId(R.id.emailTextField)).perform(typeText("abc.123@gmail.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("abc"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_PASSWORD)));
    }

    @Test
    public void checkIfPassWordIsInValid2(){
        //miss the Special characters
        onView(withId(R.id.emailTextField)).perform(typeText("abc.123@gmail.ca"));
        onView(withId(R.id.passwordTextField)).perform(typeText("A123456789"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_PASSWORD)));
    }


    @Test
    public void checkIfLoginISWorking() {
        onView(withId(R.id.emailTextField)).perform(replaceText("abc123@dal.ca"));
        //this is a wrong password because we just want to test if the login is working
        //correct password is Zh1234567
        onView(withId(R.id.passwordTextField)).perform(replaceText("Zh12345678."));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.LOGIN_NOW)));
    }

    @Test
    public void checkIfSwitched2RegisterPage() {
        onView(withId(R.id.registerLink)).perform(click());
        intended(hasComponent(RegisterPage.class.getName()));
    }


}
