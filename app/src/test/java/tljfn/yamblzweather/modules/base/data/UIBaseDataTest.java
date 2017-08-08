package tljfn.yamblzweather.modules.base.data;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.data.TestUIData;

import static org.junit.Assert.*;

/**
 * Created by ringov on 07.08.17.
 */
public class UIBaseDataTest {

    UIBaseData.Builder builder;
    UIBaseData data;

    @Before
    public void setup() {
        builder = new TestUIData.TestBuilder();
        data = builder.build();
    }

    @Test
    public void correct_building_base_data() {
        UIBaseData init = builder.init();

        assertTrue(init instanceof TestUIData);
        assertTrue(builder.getThis() != null && builder.getThis() instanceof TestUIData.TestBuilder);
        assertTrue(builder.build() instanceof TestUIData);
        assertTrue(builder.build().equals(data));
    }

    @Test
    public void no_error() {
        assertFalse(data.hasError());
        assertTrue(data.getErrorMessage().isEmpty());
    }


    @Test
    public void correct_error_setting() {
        data = builder.error("error").build();

        assertTrue(data.hasError());
        assertTrue(data.getErrorMessage().equals("error"));
    }

    @Test
    public void null_error_setting() {
        data = builder.error(null).build();

        assertFalse(data.hasError());
        assertTrue(data.getErrorMessage().isEmpty());
    }

    @Test
    public void no_loading() {
        assertFalse(data.isLoading());
    }

    @Test
    public void correct_loading_setting() {
        data.setLoading(false);
        assertFalse(data.isLoading());

        data.setLoading(true);
        assertTrue(data.isLoading());
    }

    @Test
    public void resetting_error() {
        data = builder.error("error").build();
        assertTrue(data.hasError());
        assertTrue(data.getErrorMessage().equals("error"));

        data.resetErrorState();
        assertFalse(data.hasError());
        assertTrue(data.getErrorMessage().isEmpty());
    }

    @Test
    public void equals_correct() {
        UIBaseData d1 = new UIBaseData.ErrorBuilder().build();
        UIBaseData d2 = new UIBaseData.ErrorBuilder().build();

        assertTrue(d1.equals(d2));
    }

    @Test
    public void hashcode_correct() {
        UIBaseData d1 = new UIBaseData.ErrorBuilder().build();
        UIBaseData d2 = new UIBaseData.ErrorBuilder().build();

        assertTrue(d1.hashCode() == d2.hashCode());
    }
}