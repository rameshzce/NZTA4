package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by rameshkolamala on 13/03/16.
 */
public class SpecialAdapter2 extends SimpleAdapter {
    private int[] colors = new int[]{0x30FF0000, 0x300000FF};
    private int[] padding = new int[]{20, 20};
    Context cntxt;
    String eventYear;

    private int[] listItemBackground = new int[]{R.drawable.list_background4, R.drawable.list_background4};
    private int[] galleryBackground = new int[]{R.drawable.gallery_bg10, R.drawable.gallery_bg10};
    private int[] eventImage = new int[]{R.drawable.icon_batukamma, R.drawable.icon_blooddonations};
    private int[] iconImages = new int[]{R.drawable.icon_calander_inagaration, R.drawable.icon_sankranthi, R.drawable.icon_independeneceday, R.drawable.icon_blooddonations, R.drawable.icon_holi, R.drawable.icon_ugadi, R.drawable.icon_independeneceday, R.drawable.icon_vinayakachaviti, R.drawable.icon_batukamma, R.drawable.icon_diwali, R.drawable.icon_vanabojanalu, R.drawable.icon_xmas};

    public SpecialAdapter2(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to, String year) {
        super(context, items, resource, from, to);
        cntxt = context;
        eventYear = year;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);


        LinearLayout ll = (LinearLayout) view.findViewById(R.id.layout1);

        RelativeLayout.LayoutParams lpimgHeader = new RelativeLayout.LayoutParams(ll.getLayoutParams());
        lpimgHeader.setMargins(0, 0, 0, 0);
        ll.setLayoutParams(lpimgHeader);


        final TextView tv = (TextView) view.findViewById(R.id.id);
        ImageView img = (ImageView) view.findViewById(R.id.image);


        TableRow tr2 = (TableRow) view.findViewById(R.id.tableRow2);


        int pos = position % listItemBackground.length;
        //ll.setBackgroundResource(listItemBackground[colorPos]);
        tr2.setBackgroundResource(galleryBackground[pos]);
        tv.setPadding(padding[pos], 0, 20, 0);
        img.setBackgroundResource(iconImages[position]);


        view.setMinimumHeight(90);
        final int pos2 = position;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(cntxt, "video gallery clicked: " + tv.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cntxt.getApplicationContext(), DisplayEvent.class);
                intent.putExtra("eventName", tv.getText().toString());
                intent.putExtra("eventPosition", pos2);


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cntxt.getApplicationContext().startActivity(intent);
            }
        });


        return view;
    }
}
