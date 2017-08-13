package tljfn.yamblzweather.modules.city.favorite;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 09.08.17.
 */
public class FavoriteCitiesInteractorTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    PreferencesRepo preferencesRepo;
    @Mock
    DatabaseRepo dbRepo;

    FavoriteCitiesInteractor interactor;

    @Before
    public void setup() {
        interactor = new FavoriteCitiesInteractor(preferencesRepo, dbRepo);
    }

    @Test
    public void loading_favorite_cities_correct() {
        when(dbRepo.loadFavoriteCities()).thenReturn(Flowable.fromCallable(() -> {
            DBCity city = new DBCity.Builder()
                    .favorite(true)
                    .enName("orenburg")
                    .build();
            List<DBCity> list = new ArrayList<>();
            list.add(city);
            return list;
        }));

        interactor.loadFavoriteCities().test()
                .assertNoErrors()
                .assertOf(observer -> verify(dbRepo).loadFavoriteCities())
                .assertValue(list -> {
                    return list.getFavoriteCities().size() > 0 &&
                            list.getFavoriteCities().get(0).isFavorite() &&
                            list.getFavoriteCities().get(0).getName().equals("orenburg");
                });
    }

}