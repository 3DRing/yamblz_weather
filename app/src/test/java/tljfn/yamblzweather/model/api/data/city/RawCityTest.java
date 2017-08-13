package tljfn.yamblzweather.model.api.data.city;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.data.DataProvider;

import static org.junit.Assert.*;

/**
 * Created by ringov on 13.08.17.
 */
public class RawCityTest {

    DataProvider dp;

    @Before
    public void setup() {
        dp = new DataProvider();
    }

    @Test
    public void equals_correct() throws Exception {
        RawCity r1 = new RawCity();
        RawCity r2 = new RawCity();
        assertTrue(r1.equals(r2));

        r1 = dp.getBelgorod();
        r2 = dp.getBelgorod();
        assertTrue(r1.equals(r2));

        r2 = dp.getSaintPetersburgCity();

        assertFalse(r1.equals(r2));
    }

    @Test
    public void hashCode_correct() throws Exception {
        RawCity r1 = new RawCity();
        RawCity r2 = new RawCity();
        assertTrue(r1.hashCode() == r2.hashCode());

        r1 = dp.getBelgorod();
        r2 = dp.getBelgorod();
        assertTrue(r1.hashCode() == r2.hashCode());

        r2 = dp.getSaintPetersburgCity();

        assertTrue(r1.hashCode() != r2.hashCode());
    }

}