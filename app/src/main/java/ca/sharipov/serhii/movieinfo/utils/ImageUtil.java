package ca.sharipov.serhii.movieinfo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtil {
    public static void loadImage(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(imageView);
    }

    public interface ImageLoadedCallback {
        void onImageLoaded();
    }
}
