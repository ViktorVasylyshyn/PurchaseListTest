package com.vikrotvasylyshyn.purchaselist.utill;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vikrotvasylyshyn.purchaselist.R;

public class ImageLoader {
    public static void loadPurchaseImage(ImageView imageView, String url) {
        final RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .apply(RequestOptions.fitCenterTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }
}
