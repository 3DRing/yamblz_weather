package tljfn.yamblzweather.modules.base.viewmodel;

import android.arch.lifecycle.LifecycleOwner;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.data.TestUIData;
import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment.TestLifecycleOwner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by ringov on 07.08.17.
 */
public class BaseViewModelTest {

    BaseViewModel<TestUIData> viewModel;
    LifecycleOwner owner;

    @Before
    public void setup() {
        viewModel = new TestViewModel();
        owner = new TestLifecycleOwner();
    }

    @Test
    public void building_error_data() {
        UIBaseData data = viewModel.buildUIError("error");
        assertTrue(data.equals(new TestUIData.TestBuilder().error("error").build()));
    }

    @Test
    public void passing_null_as_value() {
        viewModel.onChange(null);

        viewModel.observe(owner, test -> assertTrue(test == null));
    }

    @Test
    public void passing_normal_value() {
        TestUIData data = new TestUIData.TestBuilder().build();
        data.field = 6;
        viewModel.onChange(data);

        viewModel.observe(owner, test -> {
            assertFalse(test.hasError());
            assertTrue(test.field == 6);
            assertTrue(test.getErrorMessage().isEmpty());
            assertFalse(test.isLoading());
        });
    }

    @Test
    public void getting_error() {
        viewModel.onError(new Exception("error"));

        viewModel.observe(owner, error -> assertTrue(error.getErrorMessage().equals("error")));
    }
}