/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package arch.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;

import arch.binding.callback.PreferencesCallback;
import tljfn.yamblzweather.R;

/**
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"bind:selectedValue"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner spinner, final PreferencesCallback preferencesCallback) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer i = parent.getResources().getIntArray(R.array.intervals_seconds)[position];
                preferencesCallback.onIntervalChanged(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @InverseBindingAdapter(attribute = "bind:selectedValue", event = "bind:selectedValueAttrChanged")
    public static String captureSelectedValue(AppCompatSpinner spinner) {
        return (String) spinner.getSelectedItem();
    }

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
