package tljfn.yamblzweather.modules.city.favorite.data;

import tljfn.yamblzweather.modules.city.CityItem;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCity implements CityItem {

    private String name;

    public FavoriteCity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
