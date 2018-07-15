package com.parnekov.sasha.weathertwo.utils.networking;

import android.net.Uri;
import android.os.Message;
import android.util.Log;

import com.parnekov.sasha.weathertwo.model.LocationModel;
import com.parnekov.sasha.weathertwo.model.WeatherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for getting information from server and parsing to model
 */
public final class WeatherNetworksUtils {

    private final static String API_KEY = "c4a2fab9875a4addca3b5ca41d0f8232";
    private final static String APPID = "appid";
    private final static String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    private final static String LATITUDE = "lat";
    private final static String LONGITUDE = "lon";
    private final static String COUNT = "cnt";
    private final static String NUM_DAYS = "5";
    private final static String CITY = "q";

    private static String builtURLByCity(String userCityName) {
        Uri buildUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(CITY, userCityName)
                .appendQueryParameter(COUNT, NUM_DAYS)
                .appendQueryParameter(APPID, API_KEY)
                .build();
        return buildUri.toString();
    }

    private static String builtURLByCoordinates() {
        Uri buildUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(LATITUDE, String.valueOf(LocationModel.getLocationModel().getLatitude()))
                .appendQueryParameter(LONGITUDE, String.valueOf(LocationModel.getLocationModel().getLongitude()))
                .appendQueryParameter(COUNT, NUM_DAYS)
                .appendQueryParameter(APPID, API_KEY)
                .build();
        Log.i("URL", buildUri.toString());
        return buildUri.toString();

    }

    private static URL getURL(String userCityName) {
        URL url = null;
        // create URL when GPS Turn On and
        try {
            // we have got a location by coordinates
            if (userCityName == null) {
                url = new URL(builtURLByCoordinates());
                // we have got a location by userCityName
            } else {
                url = new URL(builtURLByCity(userCityName));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    private static String getHTTPConnection(String userCityName, android.os.Handler handler) throws FileNotFoundException {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) getURL(userCityName).openConnection();
            if (connection == null) {
            }
        } catch (IOException e) {
        }
        InputStream stream = null;
        try {
            assert connection != null;
            stream = connection.getInputStream();

        } catch (IOException e) {

            if (stream == null) {
                LocationModel.getLocationModel().setBadRequest(true);

                Message message = Message.obtain();
                message.what = 0;
                handler.sendMessage(message);
                throw new FileNotFoundException();

            }
            e.printStackTrace();
        }

        String line = null;

        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        try {
            line = reader.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return line;

    }

    static List<WeatherModel> jsonParsing(String userCityName, android.os.Handler handler) throws FileNotFoundException {

        String  day,
                date,
                humidity,
                weatherConditions,
                temp,
                pressure,
                nameCity,
                country,
                tempMin,
                tempMax,
                tempNight,
                tempEvening,
                tempMorning;
        int     population,
                weatherConditionsID;
        final   List<WeatherModel> items = new ArrayList<>();

        try {
            JSONObject mainJO = new JSONObject(getHTTPConnection(userCityName, handler));  // JSON object
            LocationModel.getLocationModel().setBadRequest(false);

            JSONObject mainJOCity = mainJO.getJSONObject("city");  // JSON object City
            nameCity = mainJOCity.get("name").toString();
            country = mainJOCity.get("country").toString();
            population = mainJOCity.getInt("population");


            JSONArray mJsonArray = mainJO.getJSONArray("list");  // get Json Array(all information)
            // from main Json Object

            for (int i = 0; i < mJsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) mJsonArray.get(i);  // get Json object
                // (one weather day) from JsonArray
                JSONArray jsonWeatherArray = jsonObject.getJSONArray("weather"); // get JsonArray
                // (information from day)
                JSONObject weatherCondition = (JSONObject) jsonWeatherArray.get(0); // get weather
                // condition (weatherConditions, sun, clouds)


                JSONObject jsonObjectTemp = (JSONObject) jsonObject.get("temp"); // get temperature

                double temperatureDay = Double.parseDouble(jsonObjectTemp.get("day").toString())
                        - 273.15;  //  convert from Kelvin to Celsius;
                temp = String.valueOf((int) temperatureDay);

                double temperatureMin = Double.parseDouble(jsonObjectTemp.get("min").toString())
                        - 273.15;
                tempMin = String.valueOf((int) temperatureMin);

                double temperatureMax = Double.parseDouble(jsonObjectTemp.get("max").toString())
                        - 273.15;
                tempMax = String.valueOf((int) temperatureMax);

                double temperatureNight = Double.parseDouble(jsonObjectTemp.get("night").toString())
                        - 273.15;
                tempNight = String.valueOf((int) temperatureNight);

                double temperatureEvening = Double.parseDouble(jsonObjectTemp.get("eve").toString())
                        - 273.15;
                tempEvening = String.valueOf((int) temperatureEvening);

                double temperatureMorning = Double.parseDouble(jsonObjectTemp.get("morn").toString())
                        - 273.15;
                tempMorning = String.valueOf((int) temperatureMorning);

                date = jsonObject.get("dt").toString();  // get date
                int dateINT = Integer.valueOf(date);
                date = new SimpleDateFormat("d MMMM").format(new Date(dateINT * 1000L));
                // convert from unix, UTC // get date
                day = new SimpleDateFormat("EEEE").format(new Date(dateINT * 1000L));

                humidity = jsonObject.get("humidity").toString(); // get humidity

                weatherConditionsID = weatherCondition.getInt("id"); // get number
                weatherConditions = weatherCondition.get("description").toString(); // get description

                pressure = jsonObject.get("pressure").toString();  // get pressure

                WeatherModel weatherItem = new WeatherModel(
                        nameCity,
                        country,
                        temp,
                        weatherConditions,
                        weatherConditionsID,
                        humidity,
                        pressure,
                        date,
                        day,
                        population,
                        tempMin,
                        tempMax,
                        tempNight,
                        tempEvening,
                        tempMorning);

                items.add(weatherItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;   // return List<WeatherModel>
    }
}