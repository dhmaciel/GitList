package com.example.worlo.gitlist;

import com.example.worlo.gitlist.activity.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by worlo on 20/03/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityRoboletricTest {

    @Test
    public void ensureRecycleViewClickWork(){
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
    }
}
