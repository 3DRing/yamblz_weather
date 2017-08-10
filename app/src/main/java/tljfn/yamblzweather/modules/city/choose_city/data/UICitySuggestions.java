package tljfn.yamblzweather.modules.city.choose_city.data;

import java.util.ArrayList;
import java.util.List;

import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.city.UICity;

/**
 * Created by ringov on 05.08.17.
 */

public class UICitySuggestions extends UIBaseData {

    private List<UICity> suggestions;

    private UICitySuggestions() {
        suggestions = new ArrayList<>();
    }

    public List<UICity> getSuggestions() {
        return suggestions;
    }

    public static class Builder extends UIBaseData.Builder<UICitySuggestions, Builder> {

        @Override
        protected UICitySuggestions init() {
            return new UICitySuggestions();
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        public Builder addCity(UICity suggestion) {
            data.suggestions.add(suggestion);
            return this;
        }
    }

}
