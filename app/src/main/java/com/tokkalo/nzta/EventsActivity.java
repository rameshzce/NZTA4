package com.tokkalo.nzta;

import android.content.Context;
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
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EventsActivity extends AppCompatActivity {
    Context applicationContext;

    String myJSON;
    TextView textView;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD = "address";
    SharedPreferences prefs;


    String jsonData = "{\"status\":\"SUCCESS\",\"message\":\"9 doner(s) found for your search.\",\"remarks\":\"\",\"doners\":{\"result\":[{\"id\":\"Calender Inauguration\",\"name\":1,\"address\":\"Rangoli competition & kite festival on 17-1-2016 \"},{\"id\":\"Sankranthi\",\"name\":2,\"address\":\"Event at epsom on 1-3-2016 Saturday at 6.00pm, all are welcome and followed by dinner \"},{\"id\":\"Republic Day\",\"name\":3,\"address\":\"War memorial hall, Mount eden on 1-6-2016 Friday 6.00pm, all are welcome and followed by dinner.\"},{\"id\":\"Blood Donations\",\"name\":4,\"address\":\"Diwali stall opens at 2 Pm at queens street. reworks and programs starts at 7 Pm.\\r\\nThe next day we have diwali celabrations\\r\\nat avondale. children participating Dances and some programs. please participate and enjoy the celebrations at 7.00 PM. Followed \"},{\"id\":\"Holi Funday\",\"name\":5,\"address\":\"War memorial hall, Mount eden,\\r\\non 01-06-16 friday, 6.00 pm, all are welcome and followed by dinner.\"},{\"id\":\"Ugadi\",\"name\":6,\"address\":\"Rangoli competation & kite festival on 17-01-16 sunday.\"},{\"id\":\"Independence Day\",\"name\":7,\"address\":\"Eevent at epsom on 01-03-16 Saturday at 6.00 pm, all are welcome\\r\\nand followed by dinner.\"},{\"id\":\"Vinayaka Chaviti\",\"name\":8,\"address\":\"War memorial hall, Mount eden,\\r\\non 01-06-16 friday, 6.00 pm, all are welcome and followed by dinner.\"},{\"id\":\"Batukamma\",\"name\":9,\"address\":\"Diwali stall  \"},{\"id\":\"Dasara & Diwali\",\"name\":10,\"address\":\"Diwali stall  \"},{\"id\":\"Vanabojanalu\",\"name\":11,\"address\":\"Diwali stall  \"},{\"id\":\"Xmas\",\"name\":12,\"address\":\"Diwali stall  \"}]}}";


    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        applicationContext = getApplicationContext();

        //init();

        ActionBar ab = getSupportActionBar();


        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9d1457")));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);

        Intent intent = getIntent();
        final String year = intent.getStringExtra("year");

        if (year == null) {
            tv.setText("Events");
        } else {
            tv.setText("Events " + year);
        }


        tv.setGravity(Gravity.CENTER);

        //tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();


        showList(year);
    }

    protected void showList(String year) {
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

            SpecialAdapter2 adapter = new SpecialAdapter2(
                    EventsActivity.this, personList, R.layout.list_item2,
                    new String[]{TAG_ID, TAG_ADD},
                    new int[]{R.id.id, R.id.address}, year
            );

            list.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /*public void init(){
        TableLayout ll = (TableLayout) findViewById(R.id.table_main);

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Sl.No ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Product ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Unit Price ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Stock Remaining ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        for (int i = 0; i < 25; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + i);
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("Product " + i);
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("Rs." + i);
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("" + i * 15 / 32 * 10);
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }

    }*/


}
