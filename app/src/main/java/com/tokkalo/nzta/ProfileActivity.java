package com.tokkalo.nzta;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private Button btn;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        btn = (Button) findViewById(R.id.button);
        btn.setTypeface(font);

        txt1 = (TextView) findViewById(R.id.textView1);
        txt1.setTypeface(font);

        txt2 = (TextView) findViewById(R.id.textView2);
        txt2.setTypeface(font);

        txt3 = (TextView) findViewById(R.id.textView3);
        txt3.setTypeface(font);
    }


}
