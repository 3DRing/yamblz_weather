package tljfn.yamblzweather.modules.city.choose_city.data;

import java.util.ArrayList;
import java.util.List;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 05.08.17.
 */

public class CitySuggestions extends UIBaseData {

    private List<UICitySuggestion> suggestions;

    private CitySuggestions() {
        suggestions = new ArrayList<>();
    }

    public List<UICitySuggestion> getSuggestions() {
        return suggestions;
    }

    public static class Builder extends UIBaseData.Builder<CitySuggestions, Builder> {

        @Override
        protected CitySuggestions init() {
            return new CitySuggestions();
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        public Builder addCity(UICitySuggestion suggestion) {
            data.suggestions.add(suggestion);
            return this;
        }
    }

}
