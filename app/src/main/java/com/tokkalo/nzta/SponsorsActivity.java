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
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SponsorsActivity extends AppCompatActivity {
    public String images[];

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        ActionBar ab = getSupportActionBar();


        String bgColor = "#9d1457";


        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor(bgColor)));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);


        //final String eventPosition = intent.getStringExtra("eventPosition");

        tv.setText("Sponsors");


        tv.setGravity(Gravity.CENTER);

        //tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        int imageSize = 30;


        images = new String[32];

        for (int i = 0; i < imageSize; i++) {
            if (i < 10) {
                images[i] = "0" + (i + 1) + ".png";
            } else {
                images[i] = (i) + ".png";
            }

        }

        images[30] = "ADS-32.jpg";
        images[31] = "aaphotos.jpeg";

        CustomAdapter3 adapter = new
                CustomAdapter3(SponsorsActivity.this, images);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
