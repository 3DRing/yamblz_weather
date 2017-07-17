package tljfn.yamblzweather.scheduler;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.AppInjector;
import tljfn.yamblzweather.di.Injectable;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.vo.weather.WeatherMap;

/**
 * This {@code IntentService} does the app's actual work.
 * {@code AlarmReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class SchedulingService extends IntentService implements Injectable {
    public static final String TAG = "Scheduling Demo";
    // An ID used to post the notification.
    public static final int NOTIFICATION_ID = 1;
    // The string the app searches for in the Google home page content. If the app finds
    // the string, it indicates the presence of a doodle.
    public static final String SEARCH_STRING = "doodle";
    // The Google home page URL from which the app fetches content.
    // You can find a list of other Google domains with possible doodles here:
    // http://en.wikipedia.org/wiki/List_of_Google_domains
    public static final String URL = "http://www.google.com";
    NotificationCompat.Builder builder;
    RemoteRepo remoteRepo;
    DatabaseRepo databaseRepo;
    private NotificationManager notificationManager;

    @Inject
    public SchedulingService(RemoteRepo remoteRepo, DatabaseRepo databaseRepo) {
        super("SchedulingService");
        this.databaseRepo = databaseRepo;
        this.remoteRepo = remoteRepo;
    }

    public SchedulingService() {
        super("SchedulingService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
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
        sendNotification("Woow");
        // Release the wake lock provided by the BroadcastReceiver.
        AlarmReceiver.completeWakefulIntent(intent);
    }

    // Post a notification indicating whether a doodle was found.
    private void sendNotification(String msg) {
        notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        long[] pattern = {160, 320, 640};

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_menu_gallery)
                        .setContentTitle(getString(R.string.app_name))
                        .setVibrate(pattern)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

//
// The methods below this line fetch content from the specified URL and return the
// content as a string.
//

    /**
     * Given a URL string, initiate a fetch operation.
     */
    private String loadFromNetwork(String urlString) throws IOException {
        InputStream stream = null;
        String str = "";

        try {
            stream = downloadUrl(urlString);
            str = readIt(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return str;
    }

    /**
     * Given a string representation of a URL, sets up a connection and gets
     * an input stream.
     *
     * @param urlString A string representation of a URL.
     * @return An InputStream retrieved from a successful HttpURLConnection.
     * @throws IOException
     */
    private InputStream downloadUrl(String urlString) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Start the query
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }

    /**
     * Reads an InputStream and converts it to a String.
     *
     * @param stream InputStream containing HTML from www.google.com.
     * @return String version of InputStream.
     * @throws IOException
     */
    private String readIt(InputStream stream) throws IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        for (String line = reader.readLine(); line != null; line = reader.readLine())
            builder.append(line);
        reader.close();
        return builder.toString();
    }
}
