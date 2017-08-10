package tljfn.yamblzweather.modules.city;

/**
 * Created by ringov on 10.08.17.
 */

public class UICity {
    private int id;
    private String name;
    private boolean favorite;

    public UICity(int id, String name, boolean favorite) {
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
