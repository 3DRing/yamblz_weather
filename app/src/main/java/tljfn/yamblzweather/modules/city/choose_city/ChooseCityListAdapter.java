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
import tljfn.yamblzweather.utils.custom_views.FavoriteButton;
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;

/**
 * Created by ringov on 05.08.17.
 */

public class ChooseCityListAdapter extends RecyclerView.Adapter<ChooseCityListAdapter.ViewHolder> {

    private List<CitySuggestion> suggestions;
    private ClickListener listener;

    public ChooseCityListAdapter(@Nullable ClickListener listener) {
        suggestions = new ArrayList<>();
        this.listener = listener;
    }

    public void setSuggestions(List<CitySuggestion> suggestions) {
        this.suggestions = suggestions;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_suggestion_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(suggestions.get(i), i, listener);
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
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

        void bind(CitySuggestion suggestion, int position, ClickListener listener) {
            tvCity.setText(suggestion.getName());
            if (listener != null) {
                tvCity.setOnClickListener(v -> listener.onClick(suggestion, position));
            }
        }
    }

    public interface ClickListener {
        void onClick(CitySuggestion suggestion, int position);
    }
}
