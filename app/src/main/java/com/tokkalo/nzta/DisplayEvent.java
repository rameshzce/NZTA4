package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.text.Html;

public class DisplayEvent extends AppCompatActivity {
    SharedPreferences prefs;
    Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);

        applicationContext = getApplicationContext();


        ActionBar ab = getSupportActionBar();


        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9d1457")));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);

        Intent intent = getIntent();
        final String eventName = intent.getStringExtra("eventName");
        //final String eventPosition = intent.getStringExtra("eventPosition");

        tv.setText(eventName);

        tv.setGravity(Gravity.CENTER);

        //tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        TableRow tr1 = (TableRow) findViewById(R.id.tableRow1);
        tr1.setMinimumHeight(50);

        TextView start_message = (TextView) findViewById(R.id.start_message);
        start_message.setText(Html.fromHtml("Thank you for downloading \"Demotivational Posters\" Click start below to start. To advance to the next picture click the \"Next\" button. If you see an image you would like to download click the \"Download\" button. If you enjoy this app please leave a rating."));

    }
}
