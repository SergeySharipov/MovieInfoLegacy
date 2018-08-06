package ca.sharipov.serhii.movieinfo.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.sharipov.serhii.movieinfo.R;
import ca.sharipov.serhii.movieinfo.model.Genre;

public class GenresRecyclerViewAdapter extends
        RecyclerView.Adapter<GenresRecyclerViewAdapter.ViewHolder> {
    private List<Genre> mGenresList;

    public GenresRecyclerViewAdapter(List<Genre> genresList) {
        mGenresList = genresList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.bind(mGenresList.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenresList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView titleTv;

        ViewHolder(View view) {
            super(view);
            titleTv = view.findViewById(R.id.title_tv);
        }

        void bind(Genre item) {
            titleTv.setText(item.getName());
        }

    }
}
