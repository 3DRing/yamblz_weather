package tljfn.yamblzweather.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.evernote.android.job.JobManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.repo.PreferencesRepo;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class PreferencesRepoModule {
    @Provides
    @NonNull
    @Singleton
    public PreferencesRepo providePreferencesRepo(Context context, SharedPreferences sp, JobManager jobManager) {
        return new PreferencesRepo(context, sp, jobManager);
    }
}
