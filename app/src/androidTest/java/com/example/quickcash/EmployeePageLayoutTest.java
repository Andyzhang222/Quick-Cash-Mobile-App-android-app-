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
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Matchers;
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
public class EmployeePageLayoutTest {
    @Rule
    public ActivityScenarioRule<EmployeePage> myRule = new ActivityScenarioRule<>(EmployeePage.class);
    public IntentsTestRule<EmployeePage> myIntentRule = new IntentsTestRule<>(EmployeePage.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test public void testTextViewNotEmpty() {
        onView(ViewMatchers.withId(R.id.recyclerview)) .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.not(""))));
    }

    @Test
    public void checkIfSwitched2JobPostingsPage() {
        onView(withId(R.id.recyclerview)).perform(click());
        intended(hasComponent(JobPosting.class.getName()));
    }
}