package com.parnekov.sasha.weathertwo.utils.networking;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.parnekov.sasha.weathertwo.R;
import com.parnekov.sasha.weathertwo.adapter.WeatherAdapter;
import com.parnekov.sasha.weathertwo.model.WeatherModel;

import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;

/** Class for background work */
public final class WeatherJSONTask extends AsyncTask<String, Void, List<WeatherModel>> {
    @BindView(R.id.progress_layout)
    public LinearLayout mProgress;

    private WeatherAdapter mWeatherAdapter;
    private Handler mHandler;

    public WeatherJSONTask(WeatherAdapter adapter, LinearLayout progress, Handler handler) {
        mWeatherAdapter = adapter;
        mProgress = progress;
        mHandler = handler;
    }

    @Override
    public List<WeatherModel> doInBackground(String... strings) {
        List<WeatherModel> list = null;
        try {
            list = WeatherNetworksUtils.jsonParsing(strings[0], mHandler);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void onPostExecute(List<WeatherModel> weatherModels) {
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        }

        if (weatherModels == null) {
            if (mProgress != null) {
                mProgress.setVisibility(View.VISIBLE);
            }
            mWeatherAdapter.updateWeatherListFromDatabase();
            mWeatherAdapter.notifyDataSetChanged();
            mProgress.setVisibility(View.INVISIBLE);
        } else {
            Log.d("WWW", "onPostExecute: " + weatherModels.get(0).getCity());
            mWeatherAdapter.updateWeatherListFromAPI(weatherModels);
            mWeatherAdapter.notifyDataSetChanged();
            mProgress.setVisibility(View.INVISIBLE);
        }
    }
}
