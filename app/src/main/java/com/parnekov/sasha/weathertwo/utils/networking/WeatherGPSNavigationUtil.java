package com.parnekov.sasha.weathertwo.utils.networking;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.parnekov.sasha.weathertwo.R;
import com.parnekov.sasha.weathertwo.model.LocationModel;

/** Class for work GPS Navigation   */
public final class WeatherGPSNavigationUtil {
    private static final int REQUEST_LOCATION = 1;
    private LocationManager mLocationManager;

    // get Internet Connection
    public static boolean getInternetConnection(AppCompatActivity activity) {
        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else { connected = false; }
        return connected;
    }

    // check connection to gps
    public boolean getLocationNow(AppCompatActivity activity) {
        boolean connected;
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        mLocationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps(activity);
            connected = false;
        } else if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation(activity);
            connected = true; }
            else {connected = false;}
        return connected;
    }

    // get Location
    private void getLocation(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                LocationModel.getLocationModel().setLatitude(location.getLatitude());
                LocationModel.getLocationModel().setLongitude(location.getLongitude());
            } else {
                Toast.makeText(activity, activity.getText(R.string.non_location), Toast.LENGTH_SHORT).show();
                LocationModel.getLocationModel().setSearchRequest(0);
            }
        }
    }

    // create
    public void buildAlertMessageNoGps(final Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(R.string.turn_on_gps)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        activity.startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}


