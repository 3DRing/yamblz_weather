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

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.vo.detailed_place_info.AddressComponent;
import tljfn.yamblzweather.vo.detailed_place_info.PlaceDetailedInfo;
import tljfn.yamblzweather.vo.weather.WeatherMap;

@SuppressWarnings("WeakerAccess") //for dagger
public class StartViewModel extends ViewModel {

    private final DatabaseRepo databaseRepo;
    private final RemoteRepo remoteRepo;
    private final PreferencesRepo preferencesRepo;
    public MutableLiveData<WeatherMap> weather = new MutableLiveData<>();

    @Inject
    public StartViewModel(RemoteRepo remoteRepo, DatabaseRepo databaseRepo, PreferencesRepo preferencesRepo) {
        this.databaseRepo = databaseRepo;
        this.remoteRepo = remoteRepo;
        this.preferencesRepo = preferencesRepo;
//        getWeather();
    }

    /**
     * Get the weather from db.
     */
    public Flowable<WeatherMap> getWeather() {
        return databaseRepo.getWeather();
    }

    public void updateWeather() {
        preferencesRepo.getCurrentCity()
                .flatMap(remoteRepo::getWeather)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(WeatherMap::updateTime)
                .map(WeatherMap::setRefreshed)
                .doOnSuccess(this::updateDatabase)
                .subscribe(weather::setValue, this::onError);
    }

    public void changeCity(double lat, double lon) {
        remoteRepo.getWeather(lat, lon)
                .map(WeatherMap::updateTime)
                .map(WeatherMap::setRefreshed)
                .doOnSuccess(this::updateCurrentCity)
                .doOnSuccess(this::updateDatabase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather::setValue, this::onError);
    }

    private void updateCurrentCity(WeatherMap weatherMap) {
        preferencesRepo.updateCurrentCity(weatherMap.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void updateDatabase(WeatherMap weatherMap) {
        databaseRepo.insertOrUpdateWeather(weatherMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void onError(Throwable throwable) {
        //// FIXME: 7/17/2017 
        weather.setValue(weather.getValue());
    }

    public void changeCity(String id) {
        remoteRepo.getPlaceInfo(id)
                .flatMap(this::getWeatherFromDetailedPlaceInfo)
                .map(WeatherMap::updateTime)
                .map(WeatherMap::setRefreshed)
                .doOnSuccess(this::updateCurrentCity)
                .doOnSuccess(this::updateDatabase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather::setValue, this::onError);
    }

    private Single<WeatherMap> getWeatherFromDetailedPlaceInfo(PlaceDetailedInfo info) {
        List<AddressComponent> components = info.getResult().getAddressComponents();
        String zipCode = components.get(components.size() - 1).getLongName();
        String countryCode = components.get(components.size() - 2).getShortName().toLowerCase();
        return getWeather(zipCode, countryCode);
    }

    private Single<WeatherMap> getWeather(String zipCode, String countryCode) {
        return remoteRepo.getWeather(zipCode, countryCode);
    }
}
