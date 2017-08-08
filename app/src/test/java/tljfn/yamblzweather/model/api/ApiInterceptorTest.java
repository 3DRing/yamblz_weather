package tljfn.yamblzweather.model.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

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

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    Interceptor.Chain chain;
    Request request;
    Request newRequest;
    Response response;

    @Before
    public void setup() {
        request = new Request.Builder().url("http://test.com/weather").build();
        newRequest = new Request.Builder().url("http://test.com/weather?appid=1234").build();
        response = new Response.Builder().request(newRequest)
                .protocol(Protocol.HTTP_1_0)
                .message("")
                .code(200)
                .build();
    }

    @Test
    public void adding_app_id() throws IOException {
        when(chain.request()).thenReturn(request);
        when(chain.proceed(any())).thenReturn(new Response.Builder()
                .request(newRequest)
                .protocol(Protocol.HTTP_1_0)
                .message("")
                .code(200)
                .build());

        ApiInterceptor interceptor = new ApiInterceptor("appid", "1234");
        Response localResponse = interceptor.intercept(chain);
        assertTrue(localResponse.toString().equals(response.toString()));

        verify(chain).request();
        verify(chain).proceed(any());
    }

}