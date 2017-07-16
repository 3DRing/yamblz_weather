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

package tljfn.yamblzweather.ui.start;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.Flowable;
import tljfn.yamblzweather.db.Weather;
import tljfn.yamblzweather.repo.DatabaseRepo;

public class StartViewModel extends ViewModel {

    private final DatabaseRepo databaseRepo;

    @Inject
    public StartViewModel(DatabaseRepo databaseRepo) {
        this.databaseRepo = databaseRepo;
    }

    /**
     * Get the weather at the city.
     *
     * @return a {@link Flowable} that will emit every time the user name has been updated.
     */
    public Flowable<Integer> getWeather(String city) {
        return databaseRepo.getWeather()
                // for every emission of the weather, get the temperature
                .map(Weather::getValue);
    }
}
