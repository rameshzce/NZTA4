package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by rameshkolamala on 13/03/16.
 */
public final class SpecialAdapter extends SimpleAdapter {
    private int[] colors = new int[]{0x30FF0000, 0x300000FF};
    private int[] padding = new int[]{20, 20};
    String fromActivity;
    Context cntxt;

    private int[] listItemBackground = new int[]{R.drawable.list_background1, R.drawable.list_background2};
    private int[] galleryBackground = new int[]{R.drawable.gallery_bg4, R.drawable.gallery_bg4};
    private int[] videoBackground = new int[]{R.drawable.gallery_bg5, R.drawable.gallery_bg5};

    public SpecialAdapter(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to, String activity) {
        super(context, items, resource, from, to);
        fromActivity = activity;
        cntxt = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        //int colorPos = position % colors.length;
        //view.setBackgroundColor(colors[colorPos]);

        //RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        //params.width = 1200;

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.layout1);

        RelativeLayout.LayoutParams lpimgHeader = new RelativeLayout.LayoutParams(ll.getLayoutParams());
        lpimgHeader.setMargins(40, 0, 40, 0);
        ll.setLayoutParams(lpimgHeader);


        final TextView tv = (TextView) view.findViewById(R.id.id);
        Typeface font = Typeface.createFromAsset(tv.getContext().getAssets(), "fonts/handlee-regular.ttf");
        tv.setTypeface(font);

        final TextView tv2 = (TextView) view.findViewById(R.id.address);

        tv2.setTypeface(font);

        TextView tv3 = (TextView) view.findViewById(R.id.photoGallery);
        String tvs3 = tv3.getText().toString();

        tv3.setTypeface(font);

        TextView tv4 = (TextView) view.findViewById(R.id.videoGallery);
        String tvs4 = tv4.getText().toString();
        tv4.setTypeface(font);

        TableRow tr2 = (TableRow) view.findViewById(R.id.tableRow2);

        TableRow tr3 = (TableRow) view.findViewById(R.id.tableRow3);

        //SpannableString content = new SpannableString(tvs3);
        //content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        //SpannableString content2 = new SpannableString(tvs4);
        //content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);


        int colorPos = position % listItemBackground.length;
        ll.setBackgroundResource(listItemBackground[colorPos]);
        tr2.setBackgroundResource(galleryBackground[colorPos]);
        tr3.setBackgroundResource(videoBackground[colorPos]);
        tv.setPadding(padding[colorPos], 0, 20, 0);
        tv2.setPadding(padding[colorPos], 0, 20, 0);

        ImageView im1 = (ImageView) view.findViewById(R.id.imageGallery);
        ImageView im2 = (ImageView) view.findViewById(R.id.imageVideo);

        if (colorPos == 0) {
            //tv3.setText(content);
            //tv4.setText(content2);

            //im1.setVisibility(View.GONE);
            //im2.setVisibility(View.GONE);

            //lpimgHeader.setMargins(0, 0, 40, 0);
            //ll.setLayoutParams(lpimgHeader);
        }


        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cntxt.getApplicationContext(), GalleryActivity.class);
                intent.putExtra("galleryType", tv.getText().toString());
                intent.putExtra("year", "2016");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cntxt.getApplicationContext().startActivity(intent);
                //Toast.makeText(cntxt, "photo gallery clicked: " + tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cntxt, "video gallery clicked: " + tv.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cntxt.getApplicationContext(), VideoGalleryActivity.class);
                intent.putExtra("galleryType", tv.getText().toString());
                if (fromActivity.equalsIgnoreCase("GoGreen") || fromActivity.equalsIgnoreCase("NZBlood")) {
                    intent.putExtra("year", " ");
                }

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cntxt.getApplicationContext().startActivity(intent);
            }
        });

        Animation animation = null;

        animation = AnimationUtils.loadAnimation(cntxt, R.anim.slide_down2);

        //view.setLayoutParams(params);
        //view.setRight(100);
        if (fromActivity.equalsIgnoreCase("GoGreen")) {
            ll.setBackgroundResource(R.drawable.list_background5);
            tr2.setBackgroundResource(R.drawable.gallery_bg6);
            tr3.setBackgroundResource(R.drawable.gallery_bg7);
            tv2.setTextColor(Color.parseColor("#9cffa5"));

        }

        if (fromActivity.equalsIgnoreCase("NZBlood")) {
            ll.setBackgroundResource(R.drawable.list_background6);
            tr2.setBackgroundResource(R.drawable.gallery_bg8);
            tr3.setBackgroundResource(R.drawable.gallery_bg9);
            tv2.setTextColor(Color.parseColor("#ffc8c8"));

        }

        view.startAnimation(animation);
        animation = null;

        return view;
    }
}
