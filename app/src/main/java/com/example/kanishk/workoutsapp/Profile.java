package com.example.kanishk.workoutsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Profile extends AppCompatActivity {

    public static final int IMAGE_REQUEST_CODE = 20;
    public static String user_gender;
    public static String weight;
    public static String waist;
    EditText nameText;
    EditText weightText;
    EditText waistText;
    ImageButton img;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton upbutton = (ImageButton)findViewById(R.id.go_up);
        upbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        img = (ImageButton) findViewById(R.id.imageButton);
        if(getSharedPreferences("Profile", MODE_PRIVATE).getString("IMAGE",null) == null);
        else{
            Bitmap bitmap = decodeBase64(getSharedPreferences("Profile", MODE_PRIVATE).getString("IMAGE",null));
            img.setImageBitmap(bitmap);
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                String path = pictureDirectory.getPath();
                Uri data = Uri.parse(path);
                photoPicker.setDataAndType(data, "image/*");
                startActivityForResult(photoPicker, IMAGE_REQUEST_CODE);
                /*Here we made a constant and it should be unique because many activities can be
                returning us something and this differentiates between which activity should go*/
                //It is how to go to image now how to use result --> OnActivityResult function
            }

        });

        SharedPreferences ProAc = getSharedPreferences("Profile", MODE_PRIVATE);
        final SharedPreferences.Editor editor = ProAc.edit();

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor mEditor = mPreferences.edit();

        nameText = (EditText) findViewById(R.id.editText2);
        nameText.setText(FirstRunActivity.user_name);
        nameText.setEnabled(false);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameText.setEnabled(true);
            }
        });
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                FirstRunActivity.user_name = nameText.getText().toString();
                mEditor.putString(getString(R.string.u_name),nameText.getText().toString()).apply();
            }
        });
        //nameText.setText(getSharedPreferences("Profile", MODE_PRIVATE).getString("NAME", FirstRunActivity.user_name));

        weightText = (EditText) findViewById(R.id.editText3);
        weightText.setEnabled(false);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightText.setEnabled(true);
            }
        });
        weightText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("WEIGHT", weightText.getText().toString()).apply();
            }
        });
        weightText.setText(getSharedPreferences("Profile", MODE_PRIVATE).getString("WEIGHT", "Weight"));

        waistText = (EditText) findViewById(R.id.editText4);
        waistText.setEnabled(false);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waistText.setEnabled(true);
            }
        });
        waistText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("WAIST", waistText.getText().toString()).apply();
            }
        });
        waistText.setText(getSharedPreferences("Profile", MODE_PRIVATE).getString("WAIST", "Waist"));


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(ProAc.getInt("GENDER", 0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user_gender = parent.getItemAtPosition(position).toString();
                editor.putInt("GENDER", position).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //***Check Later***//
            }
        });

        //editor.putString("NAME",nameText.getText().toString()).apply();
        //editor.putString("WEIGHT",weightText.getText().toString()).apply();
        //editor.putString("WAIST",waistText.getText().toString()).apply();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //everything processed successfully
            if (requestCode == IMAGE_REQUEST_CODE) {
                //we are hearing back from gallery
                Uri imageUri = data.getData();//universal image indicator,address of image on sd card

                InputStream inputStream;//declare a stream to read image data from the sd card
                //now any time we use input stream it might fail so we use try catch block
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    img.setImageBitmap(bitmap);
                    String store = encodeTobase64(bitmap);
                    getSharedPreferences("Profile", MODE_PRIVATE).edit().putString("IMAGE",store).apply();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Image loading failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
    // method for base64 to bitmap
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
