package com.parnekov.sasha.weathertwo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parnekov.sasha.weathertwo.R;
import com.parnekov.sasha.weathertwo.adapter.OnWeatherClickListener;
import com.parnekov.sasha.weathertwo.adapter.WeatherAdapter;
import com.parnekov.sasha.weathertwo.db.MyDataBase;
import com.parnekov.sasha.weathertwo.model.LocationModel;
import com.parnekov.sasha.weathertwo.model.WeatherModel;
import com.parnekov.sasha.weathertwo.utils.intent.IntentUtils;
import com.parnekov.sasha.weathertwo.utils.networking.WeatherGPSNavigationUtil;
import com.parnekov.sasha.weathertwo.utils.networking.WeatherJSONTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/** WeatherListActivity (Main) for showing list of weather*/
public class WeatherListActivity extends AppCompatActivity implements OnWeatherClickListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_main_layout_ID) LinearLayout mFrameLayoutMain;
    @BindView(R.id.recycler_layout) RecyclerView mRecyclerView;
    @BindView(R.id.progress_layout) LinearLayout mProgress;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.toolbar_container) RelativeLayout relativeLayout;
    @BindView(R.id.edit_text_user) EditText mEditText;

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;

    public final static String EXTRA_WEATHER = "WEATHER";
    private WeatherAdapter mAdapter;
    private WeatherGPSNavigationUtil weatherGPSNavigationUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);  // initialize views
        includeToolBar();    // create toolbar
        createDrawer();
        onCreateRecycler();  // create adapter
        getContent(this, LocationModel.getLocationModel().getFinalPlace());  // get information about weather

    }


    @Override  // when click on itemView in adapter replace in WeatherDayActivity.class
    public void onWeatherClicked(WeatherModel model) {
        Intent intent = new Intent(this, WeatherDayActivity.class);
        intent.putExtra(EXTRA_WEATHER, model);
        startActivity(intent);
    }


    // get information from server or database
    public void getContent(AppCompatActivity context, String userCity) {
        weatherGPSNavigationUtil = new WeatherGPSNavigationUtil();
        if (WeatherGPSNavigationUtil.getInternetConnection(context) & weatherGPSNavigationUtil.getLocationNow(context)) {
            // if we have net connection
            if (LocationModel.getLocationModel().getSearchRequest() == 1) {
                // when user put the city
                getJSON(userCity);
            } else if (LocationModel.getLocationModel().getSearchRequest() == 0 & LocationModel.getLocationModel().getLatitude() != 0)
                // null because we have lon and lat
                getJSON(null);
        } else {
            // if we do not have net connection get info from database
            if (MyDataBase.getRoom(this).size() >0){
                mAdapter.updateWeatherListFromDatabase();
                mAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(context, "Turn On The Internet", Toast.LENGTH_SHORT).show();
            }

            mProgress.setVisibility(View.GONE);
        }
    }


    // create AsyncTask
    public void getJSON(String userType) {
        WeatherJSONTask jsonTask = new WeatherJSONTask(mAdapter, mProgress, handler);
        jsonTask.execute(userType);
    }


    // create RecyclerView
    private void onCreateRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new WeatherAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    // create Dialog for user put the city
    public void onCreateDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog, null);
        final EditText editText = mView.findViewById(R.id.edit_text_user);
        final Button button = mView.findViewById(R.id.button_OK);
        final Button button1 = mView.findViewById(R.id.button_cancel);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(WeatherListActivity.this, R.string.put_city, Toast.LENGTH_SHORT).show();
                } else {
                    LocationModel.getLocationModel().setFinalPlace(editText.getText().toString());
                    LocationModel.getLocationModel().setSearchRequest(1);
                    if (LocationModel.getLocationModel().getSearchRequest() == 1) {
                        getJSON(LocationModel.getLocationModel().getFinalPlace());
                    }
                    dialog.dismiss();
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    // create Dialog turnOn the Internet
    private void buildAlertMessageNoInternet(final Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(R.string.turn_on_internet)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        activity.startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), 1);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        activity.finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }



    // create Toolbar
    private void includeToolBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSupportActionBar(mToolbar);
        }
        View viewInflate = getLayoutInflater().inflate(R.layout.toolbar_simple, null, false);
        relativeLayout.removeAllViews();
        relativeLayout.addView(viewInflate);

        relativeLayout.findViewById(R.id.toolbarbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (WeatherGPSNavigationUtil.getInternetConnection(WeatherListActivity.this) & weatherGPSNavigationUtil.getLocationNow(WeatherListActivity.this)) {   // доробити
                    onCreateDialog();
                } else {
                    Toast.makeText(WeatherListActivity.this, R.string.turn_on_internet, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // create Navigation Drawer Menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            if (WeatherGPSNavigationUtil.getInternetConnection(WeatherListActivity.this) & weatherGPSNavigationUtil.getLocationNow(WeatherListActivity.this)) {
                onCreateDialog();
            } else {
                Toast.makeText(WeatherListActivity.this, R.string.turn_on_internet, Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_update) {
            if (WeatherGPSNavigationUtil.getInternetConnection(WeatherListActivity.this) & weatherGPSNavigationUtil.getLocationNow(WeatherListActivity.this)) {
                getContent(this, LocationModel.getLocationModel().getFinalPlace());

                mDrawerLayout.closeDrawer(Gravity.START);
                Toast.makeText(this, R.string.nav_updating, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(WeatherListActivity.this, R.string.turn_on_internet, Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_share) {
            IntentUtils.shareIntent(this);
        } else if (id == R.id.nav_send) {
            IntentUtils.emailIntent(this);
        } else if (id == R.id.nav_feedback) {
            IntentUtils.feedbackIntent(this);
        } else if (id == R.id.nav_info) {
            IntentUtils.infoIntent(this);
        }

        return true;
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    // create Handler for get message from AsyncTask when user put incorrect city
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getApplicationContext(), "Put correct information", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    });

    private void createDrawer(){
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
}

