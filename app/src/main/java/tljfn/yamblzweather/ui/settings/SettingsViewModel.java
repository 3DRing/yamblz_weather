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

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.internal.operators.completable.CompletableFromAction;
import tljfn.yamblzweather.repo.PreferencesRepo;

public class SettingsViewModel extends ViewModel {

    private final PreferencesRepo preferencesRepo;

    @Inject
    public SettingsViewModel(PreferencesRepo preferencesRepo) {
        this.preferencesRepo = preferencesRepo;
    }

    /**
     * Update the interval preference.
     *
     * @param interval the new interval for weather updating
     * @return a {@link Completable} that completes when the user name is updated
     */
    public Completable updateInterval(final Integer interval) {
        return new CompletableFromAction(() -> preferencesRepo.setInterval(interval));
    }
}
