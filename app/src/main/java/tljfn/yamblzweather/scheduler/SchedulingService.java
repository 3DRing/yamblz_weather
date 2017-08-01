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
import tljfn.yamblzweather.api.data.RawWeather;
import tljfn.yamblzweather.di.AppInjector;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.RemoteRepo;

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
/*        remoteRepo.getWeather("Москва")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(RawWeather::updateTime)
                .map(RawWeather::setRefreshed)
                .doOnSuccess(this::updateDatabase)
                .subscribe();

        AlarmReceiver.completeWakefulIntent(intent);*/
    }

    private void updateDatabase(RawWeather weatherMap) {
/*        databaseRepo.insertOrUpdateWeather(weatherMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();*/
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }
}
