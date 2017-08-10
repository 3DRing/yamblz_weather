package tljfn.yamblzweather.modules.forecast;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.BaseInteractor;
import tljfn.yamblzweather.modules.forecast.data.UIForecast;
import tljfn.yamblzweather.modules.forecast.data.UISingleForecast;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

/**
 * Created by ringov on 09.08.17.
 */

public class ForecastInteractor extends BaseInteractor {

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
                .flatMap(dbRepo::getCity)
                .zipWith(preferencesRepo.subscribeToCityUpdate()
                                .flatMap(id -> dbRepo.loadCachedForecast(id).subscribeOn(Schedulers.io()))
                        , UIConverter::toUIForecast)
                .flatMap(forecast -> {
                    if (forecast.isEmpty()) {
                        // add check for old data
                        return Flowable.just(forecast);//loadForecastFromRemote();
                    } else {
                        return Flowable.just(forecast);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<List<UISingleForecast>> loadForecastFromRemote() {
        return preferencesRepo.getCurrentCity()
                .flatMap(dbRepo::getCity)
                .zipWith(preferencesRepo.getCurrentCity()
                        .flatMap(remoteRepo::getForecast), DBConverter::fromRawForecast)
                .flatMap(dbRepo::insertOrUpdateForecast)
                .flatMap(Flowable::fromIterable)
                .map(UIConverter::toUISingleForecast)
                .toList();
    }

    public Single<UIForecast> updateForecast() {
        return loadForecastFromRemote()
                .map(list -> new UIForecast.Builder().addSingleForecasts(list).build())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
