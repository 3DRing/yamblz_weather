package tljfn.yamblzweather.model.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tljfn.yamblzweather.R;

/**
 * Created by ringov on 20.07.17.
 */

public class ConnectivityInterceptor implements Interceptor {

    @Inject
    Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isOnline()) {
            throw new NoInternetConnectionException(context.getString(R.string.no_internet_connection));
        }
        Request request = chain.request();
        return chain.proceed(request);
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
