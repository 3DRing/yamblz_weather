package tljfn.yamblzweather.modules.city.choose_city;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.city.CitiesListAdapter;
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;
import tljfn.yamblzweather.utils.custom_views.FavoriteButton;

/**
 * Created by ringov on 08.08.17.
 */

public class ChooseCityListAdapter extends CitiesListAdapter<CitySuggestion, Boolean> {

    ClickListener<CitySuggestion, Integer> chooseListener;

    ChooseCityListAdapter(@Nullable ClickListener<CitySuggestion, Boolean> favoriteListener,
                          ClickListener<CitySuggestion, Integer> chooseListener) {
        super(favoriteListener);
        this.chooseListener = chooseListener;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.city_suggestion_item;
    }

    @Override
    protected CitiesListAdapter.ViewHolder<CitySuggestion, Boolean> initHolder(View v) {
        return new ViewHolder(v);
    }

    static class ViewHolder extends CitiesListAdapter.ViewHolder<CitySuggestion, Boolean> {

        @BindView(R.id.tv_city_name)
        TextView tvCity;
        @BindView(R.id.favorite)
        FavoriteButton favorite;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(CitySuggestion city, int position, @Nullable ClickListener<CitySuggestion, Boolean> listener) {
            tvCity.setText(city.getName());
            favorite.setChecked(city.isFavorite());
            if (listener != null) {
                favorite.setOnToggleListener((fav) -> listener.onClick(city, position, fav));
            }
        }

        protected void bind(CitySuggestion city, int position, @Nullable ClickListener<CitySuggestion, Boolean> favoriteListener,
                            ClickListener<CitySuggestion, Integer> chooseListener) {
            bind(city, position, favoriteListener);
            itemView.setOnClickListener(v -> chooseListener.onClick(city, position, city.getId()));
        }
    }
}
