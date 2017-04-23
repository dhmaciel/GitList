package com.example.worlo.gitlist;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.worlo.gitlist.activity.MainActivity;
import com.example.worlo.gitlist.utils_test.RecyclerViewMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by worlo on 20/03/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureRecycleViewClickWork(){

        onView(withRecyclerView(R.id.recycler_view_repositorios).atPosition(3))
                .check(ViewAssertions.matches(hasDescendant(withText("square"))));

        onView(withRecyclerView(R.id.recycler_view_repositorios).atPosition(5))
                .check(ViewAssertions.matches(hasDescendant(withText("iluwatar"))));

        onView(withId(R.id.recycler_view_repositorios)).perform(scrollToPosition(2));

        onView(withId(R.id.recycler_view_repositorios))
                .perform(RecyclerViewActions.actionOnItemAtPosition(7, click()));

        PullRequestEspressoTest pullRequestEspressoTest = new PullRequestEspressoTest();
        pullRequestEspressoTest.ensureRecycleViewClickWork();

    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

}
