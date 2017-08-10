package tljfn.yamblzweather.modules.city.choose_city;

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
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment.TestLifecycleOwner;
import tljfn.yamblzweather.modules.city.choose_city.data.UICitySuggestions;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by ringov on 08.08.17.
 */
public class ChooseCityViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    WeatherDao weatherDao;
    @Mock
    CityDao cityDao;

    DatabaseRepo dbRepo;

    @Mock
    PreferencesRepo preferencesRepo;

    ChooseCityInteractor interactor;

    DataProvider dataProvider;
    TestLifecycleOwner owner;

    ChooseCityViewModel viewModel;

    @Before
    public void setup() {
        dataProvider = new DataProvider();
        owner = new TestLifecycleOwner();

        dbRepo = new DatabaseRepo(weatherDao, cityDao);

        interactor = new ChooseCityInteractor(preferencesRepo, dbRepo);
        viewModel = new ChooseCityViewModel(interactor);
    }

    @Test
    public void building_error_data() {
        UICitySuggestions data = viewModel.buildUIError("error");
        assertTrue(data.equals(new UICitySuggestions.Builder().error("error").build()));
    }

    @Test
    public void searching_city_correct() {
/*        when(cityDao.loadCitiesSuggestion("city")).thenReturn(Flowable.fromCallable(() -> {
            SingularForecast<DBCity> list = new ArrayList<>();
            list.add(new DBCity.Builder()
                    .id(1)
                    .enName("city")
                    .favorite(true)
                    .build());
            return list;
        }));

        viewModel.searchCity("cit");

        viewModel.observe(owner, suggestions -> {
            assertTrue(suggestions != null);
            assertTrue(suggestions.getSuggestions().size() > 0);
        });*/
    }

    @Test
    public void hide_searching_correct() {
        viewModel.hideSearching();
        viewModel.observe(owner, cityList -> {
            assertTrue(cityList != null);
            assertTrue(cityList.getSuggestions().size() == 0);
            assertFalse(cityList.hasError());
        });
    }
}