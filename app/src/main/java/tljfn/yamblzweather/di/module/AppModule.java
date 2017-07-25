/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tljfn.yamblzweather.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

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
import tljfn.yamblzweather.api.PlacesApi;
import tljfn.yamblzweather.api.WeatherApi;
import tljfn.yamblzweather.db.WeatherDao;
import tljfn.yamblzweather.db.WeatherDatabase;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.scheduler.AlarmReceiver;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton
    @Provides
    AlarmReceiver provideAlarmReceiver() {
        return new AlarmReceiver();
    }

    @Singleton
    @Provides
    WeatherApi provideWeatherApi() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ApiInterceptor("appid", BaseFields.WEATHER_API_KEY));
        if (BuildConfig.DEBUG) builder.addNetworkInterceptor(new StethoInterceptor());
        return new Retrofit.Builder()
                .baseUrl(BaseFields.WEATHER_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
                .create(WeatherApi.class);
    }

    @Singleton
    @Provides
    PlacesApi provideGoogleApi() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new ApiInterceptor("key", BaseFields.PLACES_API_KEY));
        if (BuildConfig.DEBUG) builder.addNetworkInterceptor(new StethoInterceptor());
        return new Retrofit.Builder()
                .baseUrl(BaseFields.PLACES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
                .create(PlacesApi.class);
    }

    @Singleton
    @Provides
    PreferencesRepo providePreferencesRepo(Application app) {
        return new PreferencesRepo(app.getApplicationContext());
    }

    @Singleton
    @Provides
    Context provideContext(Application app) {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    DatabaseRepo provideDatabaseRepo(WeatherDao weatherDao) {
        return new DatabaseRepo(weatherDao);
    }

    @Singleton
    @Provides
    RemoteRepo provideRemoteRepo(WeatherApi weatherApi, PlacesApi placesApi) {
        return new RemoteRepo(weatherApi, placesApi);
    }

    @Singleton
    @Provides
    WeatherDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app, WeatherDatabase.class, BaseFields.DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    WeatherDao provideWeatherDao(WeatherDatabase database) {
        return database.weatherDao();
    }
}

