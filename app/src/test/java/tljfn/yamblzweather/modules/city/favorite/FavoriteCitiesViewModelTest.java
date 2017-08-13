package tljfn.yamblzweather.modules.city.favorite;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Flowable;
import tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment.TestLifecycleOwner;
import tljfn.yamblzweather.modules.city.UICity;
import tljfn.yamblzweather.modules.city.favorite.data.FavoriteCity;
import tljfn.yamblzweather.modules.city.favorite.data.UIFavoriteCityList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 09.08.17.
 */
public class FavoriteCitiesViewModelTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    FavoriteCitiesInteractor interactor;
    TestLifecycleOwner owner;

    FavoriteCitiesViewModel viewModel;

    @Before
    public void setup() {
        owner = new TestLifecycleOwner();

        UIFavoriteCityList list = new UIFavoriteCityList.Builder()
                .addFavoriteCity(new UICity(0, "moscow", "ru", true))
                .build();
        when(interactor.loadFavoriteCities()).thenReturn(Flowable.just(list));

        viewModel = new FavoriteCitiesViewModel(interactor);
    }

    @Test
    public void loading_favorite_cities_correct() {
        UIFavoriteCityList list = new UIFavoriteCityList.Builder()
                .addFavoriteCity(new UICity(0, "moscow", "ru", true))
                .build();
        when(interactor.loadFavoriteCities()).thenReturn(Flowable.just(list));

        viewModel.loadFavoriteCities();
        viewModel.observe(owner, favList -> verify(interactor, atLeastOnce()).loadFavoriteCities());
    }
}