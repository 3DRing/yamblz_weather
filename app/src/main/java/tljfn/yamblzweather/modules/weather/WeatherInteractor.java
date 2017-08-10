package tljfn.yamblzweather.modules.weather;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.BaseInteractor;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

/**
 * Created by ringov on 06.08.17.
 */

public class WeatherInteractor extends BaseInteractor {

    private RemoteRepo remoteRepo;
    private DatabaseRepo databaseRepo;
    private PreferencesRepo preferencesRepo;

    @Inject
    public WeatherInteractor(RemoteRepo remoteRepo, DatabaseRepo databaseRepo, PreferencesRepo preferencesRepo) {
        this.remoteRepo = remoteRepo;
        this.databaseRepo = databaseRepo;
        this.preferencesRepo = preferencesRepo;
    }

    /**
     * either from db or network
     *
     * @return
     */
    public Flowable<UIWeatherData> getWeather() {
        return preferencesRepo.subscribeToCityUpdate()
                .flatMap(databaseRepo::getCity)
                .zipWith(preferencesRepo.subscribeToCityUpdate()
                                .flatMap(databaseRepo::loadCachedWeather)
                        , (city, weather) -> {
                            return UIConverter.toUIWeatherData(city, weather);
                        }
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
/*
    public Flowable<UIWeatherData> loadCachedWeather() {
        return preferencesRepo.getCurrentCity()
                .flatMap(databaseRepo::getCity)
                .zipWith(preferencesRepo.getCurrentCity()
                                .flatMap(databaseRepo::loadCachedWeather),
                        UIConverter::toUIWeatherData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<UIWeatherData> updateWeather() {
        return preferencesRepo.getCurrentCity()
                .flatMap(databaseRepo::getCity)
                .zipWith(preferencesRepo.getCurrentCity()
                        .flatMap(remoteRepo::getWeather), DBConverter::fromRawWeatherData)
                .flatMap(databaseRepo::insertOrUpdateWeather)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }*/
}
