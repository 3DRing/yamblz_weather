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

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.vo.weather.WeatherMap;

public class StartViewModel extends ViewModel {

    private final DatabaseRepo databaseRepo;
    private final RemoteRepo remoteRepo;
    public MutableLiveData<WeatherMap> weather = new MutableLiveData<>();

    @Inject
    public StartViewModel(RemoteRepo remoteRepo, DatabaseRepo databaseRepo) {
        this.databaseRepo = databaseRepo;
        this.remoteRepo = remoteRepo;

        remoteRepo.getWeather("Moscow")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(WeatherMap::initUpdateTime)
                .subscribe(weather::setValue, this::onNetworkError);

    }

    private void onNetworkError(Throwable throwable) {

    }

    /**
     * Get the weather at the city.
     */
    public Flowable<WeatherMap> getWeatherFromDb(String city) {
        return databaseRepo.getWeather();
    }
}
