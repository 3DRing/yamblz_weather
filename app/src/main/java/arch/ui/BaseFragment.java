package arch.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import tljfn.yamblzweather.di.Injectable;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public abstract class BaseFragment extends LifecycleFragment implements Injectable {

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
        onViewModelAttach();
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
    public abstract Integer getLayoutRes();

    /**
     * @return {@link String} on the toolbar that corresponds to the required toolbar title
     */
    @StringRes
    public abstract int getToolbarTitle();

    /**
     * @return {@link Integer} that reflects navigation drawer mode for
     * {@link android.support.v4.widget.DrawerLayout}.
     */
    public abstract Integer getDrawerMode();

    /**
     * This method will be called when ViewModel should be instantiated.
     */
    public abstract void onViewModelAttach();

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