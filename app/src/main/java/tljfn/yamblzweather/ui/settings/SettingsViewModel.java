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

package tljfn.yamblzweather.ui.settings;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.repo.PreferencesRepo;

public class SettingsViewModel extends ViewModel {

    private final PreferencesRepo preferencesRepo;

    public MutableLiveData<Integer> interval = new MutableLiveData<>();

    @Inject
    public SettingsViewModel(PreferencesRepo preferencesRepo, Context context) {
        this.preferencesRepo = preferencesRepo;

        loadInterval()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(interval::setValue);
    }

    /**
     * Load the interval preference.
     *
     * @return a {@link Single} that contains interval in seconds from Shared Preferences
     */
    public Single<Integer> loadInterval() {
        return preferencesRepo.getInterval();
    }

    /**
     * Save the interval preference.
     *
     * @param interval the new interval for weather updating
     * @return a {@link Completable} that completes when the user name is updated
     */
    public Completable saveInterval(final Integer interval) {
        this.interval.setValue(interval);
        return preferencesRepo.setInterval(interval);
    }
}
