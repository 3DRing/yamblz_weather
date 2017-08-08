package tljfn.yamblzweather.modules.city.choose_city.data;

import java.util.ArrayList;
import java.util.List;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 05.08.17.
 */

public class UICitySuggestions extends UIBaseData {

    private List<CitySuggestion> suggestions;

    private UICitySuggestions() {
        suggestions = new ArrayList<>();
    }

    public List<CitySuggestion> getSuggestions() {
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

        public Builder addCity(CitySuggestion suggestion) {
            data.suggestions.add(suggestion);
            return this;
        }
    }

}
