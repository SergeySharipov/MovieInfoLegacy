package ca.sharipov.serhii.movieinfo.utils;

import android.support.annotation.NonNull;

import java.io.IOException;

import ca.sharipov.serhii.movieinfo.Constants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("language", Constants.APP_LANGUAGE)
                .addQueryParameter("api_key", Constants.APP_KEY)
                .build();

        Request request = original.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
