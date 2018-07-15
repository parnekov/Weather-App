package com.parnekov.sasha.weathertwo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parnekov.sasha.weathertwo.R;
import com.parnekov.sasha.weathertwo.model.WeatherModel;
import com.parnekov.sasha.weathertwo.utils.view.WeatherAnimationUtil;
import com.parnekov.sasha.weathertwo.utils.view.WeatherSetClosesImages;
import com.parnekov.sasha.weathertwo.utils.view.WeatherSetColorAndImages;
import com.parnekov.sasha.weathertwo.utils.view.WeatherSetConditionAndPressure;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * WeatherDayActivity for showing one day of weather
 */
public class WeatherDayActivity extends AppCompatActivity {

    @BindView(R.id.tv_date) TextView mTextViewDate;
    @BindView(R.id.tv_day_of_week) TextView mTextViewDay;
    @BindView(R.id.tv_detail_temperature) TextView mTextViewTVDetail;
    @BindView(R.id.tv_condition) TextView mTextViewCondition;
    @BindView(R.id.tv_humidity) TextView mTextViewHumidity;
    @BindView(R.id.tv_pressure) TextView mTextViewPressure;
    @BindView(R.id.tv_image_detail) ImageView mImageView;
    @BindView(R.id.tv_city_day) TextView mTextViewCity;
    @BindView(R.id.tv_population) TextView mTextViewPopulation;
    @BindView(R.id.tv_night_temp) TextView mTextViewTempNight;
    @BindView(R.id.tv_evening_temp) TextView mTextViewTempEvening;
    @BindView(R.id.tv_morning_temp) TextView mTextViewTempMorning;
    @BindView(R.id.tv_day_temp) TextView mTextViewTempDay;
    @BindView(R.id.tv_celcium) TextView mTextViewCelsius;
    @BindView(R.id.image_view_left) ImageView mImageViewLeft;
    @BindView(R.id.image_view_central) ImageView mImageViewCentral;
    @BindView(R.id.image_view_right) ImageView mImageViewRight;
    @BindView(R.id.icons_layout) LinearLayout mLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_details_new);
        ButterKnife.bind(this);

        WeatherModel weatherModel = getIntent().getParcelableExtra(WeatherListActivity.EXTRA_WEATHER);

        mTextViewCity.setText(weatherModel.getCity());
        mTextViewPopulation.setText(this.getString(R.string.text_population, String.valueOf(weatherModel.getPopulation())));
        mTextViewDate.setText(weatherModel.getDate());
        mTextViewDay.setText(weatherModel.getDay());
        mTextViewHumidity.setText(this.getString(R.string.text_humidity, weatherModel.getHumidity()));
        mTextViewPressure.setText(this.getString(R.string.text_pressure, WeatherSetConditionAndPressure.setPressure(this, weatherModel.getPressure())));
        mTextViewTempNight.setText(this.getString(R.string.text_temperature, weatherModel.getTempNight()));
        mTextViewTempEvening.setText(this.getString(R.string.text_temperature, weatherModel.getTempEvening()));
        mTextViewTempMorning.setText(this.getString(R.string.text_temperature, weatherModel.getTempMorning()));
        mTextViewTempDay.setText(this.getString(R.string.text_temperature, weatherModel.getTemperature()));
        mTextViewTVDetail.setText(weatherModel.getTemperature());
        WeatherSetConditionAndPressure.setCondition(mTextViewCondition, this, weatherModel.getCondition());
        WeatherSetColorAndImages.setImageCondition(weatherModel, mImageView);
        WeatherSetColorAndImages.setColorForTemperature(this, weatherModel.getTemperature(), mTextViewTVDetail, mTextViewCelsius);
        WeatherSetClosesImages.setClosesIcons(mImageViewLeft, mImageViewCentral, mImageViewRight, weatherModel.getConditionID(), weatherModel.getTemperature());

        setAnimation();
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnimation();
            }
        });
    }

    private void setAnimation() {
        WeatherAnimationUtil.animImage(this, mImageViewLeft, R.anim.fade_left);
        WeatherAnimationUtil.animImage(this, mImageViewRight, R.anim.fade_right);
        WeatherAnimationUtil.animImage(this, mImageViewCentral, R.anim.fade);
    }
}
