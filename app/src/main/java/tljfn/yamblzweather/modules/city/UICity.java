package tljfn.yamblzweather.modules.city;

/**
 * Created by ringov on 10.08.17.
 */

public class UICity {
    private int id;
    private String name;
    private String country;
    private boolean favorite;

    public UICity(int id, String name, String country, boolean favorite) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }
}
