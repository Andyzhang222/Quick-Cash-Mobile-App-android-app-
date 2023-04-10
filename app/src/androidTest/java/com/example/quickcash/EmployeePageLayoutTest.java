package com.example.quickcash;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.contrib.RecyclerViewActions;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
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
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static java.util.EnumSet.allOf;
import static java.util.regex.Pattern.matches;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
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
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));
    }

    /**
     * Test if employee click one job's block, will it switch to the next screen
     */
    @Test
    public void checkIfSwitched2JobPostingsPage() {
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        intended(hasComponent(JobPosting.class.getName()));
    }
    @Test
    public void testApplyButton(){
        SystemClock.sleep(2000);
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.applyButton)));
        SystemClock.sleep(2000);
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                if (v != null) {
                    v.performClick();
                    // Check if the text of the button has changed to "applied"
                    if (v instanceof Button) {
                        String buttonText = ((Button) v).getText().toString();
                        if (buttonText.equals("applied")) {
                            return;
                        }
                    }
                }
            }
        };
    }



}