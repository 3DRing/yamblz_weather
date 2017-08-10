package tljfn.yamblzweather.modules.city.favorite;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.city.OverengineeredCitiesListAdapter;
import tljfn.yamblzweather.modules.city.favorite.data.FavoriteCity;
import tljfn.yamblzweather.utils.custom_views.FavoriteButton;

/**
 * Created by ringov on 05.08.17.
 */

public class OldFavoriteCitiesListAdapter extends OverengineeredCitiesListAdapter<FavoriteCity, Boolean> {

    public OldFavoriteCitiesListAdapter(@Nullable ClickListener<FavoriteCity, Boolean> listener) {
        super(listener);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.favorite_city_item;
    }

    @Override
    protected OverengineeredCitiesListAdapter.ViewHolder<FavoriteCity, Boolean> initHolder(View v) {
        return new ViewHolder(v);
    }

    static class ViewHolder extends OverengineeredCitiesListAdapter.ViewHolder<FavoriteCity, Boolean> {

        @BindView(R.id.tv_city_name)
        TextView tvCity;
        @BindView(R.id.favorite)
        FavoriteButton favorite;

        protected ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(FavoriteCity city, int position, @Nullable ClickListener<FavoriteCity, Boolean> listener) {
            tvCity.setText(city.getName());
            favorite.setChecked(city.isFavorite());
            if (listener != null) {
                favorite.setOnToggleListener((fav) -> listener.onClick(city, position, fav));
            }
        }
    }
}
