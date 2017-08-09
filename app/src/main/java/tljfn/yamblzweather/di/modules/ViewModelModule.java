package tljfn.yamblzweather.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import butterknife.BindView;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import tljfn.yamblzweather.modules.about.AboutViewModel;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelKey;
import tljfn.yamblzweather.modules.city.choose_city.ChooseCityViewModel;
import tljfn.yamblzweather.modules.city.favorite.FavoriteCitiesViewModel;
import tljfn.yamblzweather.modules.forecast.ForecastViewModel;
import tljfn.yamblzweather.modules.weather.WeatherViewModel;

/**
 * Created by ringov on 07.08.17.
 */

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel.class)
    abstract ViewModel bindStartViewModel(WeatherViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel.class)
    abstract ViewModel bindAboutViewModel(AboutViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChooseCityViewModel.class)
    abstract ViewModel bindChooseCityViewModel(ChooseCityViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteCitiesViewModel.class)
    abstract ViewModel bindFavoriteCitiesViewModel(FavoriteCitiesViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel.class)
    abstract ViewModel bindForecastViewModel(ForecastViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
