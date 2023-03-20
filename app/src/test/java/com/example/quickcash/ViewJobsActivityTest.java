package com.example.quickcash;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ViewJobsActivityTest {
    @Rule
    public IntentsTestRule<ViewJobsActivity> myIntentRule = new IntentsTestRule<>(ViewJobsActivity.class);

    @Test
    public void checkIfPageIsVisible() {
        onView(withId(R.id.p3)).check(matches(withText("POSTED JOBS")));
    }

    @Test
    public void backButtonShouldNavigateToEmployerPage() {
        onView(withId(R.id.button2)).check(matches(isEnabled()));
        onView(withId(R.id.button2)).perform(click());
        intended(hasComponent(EmployerPage.class.getName()));
    }
}
