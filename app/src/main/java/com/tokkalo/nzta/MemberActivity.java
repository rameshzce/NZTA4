package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MemberActivity extends AppCompatActivity {
    Context applicationContext;
    int newHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        //getSupportActionBar().hide();
        applicationContext = getApplicationContext();

        ActionBar ab = getSupportActionBar();

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9d1457")));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);


        tv.setGravity(Gravity.CENTER);


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        TextView txt1 = (TextView) findViewById(R.id.textView1);
        //txt1.setTypeface(font);

        TextView txt1a = (TextView) findViewById(R.id.textView1a);
        //txt1a.setTypeface(font);

        TextView txt2 = (TextView) findViewById(R.id.textView2);
        //txt2.setTypeface(font);

        TextView txt2a = (TextView) findViewById(R.id.textView2a);
        //txt2a.setTypeface(font);

        TextView txt3 = (TextView) findViewById(R.id.textView3);
        //txt3.setTypeface(font);

        TextView txt3a = (TextView) findViewById(R.id.textView3a);
        //txt3a.setTypeface(font);

        TextView txt4 = (TextView) findViewById(R.id.textView4);
        //txt4.setTypeface(font);

        TextView txt4a = (TextView) findViewById(R.id.textView4a);
        //txt4a.setTypeface(font);

        TextView txt5 = (TextView) findViewById(R.id.textView5);
        //txt5.setTypeface(font);

        TextView txt5a = (TextView) findViewById(R.id.textView5a);
        //txt5a.setTypeface(font);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int h = (height / 4) - 50;

        TableRow tr1 = (TableRow) findViewById(R.id.tableRow1);
        tr1.setMinimumHeight(h);

        TableRow tr2 = (TableRow) findViewById(R.id.tableRow2);
        tr2.setMinimumHeight(h);

        TableRow tr3 = (TableRow) findViewById(R.id.tableRow3);
        tr3.setMinimumHeight(h);

        TableRow tr4 = (TableRow) findViewById(R.id.tableRow4);
        tr4.setMinimumHeight(h);

        TableRow tr5 = (TableRow) findViewById(R.id.tableRow5);
        tr5.setMinimumHeight(h);



    }

    public void rowClick1(View view) {
        //Intent i = new Intent(applicationContext, UpcomingEventsActivity.class);
        Intent i = new Intent(applicationContext, EventsActivity.class);
        startActivity(i);
        finish();
    }

    public void rowClick2(View view) {
        Intent i = new Intent(applicationContext, ExistingEventsActivity.class);
        startActivity(i);
        finish();
    }

    public void rowClick3(View view) {
        Intent i = new Intent(applicationContext, HelpingHandsActivity.class);
        startActivity(i);
        finish();
    }

    public void rowClick4(View view) {
        Intent i = new Intent(applicationContext, InviteFriendActivity.class);
        startActivity(i);
        finish();
    }

    public void rowClick5(View view) {
        Intent i = new Intent(applicationContext, MessagesActivity.class);
        startActivity(i);
        finish();
    }

    public void onBackPressed() {
        Intent intent = new Intent(MemberActivity.this, MemberActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        MemberActivity.this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent homeIntent = new Intent(MemberActivity.this, ProfileActivity.class);
                MemberActivity.this.startActivity(homeIntent);
                return true;
            case R.id.item2:
                Intent profile = new Intent(MemberActivity.this, SubscriptionActivity.class);
                MemberActivity.this.startActivity(profile);
                return true;
            case R.id.item3:
                Intent subscriptionIntent = new Intent(MemberActivity.this, TeamActivity.class);
                MemberActivity.this.startActivity(subscriptionIntent);
                return true;
            case R.id.item4:
                Intent teamIntent = new Intent(MemberActivity.this, TeamActivity.class);
                MemberActivity.this.startActivity(teamIntent);
                return true;
            case R.id.item5:
                Intent sponsors = new Intent(MemberActivity.this, TeamActivity.class);
                MemberActivity.this.startActivity(sponsors);
                return true;
            case R.id.item6:
                Intent message = new Intent(MemberActivity.this, TeamActivity.class);
                MemberActivity.this.startActivity(message);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
