package com.tokkalo.nzta;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emoiluj.doubleviewpager.DoubleViewPager;
import com.emoiluj.doubleviewpager.DoubleViewPagerAdapter;


import java.util.ArrayList;

public class TeamActivity extends AppCompatActivity {
    private DoubleViewPager viewpager;
    private int horizontalChilds;
    private int verticalChilds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        ActionBar ab = getSupportActionBar();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9d1457")));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);

        tv.setText("Team NZTA");

        tv.setGravity(Gravity.CENTER);

        //tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        loadDataFromSplash();
        loadUI();
    }

    private void loadDataFromSplash() {
        //horizontalChilds = getIntent().getExtras().getInt("HORIZONTAL");
        //verticalChilds = getIntent().getExtras().getInt("VERTICAL");

        horizontalChilds = 1;
        verticalChilds = 14;
    }

    private void loadUI() {

        ArrayList<PagerAdapter> verticalAdapters = new ArrayList<PagerAdapter>();
        generateVerticalAdapters(verticalAdapters);

        viewpager = (DoubleViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(new DoubleViewPagerAdapter(getApplicationContext(), verticalAdapters));
    }

    private void generateVerticalAdapters(ArrayList<PagerAdapter> verticalAdapters) {
        for (int i = 0; i < horizontalChilds; i++) {
            verticalAdapters.add(new VerticalPagerAdapter(this, i, verticalChilds));
        }
    }
}
