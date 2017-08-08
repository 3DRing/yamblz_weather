package tljfn.yamblzweather.model.db.weather;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ringov on 08.08.17.
 */
public class DBWeatherDataTest {

    @Test
    public void default_equals_correct() {
        DBWeatherData w1 = new DBWeatherData.Builder().build();
        DBWeatherData w2 = new DBWeatherData.Builder().build();

        assertTrue(w1.equals(w2));
    }

    @Test
    public void hashcode_correct() {
        DBWeatherData w1 = new DBWeatherData.Builder().build();
        DBWeatherData w2 = new DBWeatherData.Builder().build();

        assertTrue(w1.hashCode() == w2.hashCode());
    }
}