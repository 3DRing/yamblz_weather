package tljfn.yamblzweather.api;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 26.07.17.
 */
public class ApiInterceptorTest {

    Interceptor.Chain chain;
    Request request;
    Request newRequest;
    Response response;

    @Before
    public void setup() {
        chain = mock(Interceptor.Chain.class);
        request = new Request.Builder().url("http://test.com/weather").build();
        newRequest = new Request.Builder().url("http://test.com/weather?appid=1234").build();
        response = new Response.Builder().request(newRequest).protocol(Protocol.HTTP_1_0).code(200).build();
    }

    @Test
    public void addingAppId() throws IOException {
        when(chain.request()).thenReturn(request);
        when(chain.proceed(any())).thenReturn(new Response.Builder().request(newRequest).protocol(Protocol.HTTP_1_0).code(200).build());

        ApiInterceptor interceptor = new ApiInterceptor("appid", "1234");
        Response localResponse = interceptor.intercept(chain);
        assertTrue(localResponse.toString().equals(response.toString()));

        verify(chain).request();
        verify(chain).proceed(any());
    }

}