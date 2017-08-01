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

package tljfn.yamblzweather.ui.weather;

import javax.inject.Inject;

import tljfn.yamblzweather.api.data.RawWeather;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.ui.base.BaseViewModel;
import tljfn.yamblzweather.ui.weather.data.UIWeatherData;

@SuppressWarnings("WeakerAccess") //for dagger
public class WeatherViewModel extends BaseViewModel<UIWeatherData> {

    private final DatabaseRepo databaseRepo;
    private final RemoteRepo remoteRepo;
    private final PreferencesRepo preferencesRepo;

    @Inject
    public WeatherViewModel(RemoteRepo remoteRepo, DatabaseRepo databaseRepo, PreferencesRepo preferencesRepo) {
        this.databaseRepo = databaseRepo;
        this.remoteRepo = remoteRepo;
        this.preferencesRepo = preferencesRepo;

        loadCachedWeather();
    }

    public void loadCachedWeather() {
        sub(databaseRepo.loadCachedWeather()
                .subscribe(this::onChange, this::onError));
    }

    public void updateWeather() {
        preferencesRepo.getCurrentCity()
                .flatMap(remoteRepo::getWeather)
                .flatMap(databaseRepo::insertOrUpdateWeather)
                .subscribe(this::onChange, this::onError);
    }

    public void changeCity(double lat, double lon) {
        remoteRepo.getWeather(lat, lon)
                .doOnSubscribe(sub -> showLoading())
                .doOnSuccess(this::updateCurrentCity)
                .flatMap(databaseRepo::insertOrUpdateWeather)
                .subscribe(this::onChange, this::onError);
    }

    private void updateCurrentCity(RawWeather weatherMap) {
        preferencesRepo.updateCurrentCity(weatherMap.getId())
                .subscribe();
    }

    @Override
    protected UIWeatherData buildUIError(String message) {
        return new UIWeatherData.Builder().error(message).build();
    }
}
