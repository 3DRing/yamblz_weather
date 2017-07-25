package tljfn.yamblzweather.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tljfn.yamblzweather.BaseFields;

/**
 * Created by ringov on 25.07.17.
 */

public class APIInterceptor implements Interceptor {

    private static final String APPID_KEY = "appid";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter(APPID_KEY, BaseFields.API_KEY)
                .build();

        Request newRequest = request.newBuilder()
                .url(url)
                .build();
        return chain.proceed(newRequest);
    }
}
