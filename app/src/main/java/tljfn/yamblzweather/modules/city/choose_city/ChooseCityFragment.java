package tljfn.yamblzweather.modules.city.choose_city;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.modules.base.fragment.ViewModelFragment;
import tljfn.yamblzweather.modules.city.CitiesListAdapter;
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;
import tljfn.yamblzweather.modules.city.choose_city.data.UICitySuggestions;

/**
 * Created by ringov on 05.08.17.
 */

public class ChooseCityFragment extends ViewModelFragment<ChooseCityViewModel, UICitySuggestions> {

    public static final String TAG = ChooseCityFragment.class.getName();

    @Inject
    ViewModelFactory factory;

    @BindView(R.id.close)
    View close;
    @BindView(R.id.backpress)
    View backPress;
    @BindView(R.id.et_search_city)
    EditText search;
    @BindView(R.id.rv_suggestions)
    RecyclerView suggestions;

    CitiesListAdapter<CitySuggestion> adapter;

    @OnClick(R.id.backpress)
    void onTopBackPress() {
        getActivity().finish();
    }

    @OnClick(R.id.close)
    void onClearSearch() {
        search.setText("");
        getViewModel().hideSearching();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        initializeSuggestionsView();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // is it better to hide this logic in ViewModel?
                if (charSequence.length() > 0) {
                    getViewModel().searchCity(charSequence.toString());
                } else {
                    getViewModel().hideSearching();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initializeSuggestionsView() {
        adapter = new ChooseCityListAdapter(null);
        suggestions.setLayoutManager(new LinearLayoutManager(getContext()));
        suggestions.setAdapter(adapter);
    }

    @Override
    public Class<ChooseCityViewModel> getViewModelClass() {
        return ChooseCityViewModel.class;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(@NonNull UICitySuggestions data) {
        adapter.setItems(data.getSuggestions());
    }

    @Override
    public void onError(String errorMessage) {

    }

    @NonNull
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_choose_city;
    }

    @Override
    public int getToolbarTitle() {
        return 0;
    }

    @Override
    public int getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
