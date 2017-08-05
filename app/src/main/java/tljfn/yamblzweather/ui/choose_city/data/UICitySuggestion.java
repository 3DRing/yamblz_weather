package tljfn.yamblzweather.ui.choose_city.data;

/**
 * Created by ringov on 04.08.17.
 */

public class UICitySuggestion {
    private int id;
    private String name;

    public UICitySuggestion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
