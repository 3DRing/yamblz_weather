package tljfn.yamblzweather;

import android.app.Application;

import com.evernote.android.job.JobManager;

import tljfn.yamblzweather.di.app.DaggerNewAppComponent;
import tljfn.yamblzweather.di.app.NewAppComponent;
import tljfn.yamblzweather.di.repos.AppModule;
import tljfn.yamblzweather.di.repos.DatabaseModule;
import tljfn.yamblzweather.di.repos.DatabaseRepoModule;
import tljfn.yamblzweather.di.repos.PreferencesRepoModule;
import tljfn.yamblzweather.di.repos.RemoteRepoModule;
import tljfn.yamblzweather.di.repos.WeatherApiModule;
import tljfn.yamblzweather.di.repos.WeatherDaoModule;
import tljfn.yamblzweather.scheduler.WeatherUpdateJobCreator;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class App extends Application {


    private static NewAppComponent component;

    public static NewAppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startUpdateWeatherJob();
        component = buildComponent();
    }

    private void startUpdateWeatherJob() {
        JobManager.create(this).addJobCreator(new WeatherUpdateJobCreator());
    }

    protected NewAppComponent buildComponent() {
        return DaggerNewAppComponent.builder()
                .appModule(new AppModule(this.getApplicationContext()))
                .databaseModule(new DatabaseModule())
                .databaseRepoModule(new DatabaseRepoModule())
                .preferencesRepoModule(new PreferencesRepoModule())
                .remoteRepoModule(new RemoteRepoModule())
                .weatherApiModule(new WeatherApiModule())
                .weatherDaoModule(new WeatherDaoModule())
                .build();
    }
}
