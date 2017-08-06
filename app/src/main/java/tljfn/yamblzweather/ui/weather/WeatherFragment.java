package tljfn.yamblzweather.ui.weather;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import tljfn.yamblzweather.Utils;
import tljfn.yamblzweather.navigation.NavigationController;
import butterknife.BindView;
import butterknife.OnClick;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.ViewModelFragment;
import tljfn.yamblzweather.ui.weather.data.UIWeatherData;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class WeatherFragment extends ViewModelFragment<WeatherViewModel, UIWeatherData> {
    public static final String TAG = WeatherFragment.class.getName();

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_conditions)
    TextView tvCondition;
    @BindView(R.id.weather_image)
    ImageView weatherImage;

    @OnClick(R.id.tv_city)
    public void onCityClick() {
        //NavigationController.navigateToChooseCity(getContext());
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        swipeLayout.setOnRefreshListener(() -> viewModel.updateWeather());
    }

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_weather;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.weather_screen_title;
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    protected Class<WeatherViewModel> getViewModelClass() {
        return WeatherViewModel.class;
    }

    @Override
    protected void showLoading() {
        swipeLayout.setRefreshing(true);
    }

    @Override
    protected void hideLoading() {
        swipeLayout.setRefreshing(false);
    }

    @Override
    protected void onSuccess(@NonNull UIWeatherData data) {
        tvTemperature.setText(getString(R.string.temperature, data.getTemperature()));
        tvCity.setText(data.getCity());
        tvTime.setText(Utils.getRelativeTime(getContext(), data.getTime()));
        tvCondition.setText(data.getConditionName());
        weatherImage.setImageResource(data.getConditionImage());
    }

    @Override
    protected void onError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
