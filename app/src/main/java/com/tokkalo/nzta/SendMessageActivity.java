package com.tokkalo.nzta;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SendMessageActivity extends AppCompatActivity {
    Context applicationContext;
    private EditText editTextMsg;
    private String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        //getSupportActionBar().hide();
        applicationContext = getApplicationContext();
        ActionBar ab = getSupportActionBar();


        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9d1457")));

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, // Width of TextView
                AbsListView.LayoutParams.WRAP_CONTENT); // Height of TextView

        tv.setLayoutParams(lp);


        tv.setText("Send a message");


        tv.setGravity(Gravity.CENTER);

        //tv.setTypeface(font);

        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ab.setCustomView(tv);

        editTextMsg = (EditText) findViewById(R.id.editTextMsg);
        editTextMsg.setHintTextColor(Color.parseColor("#ffffff"));
    }

    public void sendMessage1(View view) {

        alertMessage();
    }

    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        //Toast.makeText(RequestActivity.this, "Yes Clicked", Toast.LENGTH_LONG).show();


                        //sendMessage(View view);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        //Toast.makeText(RequestActivity.this, "No Clicked", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void sendMessage(View view) {

        msg = editTextMsg.getText().toString();

        if (msg.isEmpty()) {
            showToast("Please enter a message");
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(SendMessageActivity.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Sending message...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
                @Override
                protected String doInBackground(String... params) {

                    String result = null;
                    InputStream inputStream = null;

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();


                    try {
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(
                                "http://sdctbheemili.org/ios/android_message.php?msg=" + msg);
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                        HttpResponse response = httpClient.execute(httpPost);

                        HttpEntity entity = response.getEntity();

                        inputStream = entity.getContent();
                        // json is UTF-8 by default
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                        StringBuilder sb = new StringBuilder();

                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();

                    } catch (ClientProtocolException e) {

                    } catch (IOException e) {

                    } finally {
                        try {
                            if (inputStream != null) inputStream.close();
                        } catch (Exception squish) {
                        }
                    }

                    return result;
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                    progressDialog.setProgress(0);
                    progressDialog.show();
                }

                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);
                    progressDialog.hide();

                    String jsonStr = result;
                    if (jsonStr != null) {
                        try {
                            JSONObject jsonObj = new JSONObject(jsonStr);
                            String status = jsonObj.getString("status");
                            String message = jsonObj.getString("message");

                            if (status.equals("SUCCESS")) {

                                TextView textViewToChange = (TextView) findViewById(R.id.textMessage);
                                textViewToChange.setText("Message has been sent.");

                                View b = findViewById(R.id.sendMessage);
                                b.setVisibility(View.INVISIBLE);


                            } else if (status.equals("FAILURE")) {
                                Toast.makeText(SendMessageActivity.this, message, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SendMessageActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SendMessageActivity.this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SendMessageActivity.this, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
                    }
                }


            }
            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
            sendPostReqAsyncTask.execute();
        }

    }

    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();
    }
}
