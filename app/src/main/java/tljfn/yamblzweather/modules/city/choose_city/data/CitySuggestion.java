package tljfn.yamblzweather.modules.city.choose_city.data;

import tljfn.yamblzweather.modules.city.CityItem;

/**
 * Created by ringov on 04.08.17.
 */

public class CitySuggestion implements CityItem {
    private int id;
    private String name;

    public CitySuggestion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
