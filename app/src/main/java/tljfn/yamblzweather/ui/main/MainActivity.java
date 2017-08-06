package tljfn.yamblzweather.ui.main;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.db.cities.DBCity;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.scheduler.WeatherUpdateJob;
import tljfn.yamblzweather.ui.base.activity.ViewModelActivity;
import tljfn.yamblzweather.ui.base.fragment.BaseFragment;
import tljfn.yamblzweather.navigation.NavigationController;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.repo.PreferencesRepo;

// todo refactoring of this god activity
public class MainActivity extends ViewModelActivity<MainViewModel, UIMainData> implements LifecycleRegistryOwner,
        NavigationView.OnNavigationItemSelectedListener,
        BaseFragment.OnFragmentInteractionListener {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    PreferencesRepo preferencesRepo;
    @Inject
    DatabaseRepo dbRepo;
    @Inject
    RemoteRepo remoteRepo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private ActionBar actionBar;
    private ActionBarDrawerToggle toggle;

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        initDBIfNot();
        setScheduler();

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            NavigationController.navigateToWeather(R.id.fragment_container, getSupportFragmentManager());
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void initDBIfNot() {
        if (preferencesRepo.isFirstLaunch()) {
            remoteRepo.getAllCities()
                    .flatMapObservable(Observable::fromIterable)
                    .map(DBCity::fromRawCity)
                    .toList()
                    .flatMap(cities -> dbRepo.initCities(cities.toArray(new DBCity[cities.size()])))
                    .flatMapCompletable(success -> preferencesRepo.setFirstLaunch(false))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                    }, this::onError);
        }
    }

    private void onError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void setScheduler() {
        WeatherUpdateJob.schedule(preferencesRepo);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_settings:
                NavigationController.navigateToSettings(R.id.fragment_container, getSupportFragmentManager());
                break;
            case R.id.nav_about:
                NavigationController.navigateToAbout(R.id.fragment_container, getSupportFragmentManager());
                break;
            case R.id.nav_weather:
                NavigationController.navigateToWeather(R.id.fragment_container, getSupportFragmentManager());
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(@StringRes int titleRes, Integer drawerMode) {
        actionBar.setTitle(titleRes);
        drawer.setDrawerLockMode(drawerMode);
        boolean isDrawerLocked = drawerMode == DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        actionBar.setDisplayHomeAsUpEnabled(isDrawerLocked);
        if (isDrawerLocked) {
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        } else {
            toggle.syncState();
            toolbar.setNavigationOnClickListener(v -> drawer.openDrawer(GravityCompat.START));
        }
    }

    @Override
    public Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(@NonNull UIMainData data) {

    }

    @Override
    public void onError(String errorMessage) {

    }
}
