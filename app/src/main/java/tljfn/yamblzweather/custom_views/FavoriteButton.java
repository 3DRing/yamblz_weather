package tljfn.yamblzweather.custom_views;

import android.content.Context;
import android.util.AttributeSet;

import tljfn.yamblzweather.R;

/**
 * Created by Sergey Koltsov on 10.04.2017.
 */

public class FavoriteButton extends ToggleImageButton {

    public FavoriteButton(Context context) {
        super(context);
    }

    public FavoriteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FavoriteButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getOnImageRes() {
        return R.drawable.ic_favorite_true;
    }

    @Override
    protected int getOffImageRes() {
        return R.drawable.ic_favorite_false;
    }
}
