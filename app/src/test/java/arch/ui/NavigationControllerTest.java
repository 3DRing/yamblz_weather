package arch.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.navigation.NavigationController;
import tljfn.yamblzweather.ui.about.AboutFragment;
import tljfn.yamblzweather.ui.settings.SettingsFragment;
import tljfn.yamblzweather.ui.start.StartFragment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 28.07.17.
 */
public class NavigationControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    MainActivity activity;
    @Mock
    FragmentManager manager;
    @Mock
    FragmentTransaction transaction;
    @Mock
    StartFragment fragment;

    NavigationController controller;

    @Before
    public void setup() {
        when(activity.getSupportFragmentManager()).thenReturn(manager);
        when(manager.beginTransaction()).thenReturn(transaction);

        when(transaction.addToBackStack(null)).thenReturn(transaction);

        controller = new NavigationController(activity);
    }

    @Test
    public void navigateToAbout() {
        when(transaction.replace(eq(R.id.fragment_container), any(AboutFragment.class)))
                .thenReturn(transaction);

        controller.navigateToAbout();
        verify(manager).beginTransaction();
        verify(transaction).replace(eq(R.id.fragment_container), any(AboutFragment.class));
        verify(transaction).addToBackStack(null);
        verify(transaction).commitAllowingStateLoss();
    }

    @Test
    public void navigateToSettings() {
        when(transaction.replace(eq(R.id.fragment_container), any(SettingsFragment.class)))
                .thenReturn(transaction);

        controller.navigateToSettings();
        verify(manager).beginTransaction();
        verify(transaction).replace(eq(R.id.fragment_container), any(SettingsFragment.class));
        verify(transaction).addToBackStack(null);
        verify(transaction).commitAllowingStateLoss();
    }

    @Test
    public void navigateToStart() {
        when(transaction.replace(eq(R.id.fragment_container), any(StartFragment.class), eq(StartFragment.TAG)))
                .thenReturn(transaction);

        controller.navigateToStart();
        verify(manager).beginTransaction();
        verify(transaction).replace(eq(R.id.fragment_container), any(StartFragment.class), eq(StartFragment.TAG));
        verify(transaction).addToBackStack(null);
        verify(transaction).commitAllowingStateLoss();
    }

}