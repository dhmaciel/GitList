package com.example.worlo.gitlist;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.worlo.gitlist.activity.PullRequestActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by worlo on 20/03/2017.
 */

@RunWith(AndroidJUnit4.class)
public class PullRequestEspressoTest {

    @Rule
    public ActivityTestRule<PullRequestActivity> mActivityRule =
            new ActivityTestRule<>(PullRequestActivity.class);

    @Test
    public void ensureRecycleViewClickWork(){

        onView(withId(R.id.recycler_view_pull_request))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }
}
