package demo.exercise.pclap.demomvvpp.screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by PC Lap on 8/25/2017.
 */
public abstract class BaseRecyclerViewAdapter<V extends RecyclerView.ViewHolder>
            extends RecyclerView.Adapter<V>{


    private final Context mContext;

    public BaseRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     onclick recycle view
     * OnRecyclerViewItemClickListener
     *
     * @param <T> Data from item click
     */
    public interface OnRecyclerViewItemClickListener<T> {
        void onItemRecyclerViewClick(T item);
    }

}
