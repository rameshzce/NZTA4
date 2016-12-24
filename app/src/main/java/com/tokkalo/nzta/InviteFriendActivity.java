package com.tokkalo.nzta;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class InviteFriendActivity extends AppCompatActivity {
    private EditText editTextMobile;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private String fromMobile;
    private String toMobile;
    private String toMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);

        getSupportActionBar().hide();



        SharedPreferences prefs = getSharedPreferences("com.tokkalo.nzta", Context.MODE_PRIVATE);

        fromMobile = prefs.getString("mobileNumber", "");

        btn1 = (Button) findViewById(R.id.button);
        //btn1.setTypeface(font);

        Button btn2 = (Button) findViewById(R.id.yes);
        //btn2.setTypeface(font);

        Button btn3 = (Button) findViewById(R.id.no);
        //btn3.setTypeface(font);

        txt1 = (TextView) findViewById(R.id.textView1);
        //txt1.setTypeface(font);

        txt2 = (TextView) findViewById(R.id.textViewResult);
        //txt2.setTypeface(font);

        txt3 = (TextView) findViewById(R.id.textViewResult2);
        //txt3.setTypeface(font);

        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        //editTextMobile.setTypeface(font);
        editTextMobile.setHintTextColor(Color.parseColor("#ffffff"));

    }

    public void submit(View view) {
        toMobile = editTextMobile.getText().toString();

        if (toMobile.isEmpty()) {
            showToast("Please enter a mobile number or email");
        } else if (toMobile.length() < 9) {
            showToast("Mobile number must be 9 or 10 or 11 digits");
        } else if (toMobile.length() > 11) {
            showToast("Mobile number must be 9 or 10 or 11 digits");
        } else {
            insertToDatabase(fromMobile, toMobile);
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(InviteFriendActivity.this, SendMessageActivity.class);
        InviteFriendActivity.this.startActivity(intent);
    }

    private void insertToDatabase(String fromMobile, String toMobile) {
        final ProgressDialog progressDialog = new ProgressDialog(InviteFriendActivity.this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("fromMobile", params[0]));
                nameValuePairs.add(new BasicNameValuePair("toMobile", params[1]));
                nameValuePairs.add(new BasicNameValuePair("device", "android"));

                String result = null;
                InputStream inputStream = null;

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://www.tokkalo.com/api/1/invite_friend.php");
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
                        //String status = jsonObj.getString("status");
                        String message = jsonObj.getString("message");

                        //Toast.makeText(ReferFriendActivity.this, message, Toast.LENGTH_SHORT).show();

                        editTextMobile.setVisibility(View.GONE);


                        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                        textViewResult.setText(message);
                        textViewResult.setVisibility(View.VISIBLE);

                        btn1.setVisibility(View.GONE);

                        txt3.setVisibility(View.VISIBLE);

                        btn2 = (Button) findViewById(R.id.yes);
                        btn2.setVisibility(View.VISIBLE);

                        btn3 = (Button) findViewById(R.id.no);
                        btn3.setVisibility(View.VISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(InviteFriendActivity.this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(InviteFriendActivity.this, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(fromMobile, toMobile);
    }

    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();
    }

    public void onBackPressed() {
        Intent intent = new Intent(InviteFriendActivity.this, MemberActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        InviteFriendActivity.this.startActivity(intent);
    }

    public void inviteFriend(View view) {
        Intent intent = new Intent(InviteFriendActivity.this, InviteFriendActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        InviteFriendActivity.this.startActivity(intent);
    }

    public void goToHome(View view) {
        Intent intent = new Intent(InviteFriendActivity.this, MemberActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        InviteFriendActivity.this.startActivity(intent);
    }
}
