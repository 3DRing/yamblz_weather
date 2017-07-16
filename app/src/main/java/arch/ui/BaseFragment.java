package arch.ui;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arch.binding.FragmentDataBindingComponent;
import arch.util.AutoClearedValue;
import tljfn.yamblzweather.di.Injectable;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public abstract class BaseFragment extends LifecycleFragment implements Injectable {

    protected OnFragmentInteractionListener onFragmentInteractionListener;
    protected DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private AutoClearedValue<ViewDataBinding> binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        onFragmentInteractionListener.onFragmentInteraction(getToolbarTitle(), getDrawerMode());
        onCreateDataBinding(inflater, container);
        return binding.get().getRoot();
    }

    private void onCreateDataBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        ViewDataBinding dataBinding = DataBindingUtil
                .inflate(inflater, getLayoutRes(), container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onViewModelAttach();
        onBindingBound(binding);
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
    public abstract String getToolbarTitle();

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
     * Method where you should put the code for interacting with binding object.
     *
     * @param binding binding that was created with it`s fragment layout
     */
    public abstract void onBindingBound(AutoClearedValue binding);

    /**
     * This interface should listen when new fragment appears on the activity
     */
    public interface OnFragmentInteractionListener {

        /**
         * Call when fragment attaches to activity
         */
        void onFragmentInteraction(String title, Integer drawerMode);
    }
}