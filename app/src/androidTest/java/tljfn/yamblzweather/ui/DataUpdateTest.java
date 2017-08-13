package tljfn.yamblzweather.ui;

import android.os.SystemClock;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;

/**
 * Created by ringov on 30.07.17.
 */

@RunWith(AndroidJUnit4.class)
public class DataUpdateTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public static ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
    }

    @Test
    public void dataUpdates() {
        onView(ViewMatchers.withId(R.id.swipe_layout))
                .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)));
        SystemClock.sleep(2000);
        assertFalse(((SwipeRefreshLayout) mActivityRule.getActivity()
                .findViewById(R.id.swipe_layout)).isRefreshing());
    }
}
