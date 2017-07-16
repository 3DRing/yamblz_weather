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

import static tljfn.yamblzweather.BaseFields.PREFERENCES_NAME;

/**
 * Repository that handles User objects.
 */
public class PreferencesRepo {

    private static final String KEY_INTERVAL = "keyInterval";
    private final SharedPreferences preferences;

    public PreferencesRepo(Context context) {
        this.preferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
    }

    /**
     * @return value that represents time interval in seconds for updating weather
     */
    public Single<Integer> getInterval() {
        return Single.fromCallable(() -> preferences.getInt(KEY_INTERVAL, 0));
    }

    /**
     * @param interval the new interval for weather updating
     */
    public Completable setInterval(Integer interval) {
        return Completable.fromAction(() -> preferences.edit().putInt(KEY_INTERVAL, interval).commit());
    }
}
