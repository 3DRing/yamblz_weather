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

public class ChooseCityListAdapter extends CitiesListAdapter<CitySuggestion> {

    public ChooseCityListAdapter(@Nullable ClickListener<CitySuggestion> listener) {
        super(listener);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.city_suggestion_item;
    }

    @Override
    protected CitiesListAdapter.ViewHolder<CitySuggestion> initHolder(View v) {
        return new ViewHolder(v);
    }

    protected static class ViewHolder extends CitiesListAdapter.ViewHolder<CitySuggestion> {

        @BindView(R.id.tv_city_name)
        TextView tvCity;
        @BindView(R.id.favorite)
        FavoriteButton favorite;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(CitySuggestion city, int position, @Nullable ClickListener listener) {
            tvCity.setText(city.getName());
            if (listener != null) {
                tvCity.setOnClickListener(v -> listener.onClick(city, position));
            }
        }
    }
}
