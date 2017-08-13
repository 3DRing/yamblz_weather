package tljfn.yamblzweather.modules.main;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Completable;
import io.reactivex.Single;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment.TestLifecycleOwner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 13.08.17.
 */
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    TestLifecycleOwner owner;
    MainViewModel viewModel;

    DataProvider dp;

    @Mock
    PreferencesRepo preferencesRepo;
    @Mock
    DatabaseRepo databaseRepo;
    @Mock
    RemoteRepo remoteRepo;

    @Before
    public void setup() {
        owner = new TestLifecycleOwner();
        viewModel = new MainViewModel(preferencesRepo, databaseRepo, remoteRepo);
        dp = new DataProvider();
    }

    @Test
    public void building_error_data() {
        UIMainData data = viewModel.buildUIError("error");
        assertTrue(data.equals(new UIMainData.Builder().error("error").build()));
    }

    @Test
    public void orientation_changes() {
        viewModel.setCrtOrientation(true);
        verify(preferencesRepo).setCrtOrientation(true);
        when(preferencesRepo.isLandscapeOrientation()).thenReturn(true);
    }

    @Test
    public void initializing_db_with_cities() {
        when(preferencesRepo.isFirstLaunch()).thenReturn(true);
        when(remoteRepo.getAllCities()).thenReturn(Single.fromCallable(() -> dp.getAllCities()));
        when(preferencesRepo.setFirstLaunch(false)).thenReturn(Completable.complete());

        viewModel = new MainViewModel(preferencesRepo, databaseRepo, remoteRepo);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(preferencesRepo, atLeastOnce()).isFirstLaunch();
        verify(remoteRepo).getAllCities();
        verify(databaseRepo).initCities(any());
    }
}