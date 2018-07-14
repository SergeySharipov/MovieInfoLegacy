package ca.sharipov.serhii.movieinfo.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;

public class ImageUtil {
    public static void loadImage(Fragment fragment, ImageView imageView, String url) {
        Glide.with(fragment)
                .load(url)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(Context context, final ImageView imageView,
                                 String url, @Nullable final ImageLoadedCallback callback) {
        Glide.with(context)
                .load(url)
                .crossFade()
                .into(new ImageViewTarget<GlideDrawable>(imageView) {
                    @Override
                    protected void setResource(GlideDrawable resource) {
                        imageView.setImageDrawable(resource);
                        if (callback != null) {
                            callback.onImageLoaded();
                        }
                    }
                });
    }

    public interface ImageLoadedCallback {
        void onImageLoaded();
    }
}
