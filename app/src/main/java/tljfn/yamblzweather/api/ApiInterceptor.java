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

public class ApiInterceptor implements Interceptor {

    private String keyName;
    private String keyValue;

    public ApiInterceptor(String keyName, String keyValue) {
        this.keyName = keyName;
        this.keyValue = keyValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter(keyName, keyValue)
                .build();

        Request newRequest = request.newBuilder()
                .url(url)
                .build();
        return chain.proceed(newRequest);
    }
}
