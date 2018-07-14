package ca.sharipov.serhii.movieinfo.ui.movielist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import ca.sharipov.serhii.movieinfo.ui.SingleFragmentActivity;

public class MoviesListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, MoviesListActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return MovieListFragment.newInstance();
    }
}