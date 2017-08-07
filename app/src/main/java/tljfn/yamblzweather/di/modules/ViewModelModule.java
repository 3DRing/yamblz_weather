package tljfn.yamblzweather.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import tljfn.yamblzweather.modules.about.AboutViewModel;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelKey;
import tljfn.yamblzweather.modules.city.choose_city.ChooseCityViewModel;
import tljfn.yamblzweather.modules.weather.WeatherViewModel;

/**
 * Created by ringov on 07.08.17.
 */

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    abstract ViewModel bindStartViewModel(WeatherViewModel startViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel.class)
    abstract ViewModel bindAboutViewModel(AboutViewModel aboutViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChooseCityViewModel.class)
    abstract ViewModel bindChooseCityViewModel(ChooseCityViewModel chooseCityViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
