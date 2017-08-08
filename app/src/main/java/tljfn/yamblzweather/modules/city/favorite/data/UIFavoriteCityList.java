package tljfn.yamblzweather.modules.city.favorite.data;

import java.util.ArrayList;
import java.util.List;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 08.08.17.
 */

public class UIFavoriteCityList extends UIBaseData {

    List<FavoriteCity> cities;

    private UIFavoriteCityList() {
        cities = new ArrayList<>();
    }

    public List<FavoriteCity> getFavoriteCities() {
        return cities;
    }

    public static class Builder extends UIBaseData.Builder<UIFavoriteCityList, Builder> {

        @Override
        protected UIFavoriteCityList init() {
            return new UIFavoriteCityList();
        }

        public Builder addFavoriteCity(FavoriteCity city) {
            data.cities.add(city);
            return getThis();
        }

        public Builder addAllCities(List<FavoriteCity> cities) {
            data.cities.addAll(cities);
            return getThis();
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
