package tljfn.yamblzweather.ui;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ringov on 30.07.17.
 */

@RunWith(AndroidJUnit4.class)
public class InitialStartInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void allElementsVisible() {
        onView(ViewMatchers.withId(R.id.tv_temperature)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_city)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_conditions)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_time)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_image)).check(matches(isDisplayed()));
    }
}
