package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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
    public void checkIfPageIsVisible() {
        onView(withId(R.id.title)).check(matches(withText("Post Job")));
        onView(withId(R.id.textView16)).check(matches(withText("Job Type")));
        onView(withId(R.id.textView12)).check(matches(withText("Description")));
        onView(withId(R.id.textView19)).check(matches(withText("Place")));
        onView(withId(R.id.textView17)).check(matches(withText("Date")));
        onView(withId(R.id.textView20)).check(matches(withText("Salary")));
        onView(withId(R.id.emergency_select)).check(matches(withText("emergency")));
    }

    @Test
    public void postedJobSuccessfully() {
        onView(withId(R.id.Job_type)).perform(typeText("Driver"));
        onView(withId(R.id.Description)).perform(typeText("work for a homeless people "));
        onView(withId(R.id.Place)).perform(typeText("111 crown drive"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.date)).perform(typeText("2023-01-20"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.duration)).perform(typeText("2"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.salary)).perform(typeText("2000"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Submit_job_button)).perform(click());
    }

    @Test
    public void postedJobUnSuccessfully() {
        onView(withId(R.id.Job_type)).perform(typeText(""));
        onView(withId(R.id.Description)).perform(typeText(""));
        onView(withId(R.id.Place)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.date)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.duration)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.salary)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.Submit_job_button)).perform(click());
    }

    @Test
    public void checkIfAllFieldsAreEnabled() {
        onView(withId(R.id.Job_type)).check(matches(isEnabled()));
        onView(withId(R.id.Description)).check(matches(isEnabled()));
        onView(withId(R.id.Place)).check(matches(isEnabled()));
        onView(withId(R.id.date)).check(matches(isEnabled()));
        onView(withId(R.id.duration)).check(matches(isEnabled()));
        onView(withId(R.id.salary)).check(matches(isEnabled()));
        onView(withId(R.id.Submit_job_button)).check(matches(isEnabled()));
    }
}