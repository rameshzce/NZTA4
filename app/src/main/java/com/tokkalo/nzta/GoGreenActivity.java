package com.tokkalo.nzta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GoGreenActivity extends AppCompatActivity {
    String myJSON;
    TextView textView;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD = "address";
    SharedPreferences prefs;

    String jsonData = "{\"status\":\"SUCCESS\",\"message\":\"9 doner(s) found for your search.\",\"remarks\":\"\",\"doners\":{\"result\":[{\"id\":\"Plantation @ Western springs\",\"name\":1,\"address\":\"Everything you need for better living. NZTA planting for go green. \"}]}}";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_green);

        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();

        getSupportActionBar().hide();

        ActionBar ab = getSupportActionBar();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9d1457")));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);

        tv.setText("Go Green");

        tv.setGravity(Gravity.CENTER);

        tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b59206")));

        TextView gg = (TextView) findViewById(R.id.goGreen);
        gg.setTypeface(font);

        TextView nb = (TextView) findViewById(R.id.nzBlood);
        nb.setTypeface(font);

        gg.setBackgroundColor(Color.parseColor("#009668"));
        nb.setBackgroundColor(Color.parseColor("#ff0000"));

        showList();

    }

    protected void showList() {
        try {
            //JSONObject jsonObj = new JSONObject(myJSON);
            JSONObject jsonObj1 = new JSONObject(jsonData);
            String events = jsonObj1.getString("doners");

            JSONObject jsonObj = new JSONObject(events);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String address = c.getString(TAG_ADD);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_ID, id);
                persons.put(TAG_ADD, address);

                personList.add(persons);
            }

            //Toast.makeText(UpcomingEventsActivity.this, width + " " + rotation, Toast.LENGTH_SHORT).show();


            SpecialAdapter adapter = new SpecialAdapter(
                    GoGreenActivity.this, personList, R.layout.list_item,
                    new String[]{TAG_ID, TAG_ADD},
                    new int[]{R.id.id, R.id.address}, "GoGreen"
            );

            list.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onBackPressed() {
        Intent intent = new Intent(GoGreenActivity.this, MemberActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        GoGreenActivity.this.startActivity(intent);
    }
}
