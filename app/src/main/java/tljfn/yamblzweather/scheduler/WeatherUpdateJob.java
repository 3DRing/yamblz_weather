package tljfn.yamblzweather.scheduler;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.Utils;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.ui.weather.data.UIWeatherData;

/**
 * Created by ringov on 15.07.17.
 */

public class WeatherUpdateJob extends Job {

    public static final String TAG = "com.ringov.yamblzweather.weather_update_job";
    public static final int FLEX = 300000;

    private int notificationId = 0;

    @Inject
    PreferencesRepo preferencesRepo;
    @Inject
    RemoteRepo remoteRepo;
    @Inject
    DatabaseRepo dbRepo;

    public static void schedule(@NonNull PreferencesRepo preferencesRepo) {

        preferencesRepo.getUpdateInterval()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(interval -> {
                    JobRequest job = new JobRequest.Builder(WeatherUpdateJob.TAG)
                            .setPeriodic(interval, FLEX)
                            .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                            .setPersisted(true)
                            .setUpdateCurrent(true)
                            .build();
                    job.schedule();
                }); // error is ignored

    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        App.getComponent().inject(this);

        preferencesRepo.getCurrentCity()
                .flatMap(remoteRepo::getWeather)
                .flatMap(dbRepo::insertOrUpdateWeather)
                .filter(weather -> preferencesRepo.isNotificationEnabled())
                .subscribe(this::sendWeatherNotification, this::handleError);
        return Result.SUCCESS;
    }

    private void sendWeatherNotification(UIWeatherData weather) {
        String formattedTemperature = Utils.getFormattedTemperature(getContext(), weather.getTemperature());
        String condition = getContext().getString(0);//weather.getConditionName());
        String content = getContext().getString(R.string.notification_message, formattedTemperature, condition);

        PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
                new Intent(getContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(getContext())
                .setContentTitle(getContext().getString(R.string.app_name))
                .setContentIntent(pi)
                .setContentText(content)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)//weather.getConditionImage())
                .setShowWhen(true)
                .setLocalOnly(true)
                .build();
        NotificationManagerCompat.from(getContext())
                .notify(notificationId, notification);
    }

    private void handleError(Throwable throwable) {

    }
}
