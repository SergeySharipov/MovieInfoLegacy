package ca.sharipov.serhii.movieinfo.ui.holders;

import android.content.Context;
import android.net.Uri;
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
        ImageUtil.loadImage(fragment,
                (ImageView) itemView.findViewById(R.id.image),
                movieBrief.getPosterLink());

        TextView title = itemView.findViewById(R.id.title);
        title.setText(movieBrief.getTitle());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = fragment.getContext();
                Uri uri = Uri.parse(movieBrief.getBackdropPath());
                // PhotoPageActivity.start(context, uri);
                if (context != null) {
                    context.startActivity(MovieDetailsActivity.newIntent(context));
                }
            }
        });
    }
}
