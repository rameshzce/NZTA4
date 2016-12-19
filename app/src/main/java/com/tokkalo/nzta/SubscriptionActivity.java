package com.tokkalo.nzta;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SubscriptionActivity extends AppCompatActivity {
    private EditText editTextMobile;
    private EditText editTextName;
    private EditText editTextReferredby;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private String fromMobile;
    private String toMobile;
    private String referredBy;
    private String toName;
    RadioButton radioButton;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        getSupportActionBar().hide();


        SharedPreferences prefs = getSharedPreferences("com.tokkalo.nzta", Context.MODE_PRIVATE);

        fromMobile = prefs.getString("mobileNumber", "");

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        btn1 = (Button) findViewById(R.id.button);
        //btn1.setTypeface(font);

        Button btn2 = (Button) findViewById(R.id.yes);
        //btn2.setTypeface(font);

        Button btn3 = (Button) findViewById(R.id.no);
        //btn3.setTypeface(font);

        txt1 = (TextView) findViewById(R.id.textView1);
        //txt1.setTypeface(font);

        txt4 = (TextView) findViewById(R.id.textView2);
        //txt4.setTypeface(font);

        txt2 = (TextView) findViewById(R.id.textViewResult);
        //txt2.setTypeface(font);

        txt3 = (TextView) findViewById(R.id.textViewResult2);
        //txt3.setTypeface(font);

        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        //editTextMobile.setTypeface(font);
        editTextMobile.setHintTextColor(Color.parseColor("#ffffff"));

        editTextName = (EditText) findViewById(R.id.editTextName);
        //editTextName.setTypeface(font);
        editTextName.setHintTextColor(Color.parseColor("#ffffff"));

        editTextReferredby = (EditText) findViewById(R.id.editTextReferredby);
        //editTextReferredby.setTypeface(font);
        editTextReferredby.setHintTextColor(Color.parseColor("#ffffff"));


        radioButton = (RadioButton) findViewById(R.id.familyYrarly);
        //radioButton.setTypeface(font);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ;

        //radioGroup.setLeft(editTextName.getLeft());
        radioGroup.setLeft(50);

        radioButton2 = (RadioButton) findViewById(R.id.studentYearly);
        //radioButton2.setTypeface(font);

        radioButton3 = (RadioButton) findViewById(R.id.lifetime);
        //radioButton3.setTypeface(font);
    }

    public void subscribe(View view) {
        toMobile = editTextMobile.getText().toString();
        toName = editTextName.getText().toString();
        referredBy = editTextReferredby.getText().toString();

        if (toName.isEmpty()) {
            showToast("Please enter a name");
        } else if (toMobile.isEmpty()) {
            showToast("Please enter a mobile");
        } else if (toMobile.length() < 9) {
            showToast("Mobile number must be 9 or 10 or 11 digits");
        } else if (toMobile.length() > 11) {
            showToast("Mobile number must be 9 or 10 or 11 digits");
        } else if (referredBy.isEmpty()) {
            showToast("Please enter a referrence");
        } else {
            //insertToDatabase(fromMobile, toMobile);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.familyYrarly:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.studentYearly:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.lifetime:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();
    }
}
