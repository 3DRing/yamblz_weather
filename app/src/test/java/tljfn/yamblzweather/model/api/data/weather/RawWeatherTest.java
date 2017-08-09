package tljfn.yamblzweather.model.api.data.weather;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ringov on 09.08.17.
 */
public class RawWeatherTest {
    @Test
    public void hashcode_correct() {
        assertTrue(new RawWeather().hashCode() == new RawWeather().hashCode());
    }
}