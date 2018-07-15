package com.parnekov.sasha.weathertwo.utils.view;

import android.content.Context;
import android.widget.TextView;

import com.parnekov.sasha.weathertwo.R;


/**
 * Util class for setting Condition and Pressure text in WeatherDayActivity according to information from server
 */
public final class WeatherSetConditionAndPressure {

    private static final String skyIsClear = "sky is clear";
    private static final String cloudsFew = "few clouds";
    private static final String cloudsScattered = "scattered clouds";
    private static final String cloudsBroken = "broken clouds";
    private static final String cloudsOvercast = "overcast clouds";

    private static final String thunderstormWithLightRain = "thunderstorm with light rain";
    private static final String thunderstormWithRain = "thunderstorm with rain";
    private static final String thunderstormWithHeavyRain = "thunderstorm with heavy rain";
    private static final String thunderstormLight = "light thunderstorm";
    private static final String thunderstorm = "thunderstorm";
    private static final String thunderstormHeavy = "heavy thunderstorm";
    private static final String thunderstormRagged = "ragged thunderstorm";
    private static final String thunderstormWithLightDrizzle = "thunderstorm with light drizzle";
    private static final String thunderstormWithHeavyDrizzle = "thunderstorm with heavy drizzle";
    private static final String thunderstormWithDrizzle = "thunderstorm with drizzle";

    private static final String drizzle = "drizzle";
    private static final String drizzleRain = "drizzle rain";
    private static final String drizzleShower = "shower drizzle";
    private static final String drizzleLightIntensity = "light intensity drizzle";
    private static final String drizzleLightIntensityRain = "light intensity drizzle rain";
    private static final String drizzleHeavyIntensity = "heavy intensity drizzle";
    private static final String drizzleHeavyIntensityRain = "heavy intensity drizzle rain";
    private static final String drizzleShowerRain = "shower rain and drizzle";
    private static final String drizzleHeavyShowerRain = "heavy shower rain and drizzle";

    private static final String rainLight = "light rain";
    private static final String rainModerate = "moderate rain";
    private static final String rainHeavyIntensity = "heavy intensity rain";
    private static final String rainHeavyVery = "very heavy rain";
    private static final String rainExtreme = "extreme rain";
    private static final String rainFreezing = "freezing rain";
    private static final String rainLightIntensityShower = "light intensity shower rain";
    private static final String rainShower = "shower rain";
    private static final String rainShowerRagged = "ragged shower rain";
    private static final String rainHeavyIntensityShower = "heavy intensity shower rain";

    private static final String snowLight = "light snow";
    private static final String snow = "snow";
    private static final String snowHeavy = "heavy snow";
    private static final String sleet = "sleet";
    private static final String snowLightShower = "light shower snow";
    private static final String snowHeavyShower = "heavy shower snow";
    private static final String snowShower = "shower snow";
    private static final String snowLightRain = "light rain and snow";
    private static final String snowRain = "rain and snow";
    private static final String sleetShower = "shower sleet";

    public static void equalsStringCondition(TextView textView, String weatherModelString, String string, String getStrings) {
        if (weatherModelString.equals(string)) {
            textView.setText(getStrings);
        }
    }

    public static void setCondition(TextView textView, Context context, String weatherModelString) {
        equalsStringCondition(textView, weatherModelString, skyIsClear, context.getResources().getString(R.string.sky_is_clear));
        equalsStringCondition(textView, weatherModelString, cloudsFew, context.getResources().getString(R.string.clouds_few));
        equalsStringCondition(textView, weatherModelString, cloudsScattered, context.getResources().getString(R.string.clouds_scattered));
        equalsStringCondition(textView, weatherModelString, cloudsBroken, context.getResources().getString(R.string.clouds_broken));
        equalsStringCondition(textView, weatherModelString, cloudsOvercast, context.getResources().getString(R.string.clouds_overcast));

        equalsStringCondition(textView, weatherModelString, thunderstormWithLightRain, context.getResources().getString(R.string.thunderstorm_with_light_rain));
        equalsStringCondition(textView, weatherModelString, thunderstormWithRain, context.getResources().getString(R.string.thunderstorm_with_rain));
        equalsStringCondition(textView, weatherModelString, thunderstormWithHeavyRain, context.getResources().getString(R.string.thunderstorm_with_heavy_rain));
        equalsStringCondition(textView, weatherModelString, thunderstormLight, context.getResources().getString(R.string.thunderstorm_light));
        equalsStringCondition(textView, weatherModelString, thunderstorm, context.getResources().getString(R.string.thunderstorm));
        equalsStringCondition(textView, weatherModelString, thunderstormHeavy, context.getResources().getString(R.string.thunderstorm_heavy));
        equalsStringCondition(textView, weatherModelString, thunderstormRagged, context.getResources().getString(R.string.thunderstorm_ragged));
        equalsStringCondition(textView, weatherModelString, thunderstormWithLightDrizzle, context.getResources().getString(R.string.thunderstorm_with_light_drizzle));
        equalsStringCondition(textView, weatherModelString, thunderstormWithHeavyDrizzle, context.getResources().getString(R.string.thunderstorm_with_heavy_drizzle));
        equalsStringCondition(textView, weatherModelString, thunderstormWithDrizzle, context.getResources().getString(R.string.thunderstorm_with_drizzle));

        equalsStringCondition(textView, weatherModelString, drizzle, context.getResources().getString(R.string.drizzle));
        equalsStringCondition(textView, weatherModelString, drizzleRain, context.getResources().getString(R.string.drizzle_rain));
        equalsStringCondition(textView, weatherModelString, drizzleShower, context.getResources().getString(R.string.drizzle_shower));
        equalsStringCondition(textView, weatherModelString, drizzleLightIntensity, context.getResources().getString(R.string.drizzle_light_intensity));
        equalsStringCondition(textView, weatherModelString, drizzleLightIntensityRain, context.getResources().getString(R.string.drizzle_light_intensity_rain));
        equalsStringCondition(textView, weatherModelString, drizzleHeavyIntensity, context.getResources().getString(R.string.drizzle_heavy_intensity));
        equalsStringCondition(textView, weatherModelString, drizzleHeavyIntensityRain, context.getResources().getString(R.string.drizzle_heavy_intensity_rain));
        equalsStringCondition(textView, weatherModelString, drizzleShowerRain, context.getResources().getString(R.string.drizzle_shower_rain));
        equalsStringCondition(textView, weatherModelString, drizzleHeavyShowerRain, context.getResources().getString(R.string.drizzle_shower_rain_heavy));

        equalsStringCondition(textView, weatherModelString, rainLight, context.getResources().getString(R.string.rain_light));
        equalsStringCondition(textView, weatherModelString, rainModerate, context.getResources().getString(R.string.rain_moderate));
        equalsStringCondition(textView, weatherModelString, rainHeavyIntensity, context.getResources().getString(R.string.rain_heavy_intensity));
        equalsStringCondition(textView, weatherModelString, rainHeavyVery, context.getResources().getString(R.string.rain_heavy_very));
        equalsStringCondition(textView, weatherModelString, rainExtreme, context.getResources().getString(R.string.rain_extreme));
        equalsStringCondition(textView, weatherModelString, rainFreezing, context.getResources().getString(R.string.rain_freezing));
        equalsStringCondition(textView, weatherModelString, rainLightIntensityShower, context.getResources().getString(R.string.rain_light_intensity_shower));
        equalsStringCondition(textView, weatherModelString, rainShower, context.getResources().getString(R.string.rain_shower));
        equalsStringCondition(textView, weatherModelString, rainShowerRagged, context.getResources().getString(R.string.rain_shower_ragged));
        equalsStringCondition(textView, weatherModelString, rainHeavyIntensityShower, context.getResources().getString(R.string.rain_heavy_intensity_shower));

        equalsStringCondition(textView, weatherModelString, snowLight, context.getResources().getString(R.string.snow_light));
        equalsStringCondition(textView, weatherModelString, snow, context.getResources().getString(R.string.snow));
        equalsStringCondition(textView, weatherModelString, snowHeavy, context.getResources().getString(R.string.snow_heavy));
        equalsStringCondition(textView, weatherModelString, sleet, context.getResources().getString(R.string.sleet));
        equalsStringCondition(textView, weatherModelString, snowLightShower, context.getResources().getString(R.string.snow_light_shower));
        equalsStringCondition(textView, weatherModelString, snowHeavyShower, context.getResources().getString(R.string.snow_heavy_shower));
        equalsStringCondition(textView, weatherModelString, snowShower, context.getResources().getString(R.string.snow_shower));
        equalsStringCondition(textView, weatherModelString, snowLightRain, context.getResources().getString(R.string.snow_light_rain));
        equalsStringCondition(textView, weatherModelString, snowRain, context.getResources().getString(R.string.snow_rain));
        equalsStringCondition(textView, weatherModelString, sleetShower, context.getResources().getString(R.string.sleet_shower));
    }

    public static String setPressure(Context context, String pressureString) {
        double pressure = Double.valueOf(pressureString);
        if (pressure < 950.00) {
            pressureString = context.getResources().getString(R.string.pressure_lowered);
        } else if (pressure > 950.00 & pressure <= 1020.00) {
            pressureString = context.getResources().getString(R.string.pressure_normal);
        } else if (pressure > 1020.00) {
            pressureString = context.getResources().getString(R.string.pressure_high);
        }
        return pressureString;
    }
}
