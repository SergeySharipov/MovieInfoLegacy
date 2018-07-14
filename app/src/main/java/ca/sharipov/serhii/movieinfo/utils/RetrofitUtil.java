package ca.sharipov.serhii.movieinfo.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import ca.sharipov.serhii.movieinfo.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private volatile static Retrofit sBaseRetrofit;

    private static Retrofit getBaseRetrofit() {
        if (sBaseRetrofit == null) {
            OkHttpClient client = createOkHttpClient();
            sBaseRetrofit = initRetrofit(client, Constants.TMDB_API_URL);
        }

        return sBaseRetrofit;
    }

    private static OkHttpClient createOkHttpClient() {
        RequestInterceptor requestInterceptor = new RequestInterceptor();

        return new okhttp3.OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .addInterceptor(requestInterceptor)
                .build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        return getBaseRetrofit().create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        OkHttpClient client = createOkHttpClient();

        return initRetrofit(client, baseUrl).create(serviceClass);
    }

    @NonNull
    private static Retrofit initRetrofit(OkHttpClient client, String baseUrl) {
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

}
