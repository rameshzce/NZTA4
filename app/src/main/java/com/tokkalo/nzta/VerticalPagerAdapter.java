package com.tokkalo.nzta;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    private int[] teamBgs;
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
        teamImages = new int[]{R.drawable.president, R.drawable.ramana_salvaji, R.drawable.arun, R.drawable.rajeswri, R.drawable.raj, R.drawable.narsing, R.drawable.srinivas, R.drawable.santhan_redddy, R.drawable.aravind_reddy, R.drawable.aruna, R.drawable.vijaykosna, R.drawable.sruti, R.drawable.rakesh_gondlyala, R.drawable.subrahamanyam};
        teamDesig = new String[]{"President", "General Secretary", "Vice President", "Vice President", "Treasurer", "Joint Secretary", "Joint Secretary", "Joint Treasurer", "Executive Committee Member", "Executive Committee Member", "Executive Committee Member", "Executive Committee Member", "Executive Committee Member", "Executive Committee Member"};
        teamNames = new String[]{"Mr. Dharmendar Alle", "Mr. Venkata Ramana Rao salvaji", "Mr.Arun Rao Paidigummala", "Mrs.Rajeshwari Gangishetty", "Mr.Raja Shekar Reddy Patlolla", "Mr. Narsing Rao Enaganti", "Mr. Srinivas Yepuri", "Mr.Sai Santhan Reddy Kusam", "Mr.Aravind Reddy Bathula", "Mrs.Aruna Salvaji", "Mr. Vijay Kosana", "Mrs.Dharani Shruthi Akoju", "Mr.Rakesh Gondlyala", "Mr.D.V.S.R.Subrahmanyam"};
        teamMobiles = new String[]{"0212663666", "0220491003", "0212511454", "02102604222", "02102468855", "xxx", "xxx", "02102399325", "0220372376", "0211262640", "021739943", "02102923569", "0211558543", "0211899494"};
        teamEmails = new String[]{"nztapresident@gmail.com", "generalsecretarynzta@gmail.com", "arunrao.p@gmail.com", "rajjugangishetty@gmail.com", "rrpatllola@gmail.com", "xxx", "xxx", "xxx", "aravind.bathula@gmail.com", "asalvaji@yahoo.com", "vijaykosana@gmail.com", "sruthiakoju@hotmail.com", "rakesh.gon@hotmail.com", "subbu.desu@gmail.com"};
        teamColors = new String[]{"#AC36CA", "#AC36CA", "#C59107", "#C59107", "#C59107", "#C59107", "#C59107", "#C59107", "#0671B5", "#0671B5", "#0671B5", "#0671B5", "#0671B5", "#0671B5"};
        teamBgs = new int[]{R.drawable.team_bg1, R.drawable.team_bg1, R.drawable.team_bg2, R.drawable.team_bg2, R.drawable.team_bg2, R.drawable.team_bg2, R.drawable.team_bg2, R.drawable.team_bg2, R.drawable.team_bg3, R.drawable.team_bg3, R.drawable.team_bg3, R.drawable.team_bg3, R.drawable.team_bg3, R.drawable.team_bg3};

        LinearLayout linear = new LinearLayout(mContext);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        linear.setLayoutParams(lp);

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;


        if (height < 1000) {

        }


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
        desig.setTextColor(Color.parseColor(teamColors[position]));
        desig.setTextSize(22);
        //desig.setTypeface(font);
        desig.setSingleLine(false);

        if (height < 1400) {
            desig.setPadding(0, 40, 0, 0);
        }

        linear.addView(desig);

        ImageView imgView = new ImageView(mContext);
        imgView.setImageResource(teamImages[position]);
        imgView.setPadding(0, 50, 0, 80);
        linear.addView(imgView);

        TextView name = new TextView(mContext);
        name.setGravity(Gravity.CENTER_HORIZONTAL);
        name.setText(teamNames[position]);
        name.setTextColor(Color.WHITE);
        name.setTextSize(18);
        //name.setTypeface(font);
        linear.addView(name);

        TextView mobile = new TextView(mContext);
        mobile.setGravity(Gravity.CENTER_HORIZONTAL);
        mobile.setText("M. " + teamMobiles[position]);
        mobile.setTextColor(Color.WHITE);
        mobile.setTextSize(18);
        //mobile.setTypeface(font);
        linear.addView(mobile);

        TextView email = new TextView(mContext);
        email.setGravity(Gravity.CENTER_HORIZONTAL);
        email.setText(teamEmails[position]);
        email.setTextColor(Color.WHITE);
        email.setTextSize(18);
        //email.setTypeface(font);
        linear.addView(email);

        ImageView arrow = new ImageView(mContext);
        arrow.setImageResource(R.drawable.down_arrow);
        arrow.setPadding(0, 250, 0, 0);
        if (height < 1400) {
            arrow.setPadding(0, 100, 0, 0);
        }
        if (position == 13) {
            arrow.setVisibility(View.INVISIBLE);
        }
        linear.addView(arrow);




        /*View bg = new View(mContext);
        bg.setMinimumHeight(100);
        bg.setBackgroundColor(Color.WHITE);
        linear.addView(bg);*/

        linear.setBackgroundColor(Color.parseColor(teamColors[position]));

        linear.setBackgroundResource(teamBgs[position]);
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
