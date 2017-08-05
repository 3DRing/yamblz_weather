package tljfn.yamblzweather.ui.choose_city;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.ViewModelFragment;
import tljfn.yamblzweather.ui.choose_city.data.CitySuggestions;

/**
 * Created by ringov on 05.08.17.
 */

public class ChooseCityFragment extends ViewModelFragment<ChooseCityViewModel, CitySuggestions> {

    @BindView(R.id.close)
    View close;
    @BindView(R.id.backpress)
    View backPress;
    @BindView(R.id.et_search_city)
    EditText search;
    @BindView(R.id.rv_suggestions)
    RecyclerView suggestions;

    ChooseCityListAdapter adapter;

    @OnClick(R.id.backpress)
    void onTopBackPress() {
        getActivity().finish();
    }

    @OnClick(R.id.close)
    void onClearSearch() {
        search.setText("");
        getViewModel().hideSearching();
    }

    public static final String TAG = ChooseCityFragment.class.getName();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        adapter.setSuggestions(data.getSuggestions());
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

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
