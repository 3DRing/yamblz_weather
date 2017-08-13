package tljfn.yamblzweather.modules.forecast;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.forecast.data.UIDayForecast;
import tljfn.yamblzweather.modules.forecast.data.UISingleForecast;
import tljfn.yamblzweather.utils.Utils;

/**
 * Created by ringov on 11.08.17.
 */

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {

    private List<UIDayForecast> dayForecasts;
    private ClickListener listener;
    private OrientationListener orientationListener;

    public ForecastListAdapter(@NonNull OrientationListener orientationListener, @Nullable ClickListener listener) {
        this.listener = listener;
        dayForecasts = new ArrayList<>();
        this.orientationListener = orientationListener;
    }

    public void setDayForecasts(UIDayForecast[] dayForecasts) {
        this.dayForecasts = Arrays.asList(dayForecasts);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(orientationListener.isOrientationLandscape() ?
                R.layout.day_forecast_item_land
                : R.layout.day_forecast_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(dayForecasts.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return dayForecasts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date)
        TextView date;

        @BindView(R.id.night_layout)
        View nightLayout;
        @BindView(R.id.weather_image_night)
        ImageView imageNight;
        @BindView(R.id.tv_temperature_night)
        TextView temperatureNight;
        @BindView(R.id.tv_time_night)
        TextView timeNight;

        @BindView(R.id.morning_layout)
        View morningLayout;
        @BindView(R.id.weather_image_morning)
        ImageView imageMorning;
        @BindView(R.id.tv_temperature_morning)
        TextView temperatureMorning;
        @BindView(R.id.tv_time_morning)
        TextView timeMorning;

        @BindView(R.id.day_layout)
        View afternoonLayout;
        @BindView(R.id.weather_image_afternoon)
        ImageView imageAfternoon;
        @BindView(R.id.tv_temperature_afternoon)
        TextView temperatureAfternoon;
        @BindView(R.id.tv_time_afternoon)
        TextView timeAfternoon;

        @BindView(R.id.evening_layout)
        View eveningLayout;
        @BindView(R.id.weather_image_evening)
        ImageView imageEvening;
        @BindView(R.id.tv_temperature_evening)
        TextView temperatureEvening;
        @BindView(R.id.tv_time_evening)
        TextView timeEvening;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(UIDayForecast forecast, ClickListener listener) {

            if (forecast == null) {
                return;
            }
            UISingleForecast single = forecast.getNight();
            if (single != null) {
                nightLayout.setVisibility(View.VISIBLE);
                imageNight.setImageResource(single.getCondition().getConditionImage());
                temperatureNight.setText(Utils.getFormattedTemperature(itemView.getContext(), single.getTemperature()));
                timeNight.setText(single.getRelativeTime().getName());
            } else {
                nightLayout.setVisibility(View.GONE);
            }

            single = forecast.getMorning();
            if (single != null) {
                morningLayout.setVisibility(View.VISIBLE);
                imageMorning.setImageResource(single.getCondition().getConditionImage());
                temperatureMorning.setText(Utils.getFormattedTemperature(itemView.getContext(), single.getTemperature()));
                timeMorning.setText(single.getRelativeTime().getName());
            } else {
                morningLayout.setVisibility(View.GONE);
            }

            single = forecast.getAfternoon();
            if (single != null) {
                afternoonLayout.setVisibility(View.VISIBLE);
                imageAfternoon.setImageResource(single.getCondition().getConditionImage());
                temperatureAfternoon.setText(Utils.getFormattedTemperature(itemView.getContext(), single.getTemperature()));
                timeAfternoon.setText(single.getRelativeTime().getName());
            } else {
                afternoonLayout.setVisibility(View.GONE);
            }

            single = forecast.getEvening();
            if (single != null) {
                eveningLayout.setVisibility(View.VISIBLE);
                imageEvening.setImageResource(single.getCondition().getConditionImage());
                temperatureEvening.setText(Utils.getFormattedTemperature(itemView.getContext(), single.getTemperature()));
                timeEvening.setText(single.getRelativeTime().getName());

                date.setText(Utils.getFormattedDate(single.getForecastTime()));
            } else {
                eveningLayout.setVisibility(View.GONE);
            }
        }
    }

    public interface ClickListener {
        void onClick(UISingleForecast forecast);
    }


    public interface OrientationListener {
        boolean isOrientationLandscape();
    }
}
