package tljfn.yamblzweather.modules.base.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ringov on 07.08.17.
 */
public class UIBaseDataTest {

    private static class TestData extends UIBaseData {
        public int field;
    }

    private static class TestBuilder extends UIBaseData.Builder<TestData, TestBuilder> {

        @Override
        protected TestData init() {
            return new TestData();
        }

        @Override
        protected TestBuilder getThis() {
            return this;
        }
    }

    UIBaseData.Builder builder;
    UIBaseData data;

    @Before
    public void setup() {
        builder = new TestBuilder();
        data = builder.build();
    }

    @Test
    public void correct_building_base_data() {
        UIBaseData init = builder.init();

        assertTrue(init instanceof TestData);
        assertTrue(builder.getThis() != null && builder.getThis() instanceof TestBuilder);
        assertTrue(builder.build() instanceof TestData);
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
}