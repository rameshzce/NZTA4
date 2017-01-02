package com.tokkalo.nzta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by rameshkolamala on 23/03/16.
 */
public class CustomAdapter3 extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    public String url = "http://nzta.org/images/sponsors/";


    public CustomAdapter3(Activity context,
                          String[] web) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single2, null, true);


        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView1);
        Picasso.with(context)
                //.load("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png")
                .load(url + web[position])
                .placeholder(R.drawable.progress_animation)   // optional
                        //.error(R.drawable.error)      // optional
                        //.resize(900, 450)                        // optional
                .into(imageView);


        return rowView;
    }
}
