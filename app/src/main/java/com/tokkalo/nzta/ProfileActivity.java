package com.tokkalo.nzta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    private Button btn;
    private Button btn2;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private EditText email;
    private EditText name;
    String profileName = "";
    String profileEmail = "";
    Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        applicationContext = getApplicationContext();

        getSupportActionBar().hide();

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/handlee-regular.ttf");

        btn = (Button) findViewById(R.id.button);
        btn.setTypeface(font);

        btn2 = (Button) findViewById(R.id.buttonSave);
        btn2.setTypeface(font);

        txt1 = (TextView) findViewById(R.id.textView1);
        txt1.setTypeface(font);

        txt2 = (TextView) findViewById(R.id.textView2);
        txt2.setTypeface(font);

        txt3 = (TextView) findViewById(R.id.textView3);
        txt3.setTypeface(font);

        if (extras != null) {
            if (extras.containsKey("name")) {
                profileName = intent.getStringExtra("name");
                txt1.setText(profileName);
            } else {
                txt1.setText("Ramesh Kolamala");
            }

            if (extras.containsKey("email")) {
                profileEmail = intent.getStringExtra("email");
                txt3.setText(profileEmail);
            } else {
                txt3.setText("rameshkolamala@gmail.com");
            }
        }

        name = (EditText) findViewById(R.id.editTextName);
        name.setTypeface(font);

        email = (EditText) findViewById(R.id.editTextEmail);
        email.setTypeface(font);
    }

    public void myGallery(View view) {
        Intent i = new Intent(applicationContext, GalleryActivity.class);
        i.putExtra("galleryType", "My Gallery");
        i.putExtra("year", "");
        startActivity(i);
        finish();
    }

    public void editProfile(View view) {
        View btnSave = findViewById(R.id.buttonSave);
        btnSave.setVisibility(View.VISIBLE);

        View btnGallery = findViewById(R.id.button);
        btnGallery.setVisibility(View.GONE);

        TextView txtName = (TextView) findViewById(R.id.textView1);
        txtName.setVisibility(View.GONE);

        EditText editName = (EditText) findViewById(R.id.editTextName);
        editName.setVisibility(View.VISIBLE);
        editName.setText(txtName.getText());

        editName.setActivated(true);
        //editName.setPressed(true);
        //editName.setCursorVisible(true);
        //editName.setSelection(editName.getText().length());

        TextView txtEmail = (TextView) findViewById(R.id.textView3);
        txtEmail.setVisibility(View.GONE);

        EditText editEmail = (EditText) findViewById(R.id.editTextEmail);
        editEmail.setVisibility(View.VISIBLE);
        editEmail.setText(txtEmail.getText());
    }

    public void saveProfile(View view) {
        EditText editName = (EditText) findViewById(R.id.editTextName);
        EditText editEmail = (EditText) findViewById(R.id.editTextEmail);
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();

        if (name.isEmpty()) {
            showToast("Please enter a name");
        } else if (email.isEmpty()) {
            showToast("Please enter an email id");
        } else {
            Intent i = new Intent(applicationContext, ProfileActivity.class);
            i.putExtra("name", name);
            i.putExtra("email", email);
            startActivity(i);
            finish();
        }


    }

    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();
    }
}
