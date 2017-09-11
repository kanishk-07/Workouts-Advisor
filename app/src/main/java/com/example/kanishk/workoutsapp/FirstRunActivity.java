package com.example.kanishk.workoutsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class FirstRunActivity extends AppCompatActivity {

    public static String USERNAME = "USERNAME";

    public static String user_name = "fool";
    EditText name_of_user;
    Button DONE;
    int result;
    TextToSpeech speaker;

    public static final String NAME_PREFS = "NamePrefs";
    public static final String DISPLAY_NAME_KEY = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor = mPreferences.edit();

        speaker = new TextToSpeech(FirstRunActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    result = speaker.setLanguage(Locale.ENGLISH);
                    //speaker.setSpeechRate(2.0f);
                }
            }
        });

        name_of_user = (EditText)findViewById(R.id.editText);

        mEditor.putString(getString(R.string.u_name),name_of_user.getText().toString()).apply();

        name_of_user.selectAll();

        name_of_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user_name = name_of_user.getText().toString();
            }
        });

        DONE = (Button)findViewById(R.id.button);
        DONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick();
            }
        });
        name_of_user.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                onclick();
                return true;
            }
        });;

    }
    private void onclick() {
        user_name = name_of_user.getText().toString();
        Log.d("FIrST_TESTING","FirstRunACtivity"+user_name);
        SharedPreferences prefs = getSharedPreferences(NAME_PREFS,0);
        prefs.edit().putString(DISPLAY_NAME_KEY,user_name).apply();
        speaker.speak("Welcome "+user_name,TextToSpeech.QUEUE_FLUSH,null,null);
        Intent FirstRunActivityOver = new Intent(FirstRunActivity.this, MainActivity.class);
        //FirstRunActivityOver.putExtra("FIRST_NAME",username);
        finish();
        startActivity(FirstRunActivityOver);
    }
}