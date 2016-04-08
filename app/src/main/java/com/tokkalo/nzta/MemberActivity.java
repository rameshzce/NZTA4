package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MemberActivity extends AppCompatActivity {
    Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        getSupportActionBar().hide();
        applicationContext = getApplicationContext();


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        TextView txt1 = (TextView) findViewById(R.id.textView1);
        txt1.setTypeface(font);

        TextView txt1a = (TextView) findViewById(R.id.textView1a);
        txt1a.setTypeface(font);

        TextView txt2 = (TextView) findViewById(R.id.textView2);
        txt2.setTypeface(font);

        TextView txt2a = (TextView) findViewById(R.id.textView2a);
        txt2a.setTypeface(font);

        TextView txt3 = (TextView) findViewById(R.id.textView3);
        txt3.setTypeface(font);

        TextView txt3a = (TextView) findViewById(R.id.textView3a);
        txt3a.setTypeface(font);

        TextView txt4 = (TextView) findViewById(R.id.textView4);
        txt4.setTypeface(font);

        TextView txt4a = (TextView) findViewById(R.id.textView4a);
        txt4a.setTypeface(font);


        TextView txt5 = (TextView) findViewById(R.id.textView5);
        txt5.setTypeface(font);
        TextView txt5a = (TextView) findViewById(R.id.textView5a);
        txt5a.setTypeface(font);



    }

    public void rowClick1(View view) {
        Intent i = new Intent(applicationContext, UpcomingEventsActivity.class);
        startActivity(i);
        finish();
    }

    public void rowClick2(View view) {
        Intent i = new Intent(applicationContext, ExistingEventsActivity.class);
        startActivity(i);
        finish();
    }

    public void rowClick3(View view) {
        Intent i = new Intent(applicationContext, GoGreenActivity.class);
        //Intent i = new Intent(applicationContext, SubscriptionActivity.class);
        startActivity(i);
        finish();
    }

    public void rowClick4(View view) {
        Intent i = new Intent(applicationContext, InviteFriendActivity.class);
        startActivity(i);
        finish();
    }

    public void rowClick5(View view) {
        Intent i = new Intent(applicationContext, InviteFriendActivity.class);
        startActivity(i);
        finish();
    }

    public void onBackPressed() {
        Intent intent = new Intent(MemberActivity.this, MemberActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        MemberActivity.this.startActivity(intent);
    }
}
