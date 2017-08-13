package tljfn.yamblzweather.model.api;

import android.content.Context;
import android.content.res.Resources;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.InputStream;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.api.data.city.RawCity;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 06.08.17.
 */
public class CityApiTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    Context context;
    @Mock
    Resources resources;

    DataProvider dataProvider;

    CityApi api;

    @Before
    public void setup() {
        dataProvider = new DataProvider();

        when(context.getResources()).thenReturn(resources);
        when(resources.openRawResource(R.raw.cities)).thenReturn(dataProvider.getCitiesInputStream());

        api = new CityApiImpl(context);
    }

    @Test
    public void getAllCities_returns_not_empty_result() {
        api.getAllCities().test()
                .assertNoErrors()
                .assertValue(citiesList -> {
                    boolean notNull = citiesList != null && citiesList.size() != 0;
                    if (citiesList == null) {
                        fail();
                    }
                    RawCity city = citiesList.get(0);
                    boolean correctValues = city.yaId == 2 &&
                            city.openWeatherId == 498817 &&
                            city.country.equals("ru") &&
                            city.enName.equals("Saint Petersburg") &&
                            city.ruName.equals("Санкт-Петербург");
                    return notNull && correctValues;
                });
    }

}