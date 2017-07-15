package tljfn.yamblzweather.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import tljfn.yamblzweather.ui.about.AboutViewModel;
import tljfn.yamblzweather.ui.settings.SettingsViewModel;
import tljfn.yamblzweather.ui.start.StartViewModel;
import utils.ViewModelFactory;
import utils.ViewModelKey;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel.class)
    abstract ViewModel bindStartViewModel(StartViewModel startViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel.class)
    abstract ViewModel bindAboutViewModel(AboutViewModel aboutViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel.class)
    abstract ViewModel bindSettingsViewModel(SettingsViewModel settingsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
