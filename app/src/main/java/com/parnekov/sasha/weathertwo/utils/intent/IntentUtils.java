package com.parnekov.sasha.weathertwo.utils.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parnekov.sasha.weathertwo.R;
import com.parnekov.sasha.weathertwo.activity.WeatherWebViewActivity;

/** Class for work with intents*/

public final class IntentUtils {

    public static final String GOOGLE_PLAY = "GOOGLEPLAY";
    public static final String LINKEDIN = "LINKEDIN";
    public static final String FACEBOOK = "FACEBOOK";

    // Intent for sharing
    public static void shareIntent(Activity activity) {
        ShareCompat.IntentBuilder
                .from(activity)
                .setType("text/plain")
                .setChooserTitle(activity.getString(R.string.app_name))
                .setText(activity.getString(R.string.dialog_app_url)) //TODO write Url app
                .startChooser();
    }

    // Intent for create message
    public static void emailIntent(Context context) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.setData(Uri.parse("mailto:" + context.getString(R.string.email_address)));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.email_subject));
        emailIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.email_text_hello));

        checkIntentBeforeLaunching(context, emailIntent);
    }

    // Intent for create feedback in google play
    public static void feedbackIntent(Context context) {
        String url = context.getString(R.string.dialog_app_url); //TODO write Url app
        Intent feedbackIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        checkIntentBeforeLaunching(context, feedbackIntent);
    }

    // Intent for create dialog with information about app
    public static void infoIntent(final Context context) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        final View view = View.inflate(context, R.layout.dialog_info, null);
        final TextView developerUrl = view.findViewById(R.id.developerUrl);
        final ImageButton facebook = view.findViewById(R.id.button_facebook);
        final ImageButton linkedIn = view.findViewById(R.id.button_linkedin);
        final ImageButton github = view.findViewById(R.id.button_github);
        final Button buttonOk = view.findViewById(R.id.button_OK);
        mBuilder.setView(view);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        developerUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, context.getString(R.string.developer), Toast.LENGTH_SHORT).show();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUrlAndAction(context, WeatherWebViewActivity.class, FACEBOOK);
            }
        });
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUrlAndAction(context, WeatherWebViewActivity.class, LINKEDIN);

            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUrlAndAction(context, WeatherWebViewActivity.class, GOOGLE_PLAY);
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    // Intent for create web activity
    private static void getUrlAndAction(Context context, Class activityTo, String extra) {
        Intent webIntent = new Intent(context, activityTo);
        webIntent.putExtra(extra, extra);
        if (webIntent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(webIntent);
    }

    private static void checkIntentBeforeLaunching(Context context, Intent intent) {
        if (intent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(intent);
    }
}