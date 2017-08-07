package tljfn.yamblzweather.di.modules;

import android.support.annotation.NonNull;

import com.evernote.android.job.JobManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ringov on 07.08.17.
 */

@Module
public class JobManagerModule {
    @NonNull
    @Provides
    @Singleton
    public JobManager provideJobManager() {
        return JobManager.instance();
    }
}
