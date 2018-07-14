package ca.sharipov.serhii.movieinfo.ui.holders;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ca.sharipov.serhii.movieinfo.R;

public class LoadMoreHolder<T> extends RecyclerView.ViewHolder {

    public LoadMoreHolder(View itemView) {
        super(itemView);
    }

    public LoadMoreHolder(View itemView, View.OnClickListener retryListener) {
        super(itemView);
        itemView.findViewById(R.id.item_retry_button).setOnClickListener(retryListener);
    }

    public void onBind(Fragment fragment, T data) {
        //stub
    }
}
