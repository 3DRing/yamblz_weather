package tljfn.yamblzweather.modules.base.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LifecycleOwner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.Single;
import tljfn.yamblzweather.data.TestUIData;
import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment.TestLifecycleOwner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by ringov on 07.08.17.
 */
public class BaseViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

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

        viewModel.observe(owner, test -> {
            assertTrue(test == null);
        });
    }

    @Test
    public void passing_normal_value() {
        TestUIData data = new TestUIData.TestBuilder().build();
        data.field = 6;

        viewModel.observe(owner, test -> {
            assertFalse(test.hasError());
            assertTrue(test.field == 6);
            assertTrue(test.getErrorMessage().isEmpty());
            assertFalse(test.isLoading());
        });
        viewModel.onChange(data);
    }

    @Test
    public void getting_error() {
        viewModel.onError(new Exception("error"));

        viewModel.observe(owner, error -> {
            assertTrue(error.getErrorMessage().equals("error"));
        });
    }

    @Test
    public void showing_loading() {
        TestUIData data = new TestUIData.TestBuilder().build();

        viewModel.onChange(data);
        viewModel.showLoading();
        viewModel.observe(owner, test -> {
            assertTrue(test != null);
            assertTrue(test.isLoading());
        });

        assertTrue(data.isLoading());
    }

    @Test
    public void no_errors_on_adding_subscription() {
        viewModel.sub(Single.just(true).subscribe());
    }
}