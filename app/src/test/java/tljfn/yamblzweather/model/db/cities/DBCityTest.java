package tljfn.yamblzweather.model.db.cities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ringov on 08.08.17.
 */
public class DBCityTest {

    @Test
    public void default_equals_correct() {
        DBCity c1 = new DBCity.Builder()
                .build();
        DBCity c2 = new DBCity.Builder()
                .build();

        assertTrue(c1.equals(c2));
    }


    @Test
    public void hashcode_correct() {
        DBCity c1 = new DBCity.Builder()
                .build();
        DBCity c2 = new DBCity.Builder()
                .build();

        assertTrue(c1.hashCode() == c2.hashCode());
    }
}