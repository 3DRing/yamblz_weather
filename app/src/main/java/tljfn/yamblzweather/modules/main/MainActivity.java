package tljfn.yamblzweather.modules.main;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.content.res.Configuration;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.scheduler.WeatherUpdateJob;
import tljfn.yamblzweather.modules.base.activity.ViewModelActivity;
import tljfn.yamblzweather.modules.base.fragment.BaseFragment;
import tljfn.yamblzweather.modules.city.favorite.FavoriteCitiesFragment;
import tljfn.yamblzweather.navigation.NavigationController;

public class MainActivity extends ViewModelActivity<MainViewModel, UIMainData> implements LifecycleRegistryOwner,
        NavigationView.OnNavigationItemSelectedListener,
        BaseFragment.OnFragmentInteractionListener {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    ViewModelFactory factory;
    @Inject
    PreferencesRepo preferencesRepo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Nullable
    @BindView(R.id.additional_container)
    ViewGroup otherContainer;

    @BindView(R.id.search_container)
    View searchContainer;

    private ActionBar actionBar;
    private ActionBarDrawerToggle toggle;

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeCrtOrientation();

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            if (isTwoPane()) {
                NavigationController.navigateToForecast(R.id.additional_container, getSupportFragmentManager());
            }
            NavigationController.navigateToChooseCity(R.id.search_container, getSupportFragmentManager());
            NavigationController.navigateToWeather(R.id.fragment_container, getSupportFragmentManager());
            hideSearch();
        }

        setScheduler();
    }

    private void setScheduler() {
        WeatherUpdateJob.schedule(preferencesRepo);
    }

    @Override
    protected void inject() {
        App.getComponent().inject(this);
    }

    @Override
    protected ViewModelFactory getViewModelFactory() {
        return factory;
    }

    private void storeCrtOrientation() {
        getViewModel().setCrtOrientation(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    private boolean isTwoPane() {
        return getResources().getBoolean(R.bool.twoPaneMode);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void hideSearch() {
        searchContainer.setVisibility(View.GONE);
    }

    private void showSearch() {
        searchContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int container = isTwoPane() ? R.id.additional_container : R.id.fragment_container;

        switch (item.getItemId()) {
            case R.id.nav_settings:
                NavigationController.navigateToSettings(container, getSupportFragmentManager());
                hideSearch();
                break;
            case R.id.nav_about:
                NavigationController.navigateToAbout(container, getSupportFragmentManager());
                hideSearch();
                break;
            case R.id.nav_weather:
                NavigationController.navigateToWeather(R.id.fragment_container, getSupportFragmentManager());
                hideSearch();
                break;
            case R.id.nav_forecast:
                NavigationController.navigateToForecast(container, getSupportFragmentManager());
                hideSearch();
                break;
            case R.id.nav_city:
                NavigationController.navigateToFavoriteCity(container, getSupportFragmentManager());
                showSearch();
                break;
            default:
                throw new IllegalStateException("No such menu is declared: " + item.getItemId());
        }

        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(@StringRes int titleRes, Integer drawerMode) {
        actionBar.setTitle(titleRes);
        if (drawer == null) {
            return;
        }
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
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getViewModel().setCrtOrientation(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSupportFragmentManager().findFragmentByTag(FavoriteCitiesFragment.TAG) == null) {
            hideSearch();
        } else {
            showSearch();
        }
    }
}
