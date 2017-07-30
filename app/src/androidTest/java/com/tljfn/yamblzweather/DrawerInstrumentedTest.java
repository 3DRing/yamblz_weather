package com.tljfn.yamblzweather;

import android.support.annotation.IdRes;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.start.StartFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

/**
 * Created by ringov on 30.07.17.
 */

@RunWith(AndroidJUnit4.class)
public class DrawerInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    private void openDrawerMenu(@IdRes int menuId) {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.drawer_layout)).check(matches(isOpen()));
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(menuId));
    }

    @Test
    public void openSettings() {
        openDrawerMenu(R.id.nav_settings);
    }

    @Test
    public void openAbout() {
        openDrawerMenu(R.id.nav_about);
    }

    @Test
    public void openStart() {
        openDrawerMenu(R.id.nav_start);
        Fragment f = mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag(StartFragment.TAG);
        assertTrue(f != null);
    }
}
