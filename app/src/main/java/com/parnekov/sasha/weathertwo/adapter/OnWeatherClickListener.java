package com.parnekov.sasha.weathertwo.adapter;

import com.parnekov.sasha.weathertwo.model.WeatherModel;

/** Interface for work with adapter, list item view*/
public interface OnWeatherClickListener {
    void onWeatherClicked(WeatherModel model);
}
