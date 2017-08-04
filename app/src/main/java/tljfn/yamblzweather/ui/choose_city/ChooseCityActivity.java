package tljfn.yamblzweather.ui.choose_city;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tljfn.yamblzweather.R;

/**
 * Created by ringov on 04.08.17.
 */

public class ChooseCityActivity extends AppCompatActivity {

    @BindView(R.id.backpress)
    View backPress;
    @BindView(R.id.et_search_city)
    EditText search;

    @OnClick(R.id.backpress)
    void onTopBackPress() {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        ButterKnife.bind(this);

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
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
