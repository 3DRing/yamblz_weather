package tljfn.yamblzweather.modules.weather;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.model.db.DBConverter;
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

    public Flowable<UIWeatherData> loadCachedWeather() {
        return preferencesRepo.subscribeToCityUpdate()
                .flatMap(databaseRepo::getCity)
                .zipWith(preferencesRepo.subscribeToCityUpdate()
                                .flatMap(id -> databaseRepo.loadCachedWeather(id).subscribeOn(Schedulers.io()))
                        , UIConverter::toUIWeatherData)
                .flatMap(weather -> {
                    if (weather.isEmpty()) {
                        // add check for old data
                        return loadWeatherFromRemote();
                    } else {
                        return Flowable.just(weather);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Flowable<UIWeatherData> loadWeatherFromRemote() {
        return preferencesRepo.getCurrentCity()
                .flatMap(databaseRepo::getCity)
                .zipWith(preferencesRepo.getCurrentCity()
                        .flatMap(remoteRepo::getWeather), DBConverter::fromRawWeatherData)
                .flatMap(databaseRepo::insertOrUpdateWeather);
    }

    public Flowable<UIWeatherData> updateWeather() {
        return loadCachedWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T, R> Func1<? super T, ? extends Observable<? extends R>> ternary(
            Func1<T, Boolean> predicate,
            Func1<? super T, ? extends Observable<? extends R>> ifTrue,
            Func1<? super T, ? extends Observable<? extends R>> ifFalse) {
        return (item) -> predicate.call(item)
                ? ifTrue.call(item)
                : ifFalse.call(item);
    }

    public static interface Func1<T, R> {
        R call(T t);
    }
}
