package com.tokkalo.nzta;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    GridView gv;
    Context context;
    ArrayList prgmName;
    String galleryType;
    int newWidth;
    String year;

    private ImageView imageView;
    private ImageLoader imgLoader;
    private String strUrl = "https://upload.wikimedia.org/wikipedia/commons/4/4b/Everest_kalapatthar_crop.jpg";

    public static String[] prgmNameList = {
            "Let Us C", "c++", "JAVA",
            "Jsp", "Microsoft .Net", "Android",
            "PHP", "Jquery", "JavaScript",
            "Let Us C", "c++", "JAVA",
            "Jsp", "Microsoft .Net", "Android",
            "PHP", "Jquery", "JavaScript",
            "Let Us C", "c++", "JAVA",
            "Jsp", "Microsoft .Net", "Android",
            "PHP", "Jquery", "JavaScript",
            "Let Us C", "c++", "JAVA",
            "Jsp", "Microsoft .Net", "Android",
            "PHP", "Jquery", "JavaScript"
    };
    public static int[] prgmImages = {
            R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
            R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,
            R.drawable.image_7, R.drawable.image_8, R.drawable.image_9,
            R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
            R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,
            R.drawable.image_7, R.drawable.image_8, R.drawable.image_9,
            R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
            R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,
            R.drawable.image_7, R.drawable.image_8, R.drawable.image_9,
            R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
            R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,
            R.drawable.image_7, R.drawable.image_8, R.drawable.image_9
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent intent = getIntent();
        final String galleryType = intent.getStringExtra("galleryType");
        year = intent.getStringExtra("year");

        ActionBar ab = getSupportActionBar();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b59206")));



        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);

        tv.setText(galleryType + " " + year);

        tv.setGravity(Gravity.CENTER);

        tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        String rotation = getRotation(getApplicationContext());

        //String msg = "Width: " + width + ", height: " + height + ", rotation: " + rotation;

        //Toast.makeText(GalleryActivity.this, msg, Toast.LENGTH_LONG).show();


        imageView = (ImageView) findViewById(R.id.imageView2);
        //imgLoader = new ImageLoader(this);
        Picasso.with(this)
                .load("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png")
                        //.placeholder(R.drawable.placeholder)   // optional
                        //.error(R.drawable.error)      // optional
                .resize(400, 400)                        // optional
                .into(imageView);

    }

    public String getRotation(Context context) {
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (rotation) {
            case Surface.ROTATION_0:
                //return "portrait";
                return "P";
            case Surface.ROTATION_90:
                //return "landscape";
                return "L";
            case Surface.ROTATION_180:
                //return "reverse portrait";
                return "P";
            default:
                //return "reverse landscape";
                return "L";
        }
    }


}
