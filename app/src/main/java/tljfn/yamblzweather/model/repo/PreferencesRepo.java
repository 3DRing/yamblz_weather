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

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.model.scheduler.WeatherUpdateJob;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

/**
 * Repository that handles User objects.
 */
public class PreferencesRepo {

    static final long DEFAULT_CITY = 524901; // moscow, russia

    static final String FIRST_LAUNCH_KEY = "first_launch";
    static final String CURRENT_CITY_ID_KEY = "current_city_id";

    String intervalKey;
    String intervalDefaultValue;

    String notificationsKey;
    String notificationsDefaultValue;

    String currentCityKey;
    String currentCityDefaultValue;

    private final SharedPreferences preferences;

    BehaviorSubject<Long> currentIdChanges;

    @Inject
    public PreferencesRepo(Context context, SharedPreferences sp) {
        this.preferences = sp;

        intervalKey = context.getString(R.string.update_intervals_key);
        intervalDefaultValue = context.getString(R.string.default_update_intervals_value);

        notificationsKey = context.getString(R.string.update_notifications_key);
        notificationsDefaultValue = context.getString(R.string.update_notifications_default);

        currentIdChanges = BehaviorSubject.create();
        currentIdChanges.onNext(getCurrentCityId());
    }

    private long getCurrentCityId() {
        long crtId = preferences.getLong(CURRENT_CITY_ID_KEY, DEFAULT_CITY);
        return crtId;
    }

    public boolean isNotificationEnabled() {
        final boolean DEFAULT_VALUE = Boolean.parseBoolean(notificationsDefaultValue);
        return preferences.getBoolean(notificationsKey, DEFAULT_VALUE);
    }

    public Flowable<Long> updateCurrentCity(long id) {
        preferences.edit().putLong(CURRENT_CITY_ID_KEY, id).apply();
        currentIdChanges.onNext(id);
        return currentIdChanges.toFlowable(BackpressureStrategy.LATEST);
    }

    public Flowable<Long> getCurrentCity() {
        return Flowable.just(currentIdChanges.getValue());
    }

    public void onPreferencesChanged(String s) {
        if (s.equals(intervalKey)) {
            onChangingUpdateInterval();
        } else {
            // for other preferences
        }
    }

    private void onChangingUpdateInterval() {
        Set<JobRequest> requests = JobManager.instance().getAllJobRequestsForTag(WeatherUpdateJob.TAG);
        if (!requests.isEmpty()) {
            Iterator<JobRequest> iterator = requests.iterator();
            if (iterator.hasNext()) {
                getUpdateInterval()
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

    @Deprecated
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

    public Flowable<Long> subscribeToCityUpdate() {
        return currentIdChanges.toFlowable(BackpressureStrategy.BUFFER);
    }
}
