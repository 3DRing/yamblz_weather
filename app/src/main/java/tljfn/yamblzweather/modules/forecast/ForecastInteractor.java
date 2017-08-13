package tljfn.yamblzweather.modules.forecast;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.db.forecast.DBForecast;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.BaseInteractor;
import tljfn.yamblzweather.modules.forecast.data.UIForecast;

/**
 * Created by ringov on 09.08.17.
 */

public class ForecastInteractor extends BaseInteractor {

    private static final int MIN_NUMBER_OF_FORECASTS = 4;
    private final PreferencesRepo preferencesRepo;
    private RemoteRepo remoteRepo;
    private DatabaseRepo dbRepo;

    @Inject
    public ForecastInteractor(PreferencesRepo preferencesRepo, RemoteRepo remoteRepo, DatabaseRepo dbRepo) {
        this.preferencesRepo = preferencesRepo;
        this.remoteRepo = remoteRepo;
        this.dbRepo = dbRepo;
    }

    Flowable<UIForecast> lazyUpdateCachedForecast() {
        return preferencesRepo.subscribeToCityUpdate()
                .flatMap(dbRepo::loadCachedForecast)
                .zipWith(Flowable.fromCallable(() -> true), (forecasts, b) -> forecasts)  // doesn't work without it =\\
                .flatMap(forecast -> {
                    if (forecast.size() > MIN_NUMBER_OF_FORECASTS) {
                        boolean outOfData = forecast.get(forecast.size() - MIN_NUMBER_OF_FORECASTS).getForecastTime()
                                > System.currentTimeMillis();
                        if (outOfData) {
                            return Flowable.just(forecast);
                        }
                    }
                    return loadForecastFromRemote();
                })
                .map(UIConverter::toUIForecast)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Flowable<List<DBForecast>> loadForecastFromRemote() {
        return preferencesRepo.getCurrentCity()
                .flatMap(dbRepo::getCity)
                .zipWith(preferencesRepo.getCurrentCity()
                        .flatMap(remoteRepo::getForecast), DBConverter::fromRawForecast)
                .flatMap(dbRepo::insertOrUpdateForecast);
    }

    public Flowable<UIForecast> updateForecast() {
        return loadForecastFromRemote()
                .map(UIConverter::toUIForecast)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public boolean isOrientationLandscape() {
        return preferencesRepo.isLandscapeOrientation();
    }
}
