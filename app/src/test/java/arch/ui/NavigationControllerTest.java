package arch.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.verification.VerificationMode;

import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.about.AboutFragment;
import tljfn.yamblzweather.ui.city_search.ChooseCityFragment;
import tljfn.yamblzweather.ui.settings.SettingsFragment;
import tljfn.yamblzweather.ui.start.StartFragment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 28.07.17.
 */
public class NavigationControllerTest {

    MainActivity activity;
    FragmentManager manager;
    FragmentTransaction transaction;
    StartFragment fragment;

    NavigationController controller;

    @Before
    public void setup() {
        activity = mock(MainActivity.class);
        manager = mock(FragmentManager.class);
        transaction = mock(FragmentTransaction.class);

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
/*
    @Test
    public void navigateToChooseCity() throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        fragment = mock(StartFragment.class);
        Context context = mock(Context.class);
        when(fragment.getContext()).thenReturn(context);
        Intent intent = mock(Intent.class);
        when(intent.getPackage()).thenReturn("com.test.yamblzweather");
        when(manager.findFragmentByTag(StartFragment.TAG)).thenReturn(fragment);
        when(transaction.replace(R.id.fragment_container, fragment, StartFragment.TAG))
                .thenReturn(transaction);
        when(fragment.getActivity()).thenReturn(activity);
        //doReturn(activity).when(fragment).getActivity();
        PlaceAutocomplete.IntentBuilder ib = mock(PlaceAutocomplete.IntentBuilder.class);
        when(ib.build(activity)).thenReturn(intent);
        //doReturn(intent).when(ib).build(activity);
        //when(ib.build(activity)).thenReturn(intent);

        controller.navigateToChooseCity();

        verify(fragment).startActivityForResult(any(Intent.class), NavigationController.PLACE_AUTOCOMPLETE_REQUEST_CODE);
    }

    @Test
    public void navigateToChooseCityException() throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        fragment = mock(StartFragment.class);
        when(manager.findFragmentByTag(StartFragment.TAG)).thenReturn(fragment);
        when(transaction.replace(eq(R.id.fragment_container), any(StartFragment.class), eq(StartFragment.TAG)))
                .thenReturn(transaction);
        when(fragment.getActivity()).thenReturn(activity);
        PlaceAutocomplete.IntentBuilder ib = mock(PlaceAutocomplete.IntentBuilder.class);
        when(ib.build(activity)).thenThrow(new GooglePlayServicesNotAvailableException(0));

        controller.navigateToChooseCity();

        verify(fragment, never()).startActivityForResult(any(Intent.class), NavigationController.PLACE_AUTOCOMPLETE_REQUEST_CODE);
    }*/

}