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
import tljfn.yamblzweather.BaseFields;
import tljfn.yamblzweather.BuildConfig;
import tljfn.yamblzweather.api.ApiInterceptor;
import tljfn.yamblzweather.api.ConnectivityInterceptor;
import tljfn.yamblzweather.api.WeatherApi;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class WeatherApiModule {

    @NonNull
    @Singleton
    @Provides
    public WeatherApi provideWeatherApi(Context context){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ApiInterceptor("appid", BaseFields.WEATHER_API_KEY));
        builder.addInterceptor(new ConnectivityInterceptor(context));

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
