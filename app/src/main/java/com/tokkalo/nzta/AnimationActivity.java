package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {
    TextView text;
    String animationText[];
    private static int TIME_OUT = 2000;
    Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        getSupportActionBar().hide();
        applicationContext = getApplicationContext();


        animationText = new String[7];
        animationText[0] = "hi";
        animationText[1] = "welcome\n to\n NZTA";
        animationText[2] = "we are\n" +
                "500+\n" +
                "families";
        animationText[3] = "we are\n" +
                "200+\n" +
                "students";
        animationText[4] = "20+\n" +
                "events\n" +
                "a year";
        animationText[5] = "celebrating\n" +
                "culturally";
        animationText[6] = "come & join\n" +
                "let's\n" +
                "celebrate\n" +
                "all";

        Animation animation = null;

        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.push_left_in);

        text = (TextView) findViewById(R.id.textView1);

        text.setText(animationText[0]);
        text.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Animation animation = null;

                animation = AnimationUtils.loadAnimation(applicationContext, R.anim.push_left_in);
                text.setText(animationText[1]);
                text.startAnimation(animation);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Animation animation = null;

                        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.push_left_in);
                        text.setText(animationText[2]);
                        text.startAnimation(animation);

                        new Handler().postDelayed(new Runnable() {


                            @Override
                            public void run() {
                                Animation animation = null;

                                animation = AnimationUtils.loadAnimation(applicationContext, R.anim.push_left_in);
                                text.setText(animationText[3]);
                                text.startAnimation(animation);
                                new Handler().postDelayed(new Runnable() {


                                    @Override
                                    public void run() {
                                        Animation animation = null;

                                        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.push_left_in);
                                        text.setText(animationText[4]);
                                        text.startAnimation(animation);
                                        new Handler().postDelayed(new Runnable() {

                                            @Override
                                            public void run() {
                                                Animation animation = null;

                                                animation = AnimationUtils.loadAnimation(applicationContext, R.anim.push_left_in);
                                                text.setText(animationText[5]);
                                                text.startAnimation(animation);
                                                new Handler().postDelayed(new Runnable() {


                                                    @Override
                                                    public void run() {
                                                        Animation animation = null;

                                                        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.push_left_in);
                                                        text.setText(animationText[6]);
                                                        text.startAnimation(animation);
                                                        new Handler().postDelayed(new Runnable() {


                                                            @Override
                                                            public void run() {
                                                                // This method will be executed once the timer is over
                                                                // Start your app main activity
                                                                Intent i = new Intent(AnimationActivity.this, MainActivity.class);
                                                                startActivity(i);

                                                                // close this activity
                                                                finish();
                                                            }
                                                        }, 3000);
                                                    }
                                                }, TIME_OUT);
                                            }
                                        }, TIME_OUT);
                                    }
                                }, TIME_OUT);
                            }
                        }, TIME_OUT);
                    }
                }, TIME_OUT);
            }
        }, TIME_OUT);
    }
}
