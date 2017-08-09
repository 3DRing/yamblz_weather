package tljfn.yamblzweather.modules.forecast;

import javax.inject.Inject;

import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.base.BaseInteractor;

/**
 * Created by ringov on 09.08.17.
 */

public class ForecastInteractor extends BaseInteractor {

    private RemoteRepo remoteRepo;
    private DatabaseRepo dbRepo;

    @Inject
    public ForecastInteractor(RemoteRepo remoteRepo, DatabaseRepo dbRepo) {
        this.remoteRepo = remoteRepo;
        this.dbRepo = dbRepo;
    }
}
