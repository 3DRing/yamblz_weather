package tljfn.yamblzweather.modules.city.choose_city.data;

import tljfn.yamblzweather.modules.city.CityItem;

/**
 * Created by ringov on 04.08.17.
 */

public class CitySuggestion implements CityItem {
    private int id;
    private String name;
    private boolean favorite;

    public CitySuggestion(int id, String name, boolean favorite) {
        this.id = id;
        this.name = name;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
