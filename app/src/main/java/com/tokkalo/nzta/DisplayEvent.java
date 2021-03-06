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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.text.Html;
import android.widget.Toast;

public class DisplayEvent extends AppCompatActivity {
    SharedPreferences prefs;
    Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);

        applicationContext = getApplicationContext();


        ActionBar ab = getSupportActionBar();

        Intent intent = getIntent();
        final String eventName = intent.getStringExtra("eventName");

        String bgColor = "#9d1457";

        if (eventName.equalsIgnoreCase("Helping Hands")) {
            bgColor = "#10d295";
        }

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor(bgColor)));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);


        //final String eventPosition = intent.getStringExtra("eventPosition");

        tv.setText(eventName);

        SharedPreferences prefs = getSharedPreferences("com.tokkalo.nzta", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("eventName", eventName);
        editor.commit();

        tv.setGravity(Gravity.CENTER);

        //tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        TableRow tr1 = (TableRow) findViewById(R.id.tableRow1);
        tr1.setMinimumHeight(50);

        ImageView img = (ImageView) findViewById(R.id.imageView1);

        String imageName = "img_ugadi";

        if (eventName.equalsIgnoreCase("Calender Inauguration")) {
            imageName = "img_calender_inauguration";
        } else if (eventName.equalsIgnoreCase("Sankranthi")) {
            imageName = "img_sankranti";
        } else if (eventName.equalsIgnoreCase("Republic Day")) {
            imageName = "img_republic_day";
        } else if (eventName.equalsIgnoreCase("Blood Donations")) {
            imageName = "img_blood_donations";
        } else if (eventName.equalsIgnoreCase("Holi Funday")) {
            imageName = "img_holi";
        } else if (eventName.equalsIgnoreCase("Ugadi")) {
            imageName = "img_ugadi";
        } else if (eventName.equalsIgnoreCase("Independence Day")) {
            imageName = "img_independenceday";
        } else if (eventName.equalsIgnoreCase("Vinayaka Chaviti")) {
            imageName = "img_vinayakachaturdi";
        } else if (eventName.equalsIgnoreCase("Batukamma")) {
            imageName = "img_batukamma";
        } else if (eventName.equalsIgnoreCase("Dasara & Diwali")) {
            imageName = "img_dasara_diwali";
        } else if (eventName.equalsIgnoreCase("Vanabojanalu")) {
            imageName = "img_vanabojanalu";
        } else if (eventName.equalsIgnoreCase("Xmas")) {
            imageName = "img_xmas";
        } else if (eventName.equalsIgnoreCase("Helping Hands")) {
            imageName = "img_helping_hands";
        }

        int id = getResources().getIdentifier("com.tokkalo.nzta:mipmap/" + imageName, null, null);
        img.setImageResource(id);

        Animation animation = null;

        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_in_top);
        img.startAnimation(animation);
        animation = null;

        TextView start_message = (TextView) findViewById(R.id.start_message);
        start_message.setText(Html.fromHtml("Namasthe ...<br> New Zealand Telugu Association specially invites you with family and friends to be part of auspicious<br><br> \"VINAYAKA CHATURTHI POOJA\"<br><br> for all the best days of our lives, For every miracle of waking up each morning, we want to express how grateful we are to the Lord.<br><br> Day:  Monday, 5th of September 2016.<br><br> Time: 6.30pm<br><br> Venue:  Three kings Fickling Convention enter <br><br> 546, Mt Albert Road,<br> Three Kings.<br> Followed by Bhajans and Maha Prasadam.<br><br> Main Sponsor :<br><br> Smt&Sri Sunitha-Vijay Kosna and family<br><br> Maha Prasadam by :<br> Satya South Indian Restaurant<br><br> Please join us and get the blessings of \"Lord Ganesh\". We are sure the day will inspire you for creativity and motivate you for auspicious beginnings.<br><br> See you all on Monday.<br><br> ... Executive Committee New Zealand <br> Telugu Association"));

    }

    public void showGallery(View view) {
        Intent i = new Intent(applicationContext, VideoGalleryActivity.class);
        startActivity(i);
        finish();
    }
}
