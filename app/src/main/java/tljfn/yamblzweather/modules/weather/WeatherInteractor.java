package tljfn.yamblzweather.modules.weather;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
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

    public Flowable<UIWeatherData> loadCachedWeather() {
        return databaseRepo.loadCachedWeather();
    }

    public Single<UIWeatherData> updateWeather() {
        return preferencesRepo.getCurrentCity()
                .flatMap(remoteRepo::getWeather)
                .flatMap(databaseRepo::insertOrUpdateWeather);
    }
}
