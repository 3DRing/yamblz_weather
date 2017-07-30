package tljfn.yamblzweather;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.R;

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
        onView(withId(R.id.temp_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.city_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_iv)).check(matches(isDisplayed()));
        onView(withId(R.id.last_update_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.description_tv)).check(matches(isDisplayed()));
    }
}
