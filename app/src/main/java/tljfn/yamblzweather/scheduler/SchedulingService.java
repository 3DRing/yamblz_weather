package tljfn.yamblzweather.scheduler;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasServiceInjector;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.di.AppInjector;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.vo.weather.WeatherMap;

public class SchedulingService extends IntentService implements HasServiceInjector {
    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;

    @Inject
    RemoteRepo remoteRepo;
    @Inject
    DatabaseRepo databaseRepo;

    @Inject
    public SchedulingService() {
        super("SchedulingService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init((App) getApplication());
        AndroidInjection.inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        remoteRepo.getWeather("Москва")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(WeatherMap::setUpdateTime)
                .map(WeatherMap::setRefreshed)
                .doOnSuccess(databaseRepo::insertOrUpdateWeather)
                .subscribe();
        // Release the wake lock provided by the BroadcastReceiver.
        AlarmReceiver.completeWakefulIntent(intent);
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }
}
