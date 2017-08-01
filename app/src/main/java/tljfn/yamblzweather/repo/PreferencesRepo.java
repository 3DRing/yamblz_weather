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

package tljfn.yamblzweather.repo;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.BaseFields;

/**
 * Repository that handles User objects.
 */
public class PreferencesRepo {

    static final long DEFAULT_CITY = 524901; // moscow, russia

    static final String KEY_INTERVAL = "keyInterval";
    static final String KEY_CURRENT_CITY = "keyCurrentCity";
    private final SharedPreferences preferences;

    public PreferencesRepo(Context context) {
        this.preferences = context.getSharedPreferences(BaseFields.PREFERENCES_NAME, 0);
    }

    /**
     * @return value that represents time interval in seconds for updating weather
     */
    public Single<Integer> getInterval() {
        return Single.fromCallable(() -> preferences.getInt(KEY_INTERVAL, 60));
    }

    /**
     * @param seconds the new seconds interval for weather updating
     */
    public Completable setInterval(Integer seconds) {
        return Completable.fromAction(() ->
                preferences.edit().putInt(KEY_INTERVAL, seconds).apply());
    }

    public Completable updateCurrentCity(long id) {
        return Completable.fromAction(() ->
                preferences.edit().putLong(KEY_CURRENT_CITY, id).apply())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Long> getCurrentCity() {
        return Single.fromCallable(() -> preferences.getLong(KEY_CURRENT_CITY, DEFAULT_CITY));
    }
}
