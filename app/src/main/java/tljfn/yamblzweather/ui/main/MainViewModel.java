package tljfn.yamblzweather.ui.main;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.db.cities.DBCity;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.scheduler.WeatherUpdateJob;
import tljfn.yamblzweather.ui.base.viewmodel.BaseViewModel;

/**
 * Created by ringov on 06.08.17.
 */

public class MainViewModel extends BaseViewModel<UIMainData> {

    @Inject
    PreferencesRepo preferencesRepo;
    @Inject
    DatabaseRepo dbRepo;
    @Inject
    RemoteRepo remoteRepo;

    public MainViewModel() {
        App.getComponent().inject(this);
        initDBIfNot();
        setScheduler();
    }

    @Override
    protected UIMainData buildUIError(String messageError) {
        return new UIMainData.Builder().error(messageError).build();
    }

    private void initDBIfNot() {
        if (preferencesRepo.isFirstLaunch()) {
            remoteRepo.getAllCities()
                    .flatMapObservable(Observable::fromIterable)
                    .map(DBCity::fromRawCity)
                    .toList()
                    .flatMap(cities -> dbRepo.initCities(cities.toArray(new DBCity[cities.size()])))
                    .flatMapCompletable(success -> preferencesRepo.setFirstLaunch(false))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                    }, this::onError);
        }
    }

    private void setScheduler() {
        WeatherUpdateJob.schedule(preferencesRepo);
    }
}
