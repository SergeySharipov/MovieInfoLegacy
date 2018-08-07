package ca.sharipov.serhii.movieinfo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageUtil {
    public static void loadImage(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);
    }

    public interface ImageLoadedCallback {
        void onImageLoaded();
    }
}
