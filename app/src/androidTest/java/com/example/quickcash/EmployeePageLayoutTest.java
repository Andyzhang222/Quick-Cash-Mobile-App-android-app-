package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static java.util.regex.Pattern.matches;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matcher;
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

    /**
     * Test if the employee page's recycleView is displayed
     */
    @Test public void testTextViewNotEmpty() {
        onView(withId(R.id.recyclerview))
                // Select the second item in the list
                //.perform(RecyclerViewActions.actionOnItemAtPosition(1, click()))
                // Check that the title of the second item matches the expected text
                .check(matches(isDisplayed()));
    }




    @Test
    public void checkIfSwitched2JobPostingsPage() {
        Espresso.onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        intended(hasComponent(JobPosting.class.getName()));
    }
}