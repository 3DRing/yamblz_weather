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
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;
import tljfn.yamblzweather.modules.city.favorite.data.FavoriteCity;
import tljfn.yamblzweather.utils.custom_views.FavoriteButton;

/**
 * Created by ringov on 05.08.17.
 */

public class FavoriteCitiesListAdapter extends RecyclerView.Adapter<FavoriteCitiesListAdapter.ViewHolder> {

    private List<FavoriteCity> cities;
    private ClickListener listener;

    public FavoriteCitiesListAdapter(@Nullable ClickListener listener) {
        cities = new ArrayList<>();
        this.listener = listener;
    }

    public void setCities(List<FavoriteCity> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_city_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(cities.get(i), i, listener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_city_name)
        TextView tvCity;
        @BindView(R.id.favorite)
        FavoriteButton favorite;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(FavoriteCity city, int position, ClickListener listener) {
            tvCity.setText(city.getName());
            if (listener != null) {
                tvCity.setOnClickListener(v -> listener.onClick(city, position));
            }
        }
    }

    public interface ClickListener {
        void onClick(FavoriteCity city, int position);
    }
}
