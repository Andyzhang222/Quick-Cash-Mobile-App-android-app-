package com.example.quickcash;
/**
 * Test for the pop up function
 * Editor: Guanxiang Wang, Ziyue Wang
 */

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import org.junit.Rule;
import org.junit.Test;

import com.example.quickcash.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.After;
import org.junit.Test;

public class US2PopUpDialogTest {
    @Rule
    public ActivityScenarioRule<SubmitJobsActivity> myRule = new ActivityScenarioRule<>(SubmitJobsActivity.class);
    public IntentsTestRule<SubmitJobsActivity> myIntentRule = new IntentsTestRule<>(SubmitJobsActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * test if the dialog can display after correct type in the click on the button
     */
    @Test
    public void testPopUpDialog () {
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
        onView(withId(R.id.popUpRecycleView)).check(matches(isDisplayed()));
    }
}