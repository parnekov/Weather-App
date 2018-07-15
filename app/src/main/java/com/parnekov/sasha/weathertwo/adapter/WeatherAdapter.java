package com.parnekov.sasha.weathertwo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parnekov.sasha.weathertwo.R;
import com.parnekov.sasha.weathertwo.db.MyDataBase;
import com.parnekov.sasha.weathertwo.model.WeatherModel;
import com.parnekov.sasha.weathertwo.utils.view.WeatherSetColorAndImages;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/** Recycler View Class for work list*/
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;
    private final Context mContext;
    private boolean mUseTodayLayout;
    private List<WeatherModel> mWeatherModelList = new ArrayList<>();
    private OnWeatherClickListener mClickListener;

    public WeatherAdapter(Context context, OnWeatherClickListener weatherClickListener) {
        mContext = context;
        mClickListener = weatherClickListener;
        mUseTodayLayout = mContext.getResources().getBoolean(R.bool.use_today_layout);
    }

    @Override
    public WeatherAdapter.WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // choose need layout
        int layoutId;
        switch (viewType) {
            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.list_item_today;
                break;
            }
            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.list_item_new;
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(layoutId, parent, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        WeatherModel weatherItem = mWeatherModelList.get(position);
        Context context = holder.itemView.getContext();

        holder.mTextViewDay.setText(weatherItem.getDay());
        holder.mTextViewTemperature.setText(context.getString(R.string.text_temperature, weatherItem.getTemperature()));

        // get views which initialized without ButterKnife
        if (holder.getItemViewType() == 0) {
            holder.mTextViewCity.setText(weatherItem.getCity());
            holder.mTextViewCountry.setText(weatherItem.getCountry());
        }
        WeatherSetColorAndImages.setImageCondition(weatherItem, holder.mImageView);
    }


    @Override
    public int getItemCount() {
        return mWeatherModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mUseTodayLayout && position == 0) {
            return VIEW_TYPE_TODAY;
        } else {return VIEW_TYPE_FUTURE_DAY; }
    }

    class WeatherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_day_of_week) TextView mTextViewDay;
        @BindView(R.id.tv_temperature) TextView mTextViewTemperature;
        @BindView(R.id.tv_image) ImageView mImageView;

        // declare without ButterKnife because variables need in different layouts
        TextView mTextViewCity, mTextViewCountry;


        WeatherHolder(View itemView) {
            super(itemView);
            // initialized without ButterKnife because variables need in different layouts
            mTextViewCity = itemView.findViewById(R.id.tv_city);
            mTextViewCountry = itemView.findViewById(R.id.tv_country);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // get interface method
            mClickListener.onWeatherClicked(mWeatherModelList.get(getAdapterPosition()));
        }
    }

    public void updateWeatherListFromAPI(List<WeatherModel> weatherModels) {
        MyDataBase.deleteAll(mContext);
        MyDataBase.insertRoom(mContext, weatherModels);
        mWeatherModelList = weatherModels;
        notifyDataSetChanged();
    }

    public void updateWeatherListFromDatabase() {
        mWeatherModelList = MyDataBase.getRoom(mContext);
        notifyDataSetChanged();
    }

}


