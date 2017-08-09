package tljfn.yamblzweather.modules.city;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ringov on 08.08.17.
 */

public abstract class CitiesListAdapter<T extends CityItem, R> extends RecyclerView.Adapter<CitiesListAdapter.ViewHolder<T, R>> {

    private List<T> items;
    private ClickListener<T, R> listener;

    public CitiesListAdapter(@Nullable ClickListener<T, R> listener) {
        items = new ArrayList<>();
        this.listener = listener;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract ViewHolder<T, R> initHolder(View v);

    @Override
    public ViewHolder<T, R> onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutRes(), viewGroup, false);
        return initHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder<T, R> viewHolder, int i) {
        viewHolder.bind(items.get(i), i, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected static abstract class ViewHolder<T extends CityItem, R> extends RecyclerView.ViewHolder {

        protected ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected abstract void bind(T city, int position, @Nullable ClickListener<T, R> listener);
    }

    public interface ClickListener<T extends CityItem, R> {
        void onClick(T city, int position, R result);
    }
}
