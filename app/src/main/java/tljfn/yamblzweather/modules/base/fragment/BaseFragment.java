package tljfn.yamblzweather.modules.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    protected OnFragmentInteractionListener onFragmentInteractionListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onFragmentInteractionListener.onFragmentInteraction(getToolbarTitle(), getDrawerMode());
        ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onFragmentInteractionListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * use in case of custom initialization of some views
     */
    protected void initializeViews() {
        // to override
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
    }

    /**
     * @return {@link LayoutRes} for {@link LayoutInflater} in
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} method.
     */
    @LayoutRes
    @NonNull
    public abstract int getLayoutRes();

    /**
     * @return {@link String} on the toolbar that corresponds to the required toolbar title
     */
    @StringRes
    public abstract int getToolbarTitle();

    /**
     * @return {@link Integer} that reflects navigation drawer mode for
     * {@link android.support.v4.widget.DrawerLayout}.
     */
    public abstract int getDrawerMode();

    /**
     * This interface should listen when new fragment appears on the activity
     */
    public interface OnFragmentInteractionListener {

        /**
         * Call when fragment attaches to activity
         */
        void onFragmentInteraction(@StringRes int titleRes, Integer drawerMode);
    }
}