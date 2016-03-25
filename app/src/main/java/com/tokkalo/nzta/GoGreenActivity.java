package com.tokkalo.nzta;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GoGreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_green);

        getSupportActionBar().hide();

        ActionBar ab = getSupportActionBar();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9d1457")));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);

        tv.setText("Go Green");

        tv.setGravity(Gravity.CENTER);

        tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b59206")));

        TextView gg = (TextView) findViewById(R.id.goGreen);
        gg.setTypeface(font);

        TextView nb = (TextView) findViewById(R.id.nzBlood);
        nb.setTypeface(font);

        gg.setBackgroundColor(Color.parseColor("#009668"));
        nb.setBackgroundColor(Color.parseColor("#ff0000"));



    }

    public void onBackPressed() {
        Intent intent = new Intent(GoGreenActivity.this, MemberActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        GoGreenActivity.this.startActivity(intent);
    }
}
