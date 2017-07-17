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

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tljfn.yamblzweather.api.WeatherApi;
import tljfn.yamblzweather.db.WeatherDao;
import tljfn.yamblzweather.db.WeatherDatabase;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.scheduler.AlarmReceiver;

import static tljfn.yamblzweather.BaseFields.API_URL;
import static tljfn.yamblzweather.BaseFields.DATABASE_NAME;

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
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(WeatherApi.class);
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
    RemoteRepo provideRemoteRepo(WeatherApi weatherApi) {
        return new RemoteRepo(weatherApi);
    }

    @Singleton
    @Provides
    WeatherDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app, WeatherDatabase.class, DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    WeatherDao provideWeatherDao(WeatherDatabase database) {
        return database.weatherDao();
    }
}
