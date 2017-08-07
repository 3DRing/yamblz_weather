package tljfn.yamblzweather.modules.about;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.BuildConfig;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.base.fragment.ViewModelFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class AboutFragment extends ViewModelFragment<AboutViewModel, UIBaseData> {

    public static final String TAG = AboutFragment.class.getName();

    @Inject
    ViewModelFactory factory;

    @BindView(R.id.tv_version)
    TextView version;

    @Override
    protected void initializeViews() {
        super.initializeViews();
        version.setText(getString(R.string.version, BuildConfig.VERSION_NAME));
    }

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_about;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.about_screen_title;
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    public Class<AboutViewModel> getViewModelClass() {
        return AboutViewModel.class;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(UIBaseData data) {

    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    protected void inject() {
        App.getComponent().inject(this);
    }

    @Override
    protected ViewModelFactory getViewModelFactory() {
        return factory;
    }
}
