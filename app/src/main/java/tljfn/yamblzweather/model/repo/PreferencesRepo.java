/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tljfn.yamblzweather.model.repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.model.scheduler.WeatherUpdateJob;

/**
 * Repository that handles User objects.
 */
public class PreferencesRepo {

    static final long DEFAULT_CITY = 524901; // moscow, russia

    private static final String FIRST_LAUNCH_KEY = "first_launch";

    private String intervalKey;
    private String intervalDefaultValue;

    private String notificationsKey;
    private String notificationsDefaultValue;

    private String currentCityKey;
    private String currentCityDefaultValue;

    private final SharedPreferences preferences;

    public PreferencesRepo(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);

        intervalKey = context.getString(R.string.update_intervals_key);
        intervalDefaultValue = context.getString(R.string.default_update_intervals_value);

        notificationsKey = context.getString(R.string.update_notifications_key);
        notificationsDefaultValue = context.getString(R.string.update_notifications_default);
    }

/*    @Deprecated
    private Single<Long> getUpdateInterval() {
        return Single.fromCallable(() -> {
            String value = preferences.getString(intervalKey, intervalDefaultValue);
            int minutes = Integer.parseInt(value);
            return TimeUnit.MINUTES.toMillis(minutes);
        });
    }*/

    public boolean isNotificationEnabled() {
        final boolean DEFAULT_VALUE = Boolean.parseBoolean(notificationsDefaultValue);
        return preferences.getBoolean(notificationsKey, DEFAULT_VALUE);
    }

    /**
     * @return value that represents time interval in seconds for updating weather
     */
    @Deprecated
    public Single<Integer> getInterval() {
        return Single.fromCallable(() -> preferences.getInt(intervalKey, 60));
    }

    /**
     * @param seconds the new seconds interval for weather updating
     */
    @Deprecated
    public Completable setInterval(Integer seconds) {
        return Completable.fromAction(() ->
                preferences.edit().putInt(intervalKey, seconds).apply());
    }

    public Completable updateCurrentCity(long id) {
        return Completable.fromAction(() ->
                preferences.edit().putLong(currentCityKey, id).apply())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Long> getCurrentCity() {
        return Single.fromCallable(() -> preferences.getLong(currentCityKey, DEFAULT_CITY));
    }

    public void onPreferencesChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(intervalKey)) {
            onChangingUpdateInterval(sharedPreferences);
        } else {
            // for other preferences
        }
    }

    private void onChangingUpdateInterval(SharedPreferences sp) {
        Set<JobRequest> requests = JobManager.instance().getAllJobRequestsForTag(WeatherUpdateJob.TAG);
        if (!requests.isEmpty()) {
            Iterator<JobRequest> iterator = requests.iterator();
            if (iterator.hasNext()) {
                getUpdateInterval(sp)
                        .subscribe(interval -> {
                            while (iterator.hasNext()) {
                                JobRequest jr = iterator.next();
                                long lastInterval = jr.getIntervalMs();
                                if (lastInterval != interval) {
                                    jr.cancelAndEdit().setPeriodic(interval).build();
                                }
                            }
                        });
            }
        }
    }

    public Single<Long> getUpdateInterval(SharedPreferences sharedPreferences) {
        return Single.fromCallable(() -> {
            String value = sharedPreferences.getString(intervalKey, intervalDefaultValue);
            int minutes = Integer.parseInt(value);
            return TimeUnit.MINUTES.toMillis(minutes);
        });
    }

    public Single<Long> getUpdateInterval() {
        return Single.fromCallable(() -> {
            String value = preferences.getString(intervalKey, intervalDefaultValue);
            int minutes = Integer.parseInt(value);
            return TimeUnit.MINUTES.toMillis(minutes);
        });
    }

    public Completable setFirstLaunch(boolean isFirst) {
        return Completable.fromAction(() -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(FIRST_LAUNCH_KEY, isFirst).apply();
        });
    }

    public boolean isFirstLaunch() {
        boolean isFirst = preferences.getBoolean(FIRST_LAUNCH_KEY, true);
        return isFirst;
    }
}
