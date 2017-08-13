package tljfn.yamblzweather.modules.about;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.db.cities.CityDao;
import tljfn.yamblzweather.model.db.weather.WeatherDao;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment.TestLifecycleOwner;
import tljfn.yamblzweather.modules.city.choose_city.ChooseCityViewModel;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

import static org.junit.Assert.*;

/**
 * Created by ringov on 08.08.17.
 */
public class AboutViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    AboutViewModel viewModel;

    @Before
    public void setup() {
        viewModel = new AboutViewModel();
    }

    @Test
    public void building_error_data() throws Exception {
        UIBaseData data = viewModel.buildUIError("error");
        assertTrue(data.equals(new UIBaseData.ErrorBuilder().error("error").build()));
    }

}