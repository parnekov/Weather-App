package com.parnekov.sasha.weathertwo.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/** Model Class */
@Entity(tableName = "weather")
public class WeatherModel implements Parcelable {

    @PrimaryKey(autoGenerate = true) private int uid;
    @ColumnInfo(name = "city") private String mCity;
    @ColumnInfo(name = "country") private String mCountry;
    @ColumnInfo(name = "temperature") private String mTemperature;
    @ColumnInfo(name = "date") private String mDate;
    @ColumnInfo(name = "day") private String mDay;
    @ColumnInfo(name = "humidity") private String mHumidity;
    @ColumnInfo(name = "condition") private String mCondition;
    @ColumnInfo(name = "conditionId") private int mConditionID;
    @ColumnInfo(name = "pressure") private String mPressure;
    @ColumnInfo(name = "population") private int mPopulation;
    @ColumnInfo(name = "tempMin") private String mTempMin;
    @ColumnInfo(name = "tempMax") private String mTempMax;
    @ColumnInfo(name = "tempNight") private String mTempNight;
    @ColumnInfo(name = "tempEvening") private String mTempEvening;
    @ColumnInfo(name = "tempMorning") private String mTempMorning;


    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getCity() {
        return mCity;
    }
    public String getCountry() {
        return mCountry;
    }
    public String getTemperature() {
        return mTemperature;
    }
    public String getDate() {
        return mDate;
    }
    public void setDate(String date) {
        mDate = date;
    }
    public String getDay() {
        return mDay;
    }
    public String getHumidity() {
        return mHumidity;
    }
    public String getCondition() {
        return mCondition;
    }
    public int getConditionID() {
        return mConditionID;
    }
    public String getPressure() {
        return mPressure;
    }
    public int getPopulation() {
        return mPopulation;
    }
    public String getTempMin() {
        return mTempMin;
    }
    public String getTempMax() {
        return mTempMax;
    }
    public String getTempNight() {
        return mTempNight;
    }
    public String getTempEvening() {
        return mTempEvening;
    }
    public String getTempMorning() {
        return mTempMorning;
    }

    public WeatherModel(String city,
                        String country,
                        String temperature,
                        String condition,
                        int conditionID,
                        String humidity,
                        String pressure,
                        String date,
                        String day,
                        int population,
                        String tempMin,
                        String tempMax,
                        String tempNight,
                        String tempEvening,
                        String tempMorning) {
        mCity = city;
        mCountry = country;
        mTemperature = temperature;
        mCondition = condition;
        mConditionID = conditionID;
        mHumidity = humidity;
        mPressure = pressure;
        mDate = date;
        mDay = day;
        mPopulation = population;
        mTempMin = tempMin;
        mTempMax = tempMax;
        mTempNight = tempNight;
        mTempEvening = tempEvening;
        mTempMorning = tempMorning;

    }

    private WeatherModel(Parcel in) {
        mCity = in.readString();
        mCountry = in.readString();
        mTemperature = in.readString();
        mCondition = in.readString();
        mConditionID = in.readInt();
        mHumidity = in.readString();
        mPressure = in.readString();
        mDate = in.readString();
        mDay = in.readString();
        mPopulation = in.readInt();
        mTempMin = in.readString();
        mTempMax = in.readString();
        mTempNight = in.readString();
        mTempEvening = in.readString();
        mTempMorning = in.readString();

    }

    public static final Creator<WeatherModel> CREATOR = new Creator<WeatherModel>() {
        @Override
        public WeatherModel createFromParcel(Parcel in) {
            return new WeatherModel(in);
        }

        @Override
        public WeatherModel[] newArray(int size) {
            return new WeatherModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mCity);
        parcel.writeString(mCountry);
        parcel.writeString(mTemperature);
        parcel.writeString(mCondition);
        parcel.writeInt(mConditionID);
        parcel.writeString(mHumidity);
        parcel.writeString(mPressure);
        parcel.writeString(mDate);
        parcel.writeString(mDay);
        parcel.writeInt(mPopulation);
        parcel.writeString(mTempMin);
        parcel.writeString(mTempMax);
        parcel.writeString(mTempNight);
        parcel.writeString(mTempEvening);
        parcel.writeString(mTempMorning);
    }
}
