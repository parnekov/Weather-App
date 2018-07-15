package com.parnekov.sasha.weathertwo.utils.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.parnekov.sasha.weathertwo.R;
import com.parnekov.sasha.weathertwo.model.WeatherModel;

/**
 * Util class for setting colors and images condition in WeatherDayActivity according to weather
 */
public final class WeatherSetColorAndImages {

    public static void setColorForTemperature(Context context, String getTemperature, TextView textViewDetail, TextView textViewCelcium) {
        int temperature = Integer.valueOf(getTemperature);
        if (temperature >= -20 & temperature <= -11) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber__20__10), textViewDetail, textViewCelcium);
        } else if (temperature >= -10 & temperature <= -6) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber__10__5), textViewDetail, textViewCelcium);
        } else if (temperature >= -5 & temperature <= 0) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber__5_0), textViewDetail, textViewCelcium);
        } else if (temperature >= 1 & temperature <= 5) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber_0_5), textViewDetail, textViewCelcium);
        } else if (temperature >= 6 & temperature <= 9) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber_5_10), textViewDetail, textViewCelcium);
        } else if (temperature >= 10 & temperature <= 14) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber_10_15), textViewDetail, textViewCelcium);
        } else if (temperature >= 15 & temperature <= 19) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber_15_20), textViewDetail, textViewCelcium);
        } else if (temperature >= 20 & temperature <= 24) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber_20_25), textViewDetail, textViewCelcium);
        } else if (temperature >= 25 & temperature <= 30) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber_25_30), textViewDetail, textViewCelcium);
        } else if (temperature >= 31 & temperature <= 40) {
            setColor(context.getResources().getColor(R.color.colorTextTempBigNumber_30_40), textViewDetail, textViewCelcium);
        } else {
            textViewDetail.setTextColor(context.getResources().getColor(R.color.colorTransparent));
            textViewDetail.setTextColor(context.getResources().getColor(R.color.colorTransparent));
        }
    }

    private static void setColor(int color, TextView textViewDetail, TextView textViewCelcium) {
        textViewDetail.setTextColor(color);
        textViewCelcium.setTextColor(color);
    }

    public static void setImageCondition(WeatherModel model, ImageView imageView) {

        if (model.getConditionID() >= 200 & model.getConditionID() <= 504 ||
                model.getConditionID() >= 520 & model.getConditionID() <= 531) {
            imageView.setImageResource(R.drawable.var_rain);
        } else if (model.getConditionID() == 511) {
            imageView.setImageResource(R.drawable.var_sleet);
        } else if (model.getConditionID() == 600 || model.getConditionID() == 601) {
            imageView.setImageResource(R.drawable.var_snow_occasional);
        } else if (model.getConditionID() == 602) {
            imageView.setImageResource(R.drawable.var_cold);
        } else if (model.getConditionID() >= 611 & model.getConditionID() <= 616) {
            imageView.setImageResource(R.drawable.var_sleet);
        } else if (model.getConditionID() >= 620 & model.getConditionID() <= 622) {
            imageView.setImageResource(R.drawable.var_cold);
        } else if (model.getConditionID() == 800) {
            imageView.setImageResource(R.drawable.var_sunny);
        } else if (model.getConditionID() >= 801 & model.getConditionID() <= 804) {
            imageView.setImageResource(R.drawable.var_clouds);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher_round);
        }
    }
}
