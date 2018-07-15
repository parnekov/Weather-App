package com.parnekov.sasha.weathertwo.utils.view;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public final class WeatherAnimationUtil {

    public static void animImage(Context context, ImageView view, int anim) {
        Animation animation = AnimationUtils.loadAnimation(context, anim);
        view.startAnimation(animation);
    }
}
