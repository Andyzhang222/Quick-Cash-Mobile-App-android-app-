package com.example.quickcash;


import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.quickcash.Explore.*;

@RunWith(AndroidJUnit4.class)
public class ExplorePageTest {

    @Rule
    public ActivityScenarioRule<ExploreEmployeeActivity> activityRule = new ActivityScenarioRule<>(ExploreEmployeeActivity.class);

    @Test
    public void checkIfSearchViewIsDisplayed() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()));
    }

}

