package tljfn.yamblzweather.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tljfn.yamblzweather.model.api.ResponseInterceptor;
import tljfn.yamblzweather.utils.BaseFields;
import tljfn.yamblzweather.BuildConfig;
import tljfn.yamblzweather.model.api.ApiInterceptor;
import tljfn.yamblzweather.model.api.ConnectivityInterceptor;
import tljfn.yamblzweather.model.api.WeatherApi;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class WeatherApiModule {

    @NonNull
    @Singleton
    @Provides
    public WeatherApi provideWeatherApi(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ApiInterceptor("appid", BaseFields.WEATHER_API_KEY));
        builder.addInterceptor(new ConnectivityInterceptor(context));
        builder.addInterceptor(new ResponseInterceptor());

        if (BuildConfig.DEBUG) builder.addNetworkInterceptor(new StethoInterceptor());
        return new Retrofit.Builder()
                .baseUrl(BaseFields.WEATHER_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
                .create(WeatherApi.class);
    }
}
