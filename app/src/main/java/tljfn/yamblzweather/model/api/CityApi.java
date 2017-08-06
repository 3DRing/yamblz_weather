package tljfn.yamblzweather.model.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.model.api.data.city.RawCity;

/**
 * Created by ringov on 04.08.17.
 */

public class CityApi {

    WeakReference<Context> context;

    public CityApi(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    public Single<List<RawCity>> getAllCities() {
        return Single.fromCallable(() -> {
            Context context = this.context.get();
            if (context == null) {
                return new ArrayList<>();
            } else {
                InputStream inputStream = context.getResources().openRawResource(R.raw.cities);
                Reader reader = new InputStreamReader(inputStream);
                Gson gson = new GsonBuilder().create();

                Type itemsListType = new TypeToken<List<RawCity>>() {
                }.getType();
                List<RawCity> cities = gson.fromJson(reader, itemsListType);
                return cities;
            }
        });
    }

}
