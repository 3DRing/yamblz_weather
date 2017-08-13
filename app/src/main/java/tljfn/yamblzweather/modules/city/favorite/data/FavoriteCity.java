package tljfn.yamblzweather.modules.city.favorite.data;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCity {

    private String name;
    private boolean favorite;

    public FavoriteCity(String name, boolean favorite) {
        this.name = name;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
