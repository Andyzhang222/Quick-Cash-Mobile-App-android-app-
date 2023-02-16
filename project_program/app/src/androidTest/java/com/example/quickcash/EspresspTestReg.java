/**package com.example.quickcash;

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
/**@RunWith(AndroidJUnit4.class)
public class EspresspTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.quickcash", appContext.getPackageName());
    }
    
    /*** check Register page's visibility **/
 /**   @Test
    public void checkIfRegistrationPageIsVisible() {
        onView(withId(R.id.registerEmailField)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.registerPasswordField)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.registerConfirmPasswordField)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.registerBtn));
        onView(withId(R.id.btnRedirectLogin));
    }


}**/