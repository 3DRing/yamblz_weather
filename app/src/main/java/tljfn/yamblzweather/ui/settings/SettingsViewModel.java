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

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.ui.base.BaseViewModel;
import tljfn.yamblzweather.ui.base.UIBaseData;

public class SettingsViewModel extends BaseViewModel<UIBaseData> {

    private final PreferencesRepo preferencesRepo;

    @Inject
    public SettingsViewModel(PreferencesRepo preferencesRepo) {
        this.preferencesRepo = preferencesRepo;

/*        preferencesRepo.getInterval()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess()
                .subscribe(interval::setValue);*/
    }

    /**
     * Save the interval preference.
     *
     * @param interval the new interval for liveData updating
     * @return a {@link Completable} that completes when the user name is updated
     */
    public Completable saveInterval(final Integer interval) {
        //this.interval.setValue(interval);
        return preferencesRepo.setInterval(interval);
    }

    @Override
    public void observe(LifecycleOwner owner, Observer observer) {

    }
}
