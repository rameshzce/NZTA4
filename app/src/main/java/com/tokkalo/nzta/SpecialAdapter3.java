package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by rameshkolamala on 13/03/16.
 */
public class SpecialAdapter3 extends SimpleAdapter {
    private int[] padding = new int[]{10, 10};
    Context cntxt;

    private int[] listItemBackground = new int[]{R.drawable.list_background4, R.drawable.list_background4};

    public SpecialAdapter3(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to) {
        super(context, items, resource, from, to);
        cntxt = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);


        LinearLayout ll = (LinearLayout) view.findViewById(R.id.layout1);

        RelativeLayout.LayoutParams lpimgHeader = new RelativeLayout.LayoutParams(ll.getLayoutParams());
        lpimgHeader.setMargins(0, 0, 0, 0);
        ll.setLayoutParams(lpimgHeader);


        final TextView tv = (TextView) view.findViewById(R.id.date);

        final TextView tv2 = (TextView) view.findViewById(R.id.id);


        TableLayout tb = (TableLayout) view.findViewById(R.id.tableLayout2);


        int pos = position % listItemBackground.length;

        tb.setBackgroundResource(R.drawable.gallery_bg12);


        tv.setPadding(padding[pos], 0, 10, 0);
        tv2.setPadding(padding[pos], 0, 10, 0);


        //view.setMinimumHeight(90);


        return view;
    }
}
