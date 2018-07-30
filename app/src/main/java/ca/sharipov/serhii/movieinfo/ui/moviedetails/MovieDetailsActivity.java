package ca.sharipov.serhii.movieinfo.ui.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import ca.sharipov.serhii.movieinfo.R;
import ca.sharipov.serhii.movieinfo.model.MovieBrief;
import ca.sharipov.serhii.movieinfo.ui.BaseActivity;
import ca.sharipov.serhii.movieinfo.utils.ImageUtil;

public class MovieDetailsActivity extends BaseActivity {
    public static final String MOVIE_POSTER = "MOVIE_POSTER";
    public static final String MOVIE_BACKDROP = "MOVIE_BACKDROP";

    public static Intent newIntent(Context context, MovieBrief movieBrief) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsFragment.MOVIE_ID, movieBrief.getId());
        intent.putExtra(MOVIE_POSTER, movieBrief.getPosterLink());
        intent.putExtra(MOVIE_BACKDROP, movieBrief.getBackdropLink());
        return intent;
    }

    public static void start(Context context, MovieBrief movieBrief) {
        Intent intent = newIntent(context, movieBrief);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        String movieBackdrop = getIntent().getStringExtra(MOVIE_BACKDROP);
        int movieId = getIntent().getIntExtra(MovieDetailsFragment.MOVIE_ID, 0);

        if (fragment == null) {
            fragment = MovieDetailsFragment.newInstance(movieId);
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        ImageUtil.loadImage(this, (ImageView) findViewById(R.id.toolbar_background), movieBackdrop);

        showBackButton();

        setTitle("");
    }

}