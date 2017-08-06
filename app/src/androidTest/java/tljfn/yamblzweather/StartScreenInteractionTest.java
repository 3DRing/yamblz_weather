package tljfn.yamblzweather;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tljfn.yamblzweather.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.AnyOf.anyOf;

/**
 * Created by ringov on 30.07.17.
 */

@RunWith(AndroidJUnit4.class)
public class StartScreenInteractionTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void chooseCityClickOnTv() {
        onView(allOf(withId(R.id.fragment_container), isDisplayed()));
        onView(withId(R.id.city_tv)).perform(ViewActions.click());
        onView(anyOf(withId(R.id.fragment_container), not(isDisplayed())));
    }
}
