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
public class registrationTest {

    @Rule
    public ActivityScenarioRule<RegisterPage> myRule = new ActivityScenarioRule<>(RegisterPage.class);


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
    public void checkIfRegistrationPageIsVisible() {
        onView(withId(R.id.registerText1)).check(matches(withText("REGISTER")));
        onView(withId(R.id.emailTextBox)).check(matches(withText("")));
        onView(withId(R.id.CardTextBox)).check(matches(withText("")));
        onView(withId(R.id.passwordTextBox)).check(matches(withText("")));
        onView(withId(R.id.passwordRepeatTextBox)).check(matches(withText("")));
    }

    @Test
    public void checkIfSwitched2LoginPage() {
        onView(withId(R.id.emailTextBox)).perform(typeText("aa111111@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.CardTextBox)).perform(typeText("1111111111111111"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordTextBox)).perform(typeText("Aaa111111@"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordRepeatTextBox)).perform(typeText("Aaa111111@"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginLink)).perform(click());
        intended(hasComponent(LoginPage.class.getName()));
    }
}