package ca.sharipov.serhii.movieinfo;

import android.app.Application;

import timber.log.Timber;

public class MovieInfoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}