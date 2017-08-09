package tljfn.yamblzweather.modules.city.choose_city;

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
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;
import tljfn.yamblzweather.utils.custom_views.FavoriteButton;

/**
 * Created by ringov on 09.08.17.
 */

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private List<CitySuggestion> items;
    private FavoriteListener favoriteListener;
    private ChooseCityListener chooseCityListener;

    public CityListAdapter(FavoriteListener favoriteListener, ChooseCityListener chooseCityListener) {
        items = new ArrayList<>();
        this.favoriteListener = favoriteListener;
        this.chooseCityListener = chooseCityListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_suggestion_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(items.get(i), i, favoriteListener, chooseCityListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<CitySuggestion> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_city_name)
        TextView tvCity;
        @BindView(R.id.favorite)
        FavoriteButton favorite;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CitySuggestion city, int position, @Nullable FavoriteListener favoriteListener, ChooseCityListener chooseCityListener) {
            tvCity.setText(city.getName());
            favorite.setChecked(city.isFavorite());
            if (favoriteListener != null) {
                favorite.setOnToggleListener((fav) -> favoriteListener.onClick(city, position, fav));
            }
            if (chooseCityListener != null) {
                itemView.setOnClickListener(v -> chooseCityListener.onClick(city, position, city.getId()));
            }
        }
    }

    interface ChooseCityListener {
        void onClick(CitySuggestion city, int position, int cityId);
    }

    interface FavoriteListener {
        void onClick(CitySuggestion city, int position, boolean favorite);
    }
}
