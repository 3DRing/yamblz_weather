package tljfn.yamblzweather.scheduler;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;

/**
 * Created by ringov on 15.07.17.
 */

public class WeatherUpdateJob extends Job {

    public static final String TAG = "com.ringov.yamblzweather.weather_update_job";
    public static final int FLEX = 300000;

    private int notificationId = 0;

    public static void schedule() {
/*        long interval = Database.getInstance().getUpdateInterval();
        JobRequest job = new JobRequest.Builder(WeatherUpdateJob.TAG)
                .setPeriodic(interval, FLEX)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setPersisted(true)
                .setUpdateCurrent(true)
                .build();
        job.schedule();*/
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
/*        App.getComponent().inject(this);
        weatherRepository.updateWeatherIfDataIsOld()
                .filter(w -> settings.isNotificationsEnabled())
                .subscribe(weatherInfo -> {
                    String formattedTemperature = Utils.getFormattedTemperature(getContext(), weatherInfo.getTemperature());
                    String condition = getContext().getString(weatherInfo.getConditionName());
                    String content = getContext().getString(R.string.notification_message, formattedTemperature, condition);

                    PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
                            new Intent(getContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
                    Notification notification = new NotificationCompat.Builder(getContext())
                            .setContentTitle(getContext().getString(R.string.app_name))
                            .setContentIntent(pi)
                            .setContentText(content)
                            .setAutoCancel(true)
                            .setSmallIcon(weatherInfo.getConditionImage())
                            .setShowWhen(true)
                            .setLocalOnly(true)
                            .build();
                    NotificationManagerCompat.from(getContext())
                            .notify(notificationId, notification);
                }, this::handleError);*/
        return Result.SUCCESS;
    }

    private void handleError(Throwable throwable) {

    }
}