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

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.api.data.RawWeather;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.ui.base.BaseViewModel;
import tljfn.yamblzweather.ui.weather.data.UIWeatherData;
import tljfn.yamblzweather.ui.weather.data.WeatherConverter;

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

        updateWeather();

        // caching is disabled because of some bug that was not caught so far
        // and caused bad ux experience
/*        disposable.add(getLiveData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liveData::setValue, this::onError));*/
    }

    /**
     * Get the liveData from db.
     */
/*    public Flowable<RawWeather> getLiveData() {
        return databaseRepo.getWeather();
    }*/
    public void updateWeather() {
        preferencesRepo.getCurrentCity()
                .flatMap(remoteRepo::getWeather)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(this::updateDatabase)
                .map(WeatherConverter::toUIData)
                .subscribe(this::onChange, this::onError);
    }

    public void changeCity(double lat, double lon) {
        remoteRepo.getWeather(lat, lon)
                .doOnSuccess(this::updateCurrentCity)
                .doOnSuccess(this::updateDatabase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(WeatherConverter::toUIData)
                .subscribe(this::onChange, this::onError);
    }

    private void updateCurrentCity(RawWeather weatherMap) {
        preferencesRepo.updateCurrentCity(weatherMap.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    void updateDatabase(RawWeather weatherMap) {
        // caching is disabled because of some bug that was not caught so far
        // and caused bad ux experience
/*        databaseRepo.insertOrUpdateWeather(weatherMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();*/
    }

    @Override
    protected UIWeatherData buildUIError(String message) {
        return new UIWeatherData.Builder().error(message).build();
    }
}
