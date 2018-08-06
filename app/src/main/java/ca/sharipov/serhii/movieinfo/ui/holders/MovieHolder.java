package ca.sharipov.serhii.movieinfo.ui.holders;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ca.sharipov.serhii.movieinfo.R;
import ca.sharipov.serhii.movieinfo.model.MovieBrief;
import ca.sharipov.serhii.movieinfo.ui.moviedetails.MovieDetailsActivity;
import ca.sharipov.serhii.movieinfo.utils.ImageUtil;

public class MovieHolder extends LoadMoreHolder<MovieBrief> {

    public MovieHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(final Fragment fragment, final MovieBrief movieBrief) {
        ImageUtil.loadImage(fragment.getContext(),
                (ImageView) itemView.findViewById(R.id.image),
                movieBrief.getPosterLink());

        TextView title = itemView.findViewById(R.id.title);
        title.setText(movieBrief.getTitle());

        TextView releaseDate = itemView.findViewById(R.id.release_date);
        String releaseDateStr = fragment.getString(R.string.activity_movie_details_release_date, movieBrief.getReleaseDate());
        releaseDate.setText(releaseDateStr);

        TextView score = itemView.findViewById(R.id.score);
        String scoreStr = fragment.getString(R.string.activity_movie_details_movie_score, movieBrief.getVoteAverage(), movieBrief.getVoteCount());
        score.setText(scoreStr);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = fragment.getContext();

                if (context != null) {
                    context.startActivity(MovieDetailsActivity.newIntent(context, movieBrief));
                }
            }
        });
    }
}
