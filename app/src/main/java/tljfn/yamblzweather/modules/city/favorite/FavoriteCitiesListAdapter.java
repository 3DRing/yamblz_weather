package tljfn.yamblzweather.modules.city.favorite;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.city.CitiesListAdapter;
import tljfn.yamblzweather.modules.city.CityItem;
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;
import tljfn.yamblzweather.modules.city.favorite.data.FavoriteCity;
import tljfn.yamblzweather.utils.custom_views.FavoriteButton;

/**
 * Created by ringov on 05.08.17.
 */

public class FavoriteCitiesListAdapter extends CitiesListAdapter<FavoriteCity> {

    public FavoriteCitiesListAdapter(@Nullable ClickListener<FavoriteCity> listener) {
        super(listener);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.favorite_city_item;
    }

    @Override
    protected CitiesListAdapter.ViewHolder<FavoriteCity> initHolder(View v) {
        return new ViewHolder(v);
    }

    static class ViewHolder extends CitiesListAdapter.ViewHolder<FavoriteCity> {

        @BindView(R.id.tv_city_name)
        TextView tvCity;
        @BindView(R.id.favorite)
        FavoriteButton favorite;

        protected ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(FavoriteCity city, int position, @Nullable ClickListener<FavoriteCity> listener) {
            tvCity.setText(city.getName());
            if (listener != null) {
                tvCity.setOnClickListener(v -> listener.onClick(city, position));
            }
        }
    }
}
