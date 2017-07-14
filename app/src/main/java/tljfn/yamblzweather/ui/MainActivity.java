package tljfn.yamblzweather.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.about.AboutFragment;
import tljfn.yamblzweather.ui.settings.SettingsFragment;
import tljfn.yamblzweather.ui.start.StartFragment;
import utils.BaseFragment;
import utils.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        OnFragmentInteractionListener {

    DrawerLayout drawer;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initNavigationDrawer(toolbar);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            pushFragment(StartFragment.class);
        }
    }

    private void initNavigationDrawer(Toolbar toolbar) {
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_settings:
                pushFragment(SettingsFragment.class);
                break;
            case R.id.nav_about:
                pushFragment(AboutFragment.class);
                break;
            case R.id.nav_start:
                while (!fragmentManager.findFragmentByTag(StartFragment.getFragmentTag()).isVisible()) {
                    fragmentManager.popBackStackImmediate();
                }
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This method uses reflection API to get static field "getFragmentTag()"
     */
    private void pushFragment(Class<? extends BaseFragment> fragmentClass) {
        try {
            Method method;
            method = fragmentClass.getDeclaredMethod("getFragmentTag");
            String tag = method != null ? method.invoke(null).toString() : null;
            if (fragmentManager.findFragmentByTag(tag) == null) {
                // FragmentManager doesn`t have necessary object of fragment,
                // so we have to create new one. We store only 2 fragment in backstack.
                int backStackEntryCount = fragmentManager.getBackStackEntryCount();
                while (backStackEntryCount > 1) {
                    fragmentManager.popBackStack();
                    backStackEntryCount--;
                }
                BaseFragment fragment = fragmentClass.newInstance();
                fragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.fragment_container, fragment, tag)
                        .addToBackStack(tag)
                        .commit();
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException
                | InstantiationException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setTitle(title);
    }
}
