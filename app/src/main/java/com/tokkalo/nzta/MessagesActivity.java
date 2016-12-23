package com.tokkalo.nzta;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MessagesActivity extends AppCompatActivity {
    Context applicationContext;
    private static final String TAG_RESULTS = "result";

    private static final String TAG_ADD = "address";
    SharedPreferences prefs;

    String year;

    String jsonData = "{\"status\":\"SUCCESS\",\"message\":\"9 doner(s) found for your search.\",\"remarks\":\"\",\"doners\":{\"result\":[{\"id\":\"War memorial hall, Mount eden, on 01-06-16 friday, 6.00 pm, all are welcome and followed by dinner. War memorial hall, Mount eden, on 01-06-16 friday, 6.00 pm, all are welcome and followed by dinner. War memorial hall, Mount eden, on 01-06-16 friday, 6.00 pm, all are welcome and followed by dinner. War memorial hall, Mount eden, on 01-06-16 friday, 6.00 pm, all are welcome and followed by dinner.\",\"name\":1,\"address\":\"Rangoli competition & kite festival on 17-1-2016 \"},{\"id\":\"Sankranthi\",\"name\":2,\"address\":\"Event at epsom on 1-3-2016 Saturday at 6.00pm, all are welcome and followed by dinner \"},{\"id\":\"Republic Day\",\"name\":3,\"address\":\"War memorial hall, Mount eden on 1-6-2016 Friday 6.00pm, all are welcome and followed by dinner.\"},{\"id\":\"Blood Donations\",\"name\":4,\"address\":\"Diwali stall opens at 2 Pm at queens street. reworks and programs starts at 7 Pm.\\r\\nThe next day we have diwali celabrations\\r\\nat avondale. children participating Dances and some programs. please participate and enjoy the celebrations at 7.00 PM. Followed \"},{\"id\":\"Holi Funday\",\"name\":5,\"address\":\"War memorial hall, Mount eden,\\r\\non 01-06-16 friday, 6.00 pm, all are welcome and followed by dinner.\"},{\"id\":\"Ugadi\",\"name\":6,\"address\":\"Rangoli competation & kite festival on 17-01-16 sunday.\"},{\"id\":\"Independence Day\",\"name\":7,\"address\":\"Eevent at epsom on 01-03-16 Saturday at 6.00 pm, all are welcome\\r\\nand followed by dinner.\"},{\"id\":\"Vinayaka Chaviti\",\"name\":8,\"address\":\"War memorial hall, Mount eden,\\r\\non 01-06-16 friday, 6.00 pm, all are welcome and followed by dinner.\"},{\"id\":\"Batukamma\",\"name\":9,\"address\":\"Diwali stall  \"},{\"id\":\"Dasara & Diwali\",\"name\":10,\"address\":\"Diwali stall  \"},{\"id\":\"Vanabojanalu\",\"name\":11,\"address\":\"Diwali stall  \"},{\"id\":\"Xmas\",\"name\":12,\"address\":\"Diwali stall  \"}]}}";
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    //private static String url = "https://raw.githubusercontent.com/mobilesiri/JSON-Parsing-in-Android/master/index.html";
    private static String url = "http://sdctbheemili.org/ios/messages.php?type=messages";

    // JSON Node names
    private static final String TAG_STUDENTINFO = "messages";
    private static final String TAG_ID = "time";
    private static final String TAG_NAME = "message";
    private static final String TAG_EMAIL = "time2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        applicationContext = getApplicationContext();

        ActionBar ab = getSupportActionBar();


        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fc5f22")));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);


        tv.setText("Messages");


        tv.setGravity(Gravity.CENTER);

        //tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();


        // showList();

        new GetStudents().execute();
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

            SpecialAdapter3 adapter = new SpecialAdapter3(
                    MessagesActivity.this,
                    personList,
                    R.layout.list_item3,
                    new String[]{TAG_ID, TAG_ADD},
                    new int[]{R.id.id, R.id.date}
            );

            list.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetStudents extends AsyncTask<Void, Void, Void> {

        // Hashmap for ListView
        ArrayList<HashMap<String, String>> studentList;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MessagesActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            WebRequest webreq = new WebRequest();

            // Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(url, WebRequest.GET);

            Log.d("Response: ", "> " + jsonStr);

            studentList = ParseJSON(jsonStr);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
          /*  ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, studentList,
                    R.layout.list_item, new String[]{TAG_NAME, TAG_EMAIL,
                    TAG_PHONE_MOBILE}, new int[]{R.id.name,
                    R.id.email, R.id.mobile}); */


            SpecialAdapter3 adapter = new SpecialAdapter3(
                    MessagesActivity.this,
                    studentList,
                    R.layout.list_item3,
                    new String[]{TAG_NAME, TAG_EMAIL},
                    new int[]{R.id.id, R.id.date}
            );

            list.setAdapter(adapter);
        }

    }

    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray students = jsonObj.getJSONArray(TAG_STUDENTINFO);

                // looping through All Students
                for (int i = 0; i < students.length(); i++) {
                    JSONObject c = students.getJSONObject(i);

                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String email = c.getString(TAG_EMAIL);

                    // Phone node is JSON Object

                    // tmp hashmap for single student
                    HashMap<String, String> student = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    student.put(TAG_ID, id);
                    student.put(TAG_NAME, name);
                    student.put(TAG_EMAIL, email);

                    // adding student to students list
                    studentList.add(student);
                }
                return studentList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
            return null;
        }
    }
}
