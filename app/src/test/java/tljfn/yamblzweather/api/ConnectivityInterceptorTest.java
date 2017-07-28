package tljfn.yamblzweather.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 26.07.17.
 */
public class ConnectivityInterceptorTest {

    Context context;
    ConnectivityManager manager;
    NetworkInfo networkInfo;
    ConnectivityInterceptor interceptor;
    Interceptor.Chain chain;
    Request request;
    Response response;

    @Before
    public void setup() {
        context = mock(Context.class);
        manager = mock(ConnectivityManager.class);
        networkInfo = mock(NetworkInfo.class);
        interceptor = new ConnectivityInterceptor(context);
        chain = mock(Interceptor.Chain.class);
        request = new Request.Builder().url("http://test.com").build();
        response = new Response.Builder().request(request).protocol(Protocol.HTTP_1_0).code(200).build();
    }

    @Test
    public void internetAvailable() throws IOException {
        when(networkInfo.isConnected()).thenReturn(true);
        when(manager.getActiveNetworkInfo()).thenReturn(networkInfo);
        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(manager);
        when(chain.request()).thenReturn(request);
        when(chain.proceed(request)).thenReturn(response);

        assertTrue(interceptor.intercept(chain).equals(response));
    }

    @Test(expected = NoInternetConnectionException.class)
    public void internetNotAvailable() throws IOException {
        when(networkInfo.isConnected()).thenReturn(false);
        when(manager.getActiveNetworkInfo()).thenReturn(networkInfo);
        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(manager);
        when(chain.request()).thenReturn(request);
        when(chain.proceed(request)).thenReturn(response);

        interceptor.intercept(chain);
    }
}