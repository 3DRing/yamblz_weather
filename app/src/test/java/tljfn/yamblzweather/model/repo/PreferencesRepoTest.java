package tljfn.yamblzweather.model.repo;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.concurrent.TimeUnit;

import tljfn.yamblzweather.R;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tljfn.yamblzweather.model.repo.PreferencesRepo.FIRST_LAUNCH_KEY;

/**
 * Created by ringov on 27.07.17.
 */
public class PreferencesRepoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    SharedPreferences sp;
    @Mock
    SharedPreferences.Editor editor;
    @Mock
    Context context;

    PreferencesRepo repo;

    @Before
    public void setup() {
        when(context.getString(R.string.update_intervals_key)).thenReturn("update_intervals_key");
        when(context.getString(R.string.default_update_intervals_value)).thenReturn("120");
        when(context.getString(R.string.update_notifications_key)).thenReturn("update_notifications_key");
        when(context.getString(R.string.update_notifications_default)).thenReturn("false");

        when(sp.edit()).thenReturn(editor);

        repo = new PreferencesRepo(context, sp);
        verify(context).getString(R.string.default_update_intervals_value);
        verify(context).getString(R.string.update_intervals_key);
        verify(context).getString(R.string.update_notifications_key);
        verify(context).getString(R.string.update_notifications_default);
    }

    @Test
    public void is_notification_enabled_correct_false() {
        when(sp.getBoolean(repo.notificationsKey, false)).thenReturn(false);
        assertFalse(repo.isNotificationEnabled());
        verify(sp).getBoolean(repo.notificationsKey, false);
    }

    @Test
    public void is_notification_enabled_correct_true() {
        when(sp.getBoolean(repo.notificationsKey, false)).thenReturn(true);
        assertTrue(repo.isNotificationEnabled());
        verify(sp).getBoolean(repo.notificationsKey, false);
    }

    @Test
    public void is_first_launch_correct_true() {
        when(sp.getBoolean(FIRST_LAUNCH_KEY, true)).thenReturn(true);
        assertTrue(repo.isFirstLaunch());
        verify(sp).getBoolean(FIRST_LAUNCH_KEY, true);
    }

    @Test
    public void is_first_launch_correct_false() {
        when(sp.getBoolean(FIRST_LAUNCH_KEY, true)).thenReturn(false);
        assertFalse(repo.isFirstLaunch());
        verify(sp).getBoolean(FIRST_LAUNCH_KEY, true);
    }

    @Test
    public void setting_first_launch_correct_false() {
        when(editor.putBoolean(FIRST_LAUNCH_KEY, false)).thenReturn(editor);
        repo.setFirstLaunch(false).test()
                .assertNoErrors()
                .assertOf(voidTestObserver -> {
                    verify(editor).putBoolean(FIRST_LAUNCH_KEY, false);
                    verify(editor).apply();
                });
    }

    @Test
    public void setting_first_launch_correct_true() {
        when(editor.putBoolean(FIRST_LAUNCH_KEY, true)).thenReturn(editor);
        repo.setFirstLaunch(true).test()
                .assertNoErrors()
                .assertOf(voidTestObserver -> {
                    verify(editor).putBoolean(FIRST_LAUNCH_KEY, true);
                    verify(editor).apply();
                });

    }

    @Test
    public void getting_correct_update_interval() {
        when(sp.getString(repo.intervalKey, repo.intervalDefaultValue)).thenReturn("120");

        repo.getUpdateInterval(sp).test()
                .assertNoErrors()
                .assertValue(TimeUnit.MINUTES.toMillis(120));
    }

    @Test
    public void getting_correct_update_interval_other() {
        when(sp.getString(repo.intervalKey, repo.intervalDefaultValue)).thenReturn("120");

        repo.getUpdateInterval().test()
                .assertNoErrors()
                .assertValue(TimeUnit.MINUTES.toMillis(120))
                .assertOf(longTestObserver ->
                        verify(sp).getString(repo.intervalKey, repo.intervalDefaultValue));
    }

    @Test
    public void correct_getting_current_city() {
        when(sp.getLong(repo.currentCityKey, PreferencesRepo.DEFAULT_CITY)).thenReturn(12356L);

        repo.getCurrentCity().test()
                .assertNoErrors()
                .assertValue(12356L)
                .assertOf(longTestObserver ->
                        verify(sp).getLong(repo.currentCityKey, PreferencesRepo.DEFAULT_CITY));
    }

    @Test
    public void correct_updating_current_city() {
        when(editor.putLong(repo.currentCityKey, 600l)).thenReturn(editor);

        repo.updateCurrentCity(600l).test()
                .assertNoErrors()
                .assertOf(voidTestObserver -> {
                    verify(sp).edit();
                    verify(editor).putLong(repo.currentCityKey, 600l);
                    verify(editor).apply();
                });
    }

/*     @Test
    public void getInterval() throws Exception {
        when(sp.getInt(PreferencesRepo.KEY_INTERVAL, 60)).thenReturn(15);

        repo.getInterval().test()
                .assertNoErrors()
                .assertValue(15);
    }

    @Test
    public void setInterval() throws Exception {
        when(editor.putInt(PreferencesRepo.KEY_INTERVAL, 40)).thenReturn(editor);
        when(sp.getInt(PreferencesRepo.KEY_INTERVAL, 60)).thenReturn(40);

        repo.setInterval(40).test()
                .assertNoErrors()
                .assertComplete();

        verify(editor).putInt(PreferencesRepo.KEY_INTERVAL, 40);

        repo.getInterval().test()
                .assertNoErrors()
                .assertValue(40);
    }

    @Test
    public void updateCurrentCity() throws Exception {
        when(editor.putLong(PreferencesRepo.KEY_CURRENT_CITY, 20L))
                .thenReturn(editor);

        repo.updateCurrentCity(20L).test()
                .assertNoErrors()
                .assertComplete();

        verify(editor).putLong(PreferencesRepo.KEY_CURRENT_CITY, 20L);

        when(sp.getLong(PreferencesRepo.KEY_CURRENT_CITY, PreferencesRepo.DEFAULT_CITY))
                .thenReturn(20L);

        repo.getCurrentCity().test()
                .assertNoErrors()
                .assertValue(20L);
    }

    @Test
    public void getCurrentCity() throws Exception {
        when(sp.getLong(PreferencesRepo.KEY_CURRENT_CITY, PreferencesRepo.DEFAULT_CITY)).thenReturn(PreferencesRepo.DEFAULT_CITY);

        repo.getCurrentCity().test()
                .assertNoErrors()
                .assertValue(PreferencesRepo.DEFAULT_CITY);
    }*/

}