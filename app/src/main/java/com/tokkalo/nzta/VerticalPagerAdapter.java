package com.tokkalo.nzta;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
    private String[] teamDesig;
    private String[] teamNames;
    private String[] teamMobiles;
    private String[] teamEmails;
    private String[] teamColors;

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
        teamDesig = new String[]{"President", "Executive \n Committe Member", "Joint Treasurer"};
        teamNames = new String[]{"Dharmendar Alle", "Vijay Kosana", "Sai Santhan Reddy Kusam"};
        teamMobiles = new String[]{"M. 0212663666", "M. 021739943", "M. 02102399325"};
        teamEmails = new String[]{"nztapresdent@gmail.com", "vijakosana@gmail.com", "saisanthan@gmail.com"};
        teamColors = new String[]{"#0671B5", "#AC36CA", "#C59107"};

        LinearLayout linear = new LinearLayout(mContext);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        linear.setLayoutParams(lp);

        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/handlee-regular.ttf");

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

        TextView desig = new TextView(mContext);
        desig.setGravity(Gravity.CENTER_HORIZONTAL);
        desig.setText(teamDesig[position]);
        desig.setTextColor(Color.WHITE);
        desig.setTextSize(25);
        desig.setTypeface(font);
        desig.setSingleLine(false);
        linear.addView(desig);

        ImageView imgView = new ImageView(mContext);
        imgView.setImageResource(teamImages[position]);

        linear.addView(imgView);

        TextView name = new TextView(mContext);
        name.setGravity(Gravity.CENTER_HORIZONTAL);
        name.setText(teamNames[position]);
        name.setTextColor(Color.WHITE);
        name.setTextSize(20);
        name.setTypeface(font);
        linear.addView(name);

        TextView mobile = new TextView(mContext);
        mobile.setGravity(Gravity.CENTER_HORIZONTAL);
        mobile.setText(teamMobiles[position]);
        mobile.setTextColor(Color.WHITE);
        mobile.setTextSize(20);
        mobile.setTypeface(font);
        linear.addView(mobile);

        TextView email = new TextView(mContext);
        email.setGravity(Gravity.CENTER_HORIZONTAL);
        email.setText(teamEmails[position]);
        email.setTextColor(Color.WHITE);
        email.setTextSize(20);
        email.setTypeface(font);
        linear.addView(email);

        ImageView arrow = new ImageView(mContext);
        arrow.setImageResource(R.drawable.down_arrow);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
        params.setMargins(0, 500, 0, 10);
        arrow.setLayoutParams(params);

        linear.addView(arrow);

        linear.setBackgroundColor(Color.parseColor(teamColors[position]));
        //setColors(position, linear);
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
