package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theappguruz.imagezoom.ImageViewTouch;

public class DisplayImageActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageViewTouch ivLargeImage;
    private Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        SharedPreferences prefs = getSharedPreferences("com.tokkalo.nzta", Context.MODE_PRIVATE);

        String eventName = prefs.getString("eventName", "");

        ActionBar ab = getSupportActionBar();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b59206")));


        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);

        tv.setText(eventName);

        tv.setGravity(Gravity.CENTER);

        //tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String newImage = image.replace("thumb", "image");
        if (eventName.equalsIgnoreCase("Ugadi")) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Calender Inauguration")) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Sankranthi")) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Holi Funday")) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Vinayaka Chaviti")) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Blood Donations")) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Batukamma")) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Dasara & Diwali")) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Vanabojanalu")) {
            newImage = newImage.replace("png", "jpg");
        } else if ((eventName.equalsIgnoreCase("Independence Day")) || (eventName.equalsIgnoreCase("Republic Day"))) {
            newImage = newImage.replace("png", "jpg");
        } else if (eventName.equalsIgnoreCase("Xmas")) {
            newImage = newImage.replace("png", "jpg");
        }


        //Log.d("Image:", newImage);

        imageView = (ImageView) findViewById(R.id.imageView);

        Picasso.with(this)
                .load(newImage)
                .placeholder(R.drawable.progress_animation)   // optional
                        //.error(R.drawable.error)      // optional
                .resize(700, 450)                        // optional
                .into(imageView);

        ivLargeImage = (ImageViewTouch) findViewById(R.id.ivLargeImageView);
        ImageName imageId = new ImageName();

        //if image size is too large. scale image.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        myBitmap = BitmapFactory.decodeResource(getResources(),
                imageId.getImageId(), options);
        if (options.outWidth > 3000 || options.outHeight > 2000) {
            options.inSampleSize = 4;
        } else if (options.outWidth > 2000 || options.outHeight > 1500) {
            options.inSampleSize = 3;
        } else if (options.outWidth > 1000 || options.outHeight > 1000) {
            options.inSampleSize = 2;
        }
        options.inJustDecodeBounds = false;
        myBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.img_batukamma, options);
        /*myBitmap = BitmapFactory.decodeResource(getResources(),
                imageId.getImageId(),options);*/
        ivLargeImage.setImageBitmapReset(myBitmap, 0, true);


    }
}
