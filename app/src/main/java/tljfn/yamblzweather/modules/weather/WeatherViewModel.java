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

package tljfn.yamblzweather.modules.weather;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

public class WeatherViewModel extends BaseViewModel<UIWeatherData> {

    WeatherInteractor interactor;

    @Inject
    public WeatherViewModel(WeatherInteractor interactor) {
        this.interactor = interactor;
        loadCachedWeather();
    }

    public void loadCachedWeather() {
        sub(interactor.loadCachedWeather()
                .subscribe(this::onChange, this::onError));
    }

    public void updateWeather() {
        sub(interactor.updateWeather()
                .subscribe(this::onChange, this::onError));
    }

    @Override
    protected UIWeatherData buildUIError(String message) {
        return new UIWeatherData.Builder().error(message).build();
    }
}
