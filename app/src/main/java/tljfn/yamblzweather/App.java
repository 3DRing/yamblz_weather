package tljfn.yamblzweather;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.evernote.android.job.JobManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import tljfn.yamblzweather.di.AppInjector;
import tljfn.yamblzweather.scheduler.WeatherUpdateJobCreator;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class App extends Application implements HasActivityInjector, HasServiceInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
        startUpdateWeatherJob();
    }

    private void startUpdateWeatherJob() {
        JobManager.create(this).addJobCreator(new WeatherUpdateJobCreator());
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }
}
