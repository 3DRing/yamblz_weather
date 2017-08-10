package tljfn.yamblzweather.modules.city.choose_city;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 09.08.17.
 */
public class ChooseCityInteractorTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    DatabaseRepo dbRepo;
    @Mock
    PreferencesRepo preferencesRepo;

    DataProvider dataProvider;

    ChooseCityInteractor interactor;

    @Before
    public void setup() {
        dataProvider = new DataProvider();
        interactor = new ChooseCityInteractor(preferencesRepo, dbRepo);
    }

    @Test
    public void getting_suggestions_correct() {
        when(dbRepo.getSuggestions("saint")).thenReturn(Flowable.fromCallable(() -> {
            List<DBCity> cities = new ArrayList<DBCity>();
            cities.add(DBConverter.fromRawCity(dataProvider.getSaintPetersburgCity()));
            return cities;
        }));

        interactor.getSuggestions("saint").test()
                .assertOf(observer -> verify(dbRepo).getSuggestions("saint"))
                .assertNoErrors()
                .assertValue(list ->
                        list.getSuggestions() != null && list.getSuggestions().size() != 0
                                && list.getSuggestions().get(0).getName().equals("Saint petersburg"));
    }

    @Test
    public void setting_favorite_without_seeing_suggestions_wrong_behavior() {
        DBCity[] sample = dataProvider.getDBCitiesSampleArray();
        interactor.setFavorite(sample[0].getId(), true).test()
                .assertNoErrors()
                .assertOf(observer -> verify(dbRepo, never()).setFavorite(sample[0]))
                .assertValue(false);
    }

    @Test
    public void setting_favorite_correct() {
        List<DBCity> sample = new ArrayList<>();
        sample.add(new DBCity.Builder().id(1).enName("city1").favorite(false).build());
        sample.add(new DBCity.Builder().id(2).enName("city2").favorite(false).build());

        when(dbRepo.setFavorite(sample.get(0))).thenReturn(Single.just(true));
        when(dbRepo.getSuggestions("city")).thenReturn(Flowable.fromCallable(() -> sample));

        interactor.getSuggestions("city")
                .doOnNext(suggestions ->
                        interactor.setFavorite(suggestions.getSuggestions().get(0).getId(), true).test()
                                .assertNoErrors()
                                .assertOf(observer -> verify(dbRepo)
                                        .setFavorite(new DBCity.Builder().id(1)
                                                .enName("city1").favorite(true).build()))
                                .assertValue(true))
                .subscribe();
        verify(dbRepo).getSuggestions("city");

    }
}