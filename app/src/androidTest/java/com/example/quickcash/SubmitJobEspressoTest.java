package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SubmitJobEspressoTest {
    @Rule
    public ActivityScenarioRule<SubmitJobsActivity> myRule = new ActivityScenarioRule<>(SubmitJobsActivity.class);
    public IntentsTestRule<SubmitJobsActivity> myIntentRule = new IntentsTestRule<>(SubmitJobsActivity.class);

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
    public void checkIfGetValue() {
        onView(withId(R.id.Job_type)).perform(typeText("Driver"));
        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.Job_type)).perform(typeText("Driver"));
//        onView(withId(R.id.Job_type)).perform(typeText("Driver"));
//        onView(withId(R.id.Job_type)).perform(typeText("Driver"));
        onView(withId(R.id.Submit_job_button)).perform(click());
//        intended(hasComponent(LoginPage.class.getName()));
    }
}