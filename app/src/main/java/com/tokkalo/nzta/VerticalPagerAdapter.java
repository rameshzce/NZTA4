package com.tokkalo.nzta;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rameshkolamala on 23/04/16.
 */
public class VerticalPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int mParent;
    private int mChilds;
    private JSONArray mColors;
    private int[] teamImages;

    public VerticalPagerAdapter(Context c, int parent, int childs) {
        mContext = c;
        mParent = parent;
        mChilds = childs;
        loadJSONFromAsset(c);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mChilds;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        teamImages = new int[]{R.drawable.team1, R.drawable.team2, R.drawable.team3};

        LinearLayout linear = new LinearLayout(mContext);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        linear.setLayoutParams(lp);

        /*TextView tvParent = new TextView(mContext);
        tvParent.setGravity(Gravity.CENTER_HORIZONTAL);
        tvParent.setText("Parent:" + mParent);
        tvParent.setTextColor(Color.BLACK);
        tvParent.setTextSize(70);
        linear.addView(tvParent);

        TextView tvChild = new TextView(mContext);
        tvChild.setGravity(Gravity.CENTER_HORIZONTAL);
        tvChild.setText("Child:" + position);
        tvChild.setTextColor(Color.BLACK);
        tvChild.setTextSize(70);
        linear.addView(tvChild);*/

        ImageView imgView = new ImageView(mContext);
        imgView.setImageResource(teamImages[position]);

        linear.addView(imgView);

        ImageView arrow = new ImageView(mContext);
        arrow.setImageResource(R.drawable.down_arrow);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
        params.setMargins(0, 500, 0, 10);
        arrow.setLayoutParams(params);

        linear.addView(arrow);


        setColors(position, linear);
        container.addView(linear);
        return linear;
    }

    public void setColors(int position, View layout) {

        try {
            String colorString = "#" + mColors.getJSONArray(mParent % 10).getString(position % 10);
            layout.setBackgroundColor(Color.parseColor(colorString));
        } catch (JSONException ex) {
            Log.e("XXX", "Fail to load color [" + mParent + "][" + position + "]");
        }

    }

    public void loadJSONFromAsset(Context ctx) {
        try {
            InputStream is = ctx.getAssets().open("colors.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String stringJson = new String(buffer, "UTF-8");
            mColors = new JSONArray(stringJson);
        } catch (IOException ex) {
            Log.e("XXX", "Fail to load color JSON file");
            ex.printStackTrace();
        } catch (JSONException ex) {
            Log.e("XXX", "Fail to parse colors JSON");
            ex.printStackTrace();
        }
    }
}
