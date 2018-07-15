package com.parnekov.sasha.weathertwo.utils.view;

import android.widget.ImageView;

import com.parnekov.sasha.weathertwo.R;


/**
 * Util class for setting icons in WeatherDayActivity according to weather
 */
public final class WeatherSetClosesImages {

    public static void setClosesIcons(ImageView imageView1, ImageView imageView2, ImageView imageView3, int weatherCode, String temp) {
        int tempIntegerValue = Integer.valueOf(temp);

        // Rain
        if (weatherCode >= 200 & weatherCode <= 531) {
            if (tempIntegerValue >= -10 & tempIntegerValue <= -1) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._010_01_rain_left_umbrella, R.drawable._010_01_rain_central__winter_hat, R.drawable._010_01_rain_right_boots);
            } else if (tempIntegerValue >= 0 & tempIntegerValue <= 10) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._00_10_rain_left_umbrellas, R.drawable._00_10_rain_central_sweater, R.drawable._00_10_rain_right_boots);
            } else if (tempIntegerValue >= 11 & tempIntegerValue <= 20) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._11_20_rain_left_umbrella, R.drawable._11_20_rain_central_trousers, R.drawable._11_20_rain_right_hoodie);
            } else if (tempIntegerValue >= 21 & tempIntegerValue <= 25) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._21_25_rain_left_umbrella, R.drawable._21_25_rain_central_sneakers, R.drawable._21_25_rain_right_shorts);
            } else if (tempIntegerValue >= 26 & tempIntegerValue <= 31) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._26_31_rain_left_umbrella, R.drawable._26_31_rain_central_swimsuit, R.drawable._26_31_rain_right_flops);
            } else if (tempIntegerValue >= 32 & tempIntegerValue <= 40) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._32_40_rain_left_sun_umbrella, R.drawable._32_40_rain_central_underwear, R.drawable._32_40_rain_right_suitcase);
            } else {
                imageIcons(imageView1, imageView2, imageView3, R.drawable._010_01_rain_left_umbrella, R.drawable._010_01_rain_left_umbrella, R.drawable._010_01_rain_left_umbrella);
            }
        }

        // Snow
        if (weatherCode >= 600 & weatherCode <= 622) {
            if (tempIntegerValue >= -20 & tempIntegerValue <= -11) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._020_011_snow_left_boot, R.drawable._020_011_snow_central_coat, R.drawable._020_011_snow_rigth_ski);
            } else if (tempIntegerValue >= -10 & tempIntegerValue <= 0) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._010_01_snow_left_scarf, R.drawable._010_01_snow_central_mittens, R.drawable._010_01_snow_right_boot);
            } else if (tempIntegerValue >= 1 & tempIntegerValue <= 10) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._00_10_snow_left_gloves, R.drawable._00_10_snow_central_jacket, R.drawable._00_10_snow_rigth_boot);
            } else {
                imageIcons(imageView1, imageView2, imageView3, R.drawable._010_01_snow_left_scarf, R.drawable._010_01_snow_left_scarf, R.drawable._010_01_snow_left_scarf);
            }
        }

        // Sunny
        if (weatherCode == 800) {
            if (tempIntegerValue >= -20 & tempIntegerValue <= -11) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._020_011_sunnny_left_scarf, R.drawable._020_011_sunny_central_jacket, R.drawable._020_011_sunny_rigth_sled);
            } else if (tempIntegerValue >= -10 & tempIntegerValue <= -1) {
                imageIcons(imageView1, imageView2, imageView3,  // change
                        R.drawable._010_01_sunnny_left_scarf, R.drawable._010_01_sunnny_central_beanie, R.drawable._010_01_sunnny_right_socks);
            } else if (tempIntegerValue >= 0 & tempIntegerValue <= 10) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._00_10_sunny_left_hoodie, R.drawable._00_10_sunny_central_jeans, R.drawable._00_10_sunny_right_shoes);
            } else if (tempIntegerValue >= 11 & tempIntegerValue <= 20) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._11_20_sunny_left_jeans, R.drawable._11_20_sunny_central_shirt, R.drawable._11_20_sunny_right_shoe);
            } else if (tempIntegerValue >= 21 & tempIntegerValue <= 25) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._21_25_sunny_left_cap, R.drawable._21_25_sunny_central_sunglasses, R.drawable._21_25_sunny_right_slippers);
            } else if (tempIntegerValue >= 26 & tempIntegerValue <= 31) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._26_31_sunny_leftcap, R.drawable._26_31_sunny_central_basketball, R.drawable._26_31_sunny_right_slippers);
            } else if (tempIntegerValue >= 31 & tempIntegerValue <= 40) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._32_40_sunny_left_sunglasses, R.drawable._32_40_sunny_central_beauty, R.drawable._32_40_sunny_right_footprints);
            } else {
                imageIcons(imageView1, imageView2, imageView3, R.drawable._32_40_sunny_central_beauty, R.drawable._32_40_sunny_central_beauty, R.drawable._32_40_sunny_central_beauty);

            }
        }

        // Clouds
        if (weatherCode >= 801 & weatherCode <= 804) {
            if (tempIntegerValue >= -20 & tempIntegerValue <= -11) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._020_011_cloud_left_helmet, R.drawable._020_011_cloud_central_jacket, R.drawable._020_011_cloud_rigth_gloves);
            } else if (tempIntegerValue >= -10 & tempIntegerValue <= -1) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._010_01_cloud_left_winter_hat, R.drawable._010_01_cloud_central_gloves, R.drawable._010_01_cloud_right_socks);
            } else if (tempIntegerValue >= 0 & tempIntegerValue <= 10) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._00_10_cloud_left_hat, R.drawable._00_10_cloud_central_coat, R.drawable._00_10_cloud_right_shoes);
            } else if (tempIntegerValue >= 11 & tempIntegerValue <= 20) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._11_20_clouds_left_hat, R.drawable._11_20_clouds_central_jacket, R.drawable._11_20_clouds_right_shoes);
            } else if (tempIntegerValue >= 21 & tempIntegerValue <= 25) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._21_25_clouds_left_brain, R.drawable._21_25_clouds_central_polo, R.drawable._21_25_clouds_right_boots);
            } else if (tempIntegerValue >= 26 & tempIntegerValue <= 31) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._26_31_cloud_left_shorts, R.drawable._26_31_cloud_central_tshirt, R.drawable._26_31_cloud_right_slipper);
            } else if (tempIntegerValue >= 32 & tempIntegerValue <= 40) {
                imageIcons(imageView1, imageView2, imageView3,
                        R.drawable._32_40_cloud_left_underwear, R.drawable._32_40_cloud_central_men, R.drawable._32_40_cloud_right_slipper);
            } else {
                imageIcons(imageView1, imageView2, imageView3, R.drawable._11_20_clouds_central_jacket, R.drawable._11_20_clouds_central_jacket, R.drawable._11_20_clouds_central_jacket);
            }
        }
    }

    private static void imageIcons(ImageView imageView1, ImageView imageView2, ImageView imageView3, int icon1, int icon2, int icon3) {
        imageView1.setImageResource(icon1);
        imageView2.setImageResource(icon2);
        imageView3.setImageResource(icon3);
    }

}
