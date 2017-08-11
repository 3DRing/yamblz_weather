package tljfn.yamblzweather.modules.forecast;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.forecast.data.UISingleForecast;
import tljfn.yamblzweather.utils.Utils;

/**
 * Created by ringov on 11.08.17.
 */

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {

    private List<UISingleForecast> forecasts;
    private ClickListener listener;

    public ForecastListAdapter(@Nullable ClickListener listener) {
        this.listener = listener;
        forecasts = new ArrayList<>();
    }

    public void setForecasts(List<UISingleForecast> forecasts) {
        this.forecasts = forecasts;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singular_weather_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(forecasts.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_image)
        ImageView weatherConditionImage;
        @BindView(R.id.tv_temperature)
        TextView temperature;
        @BindView(R.id.tv_conditions)
        TextView conditions;
        @BindView(R.id.tv_time)
        TextView time;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(UISingleForecast forecast, ClickListener listener) {
            time.setText(Utils.getFormattedTime(itemView.getContext(), forecast.getForecastTime()));
            temperature.setText(Utils.getFormattedTemperature(itemView.getContext(), forecast.getTemperature()));
            conditions.setText(forecast.getCondition().getFriendlyName());

            weatherConditionImage.setImageResource(forecast.getCondition().getConditionImage());

            if (listener != null) {
                itemView.setOnClickListener(v -> listener.onClick(forecast));
            }
        }
    }

    public interface ClickListener {
        void onClick(UISingleForecast forecast);
    }

}
