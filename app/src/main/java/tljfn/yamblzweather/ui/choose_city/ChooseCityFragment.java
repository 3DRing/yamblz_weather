package tljfn.yamblzweather.ui.choose_city;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.ViewModelFragment;
import tljfn.yamblzweather.ui.choose_city.data.CitySuggestions;

/**
 * Created by ringov on 05.08.17.
 */

public class ChooseCityFragment extends ViewModelFragment<ChooseCityViewModel, CitySuggestions> {

    @BindView(R.id.backpress)
    View backPress;
    @BindView(R.id.et_search_city)
    EditText search;

    @OnClick(R.id.backpress)
    void onTopBackPress() {
        getActivity().finish();
    }

    public static final String TAG = ChooseCityFragment.class.getName();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    backPress.setVisibility(View.GONE);
                } else {
                    backPress.setVisibility(View.VISIBLE);
                }
                getViewModel().searchCity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected Class<ChooseCityViewModel> getViewModelClass() {
        return ChooseCityViewModel.class;
    }

    @Override
    protected void showLoading() {

    }

    @Override
    protected void hideLoading() {

    }

    @Override
    protected void onSuccess(@NonNull CitySuggestions data) {
        Toast.makeText(getContext(), data.getSuggestions().size() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onError(String errorMessage) {

    }

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_choose_city;
    }

    @Override
    public int getToolbarTitle() {
        return 0;
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }
}
