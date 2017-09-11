package com.example.kanishk.workoutsapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

;

public class CurrentWorkoutActivity extends AppCompatActivity {

    TextToSpeech speaker;
    int result;
    //boolean background = true;
    Button volume;
    //AudioManager mAudioManager;
    private int i;
    private int j=0;
    //Context context;
    final Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton go_up = (ImageButton)findViewById(R.id.go_up);
        go_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("Position");
        String header = WorkoutsListActivity.Tiles[position];
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("\t\t\t\t"+header);
        TextView name = (TextView) findViewById(R.id.workout_name);
        name.setText(header);
        TextView des = (TextView) findViewById(R.id.workout_des);
        Resources res = getResources();
        String[] description = res.getStringArray(R.array.DESCRIPTIONS_OF_WORKOUTS);
        des.setText(description[position]);

        volume = (Button) findViewById(R.id.vol_button);
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muteandchange();
            }
        });

        final Handler handler = new Handler();

        speaker = new TextToSpeech(CurrentWorkoutActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    result = speaker.setLanguage(Locale.ENGLISH);
                    //speaker.setSpeechRate(2.0f);
                }else{
                    Toast.makeText(CurrentWorkoutActivity.this,"Speaking feature not supported in your device (-_-)",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        final ImageView show = (ImageView)findViewById(R.id.imageView3);
        final Runnable runnable1;


        if (position == 0)      //Abdominal Crunches
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.w_abdominal_crunch_1);
                    speaker.speak("Get ready for 25 rounds of abdominal crunches", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if (i % 2 == 0) {
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.w_abdominal_crunch_1);
                            } else {
                                show.setImageResource(0);
                                speaker.speak(String.valueOf((i/2)+1),TextToSpeech.QUEUE_FLUSH,null,null);
                                show.setImageResource(R.drawable.w_abdominal_crunch_2);
                            }
                            if (i++ < 50)
                                handler.postDelayed(this, 1000);
                            else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,4000);
        }



        else if (position == 1) //Arm Circles
        {
            i=0;
            final int[] images = {R.drawable.arm_circles_1, R.drawable.arm_circles_2, R.drawable.arm_circles_3, R.drawable.arm_circles_4,
                    R.drawable.arm_circles_5, R.drawable.arm_circles_6, R.drawable.arm_circles_7, R.drawable.arm_circles_8,
                    R.drawable.arm_circles_9, R.drawable.arm_circles_10, R.drawable.arm_circles_11, R.drawable.arm_circles_12,
                    R.drawable.arm_circles_13, R.drawable.arm_circles_14, R.drawable.arm_circles_15, R.drawable.arm_circles_16,
                    R.drawable.arm_circles_17, R.drawable.arm_circles_18, R.drawable.arm_circles_19, R.drawable.arm_circles_20};

            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.arm_circles_1);
                    speaker.speak("Get set for 5 rounds of arm circles", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,450);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
//                            if(j == 0) {
//                                speaker.speak("Forwards",TextToSpeech.QUEUE_FLUSH,null,null);
//                                //Log.d("XXYYZZ", String.valueOf(j));
//                            }
                            if (j < 9) {
                                if(j == 0) {
                                    speaker.speak("Forwards",TextToSpeech.QUEUE_FLUSH,null,null);
                                    //Log.d("XXYYZZ", String.valueOf(j));
                                }
                                j++;
                                show.setImageResource(0);
                                show.setImageResource(images[j+1]);
                            }
                            else if(j == 9){
                                speaker.speak("Backwards",TextToSpeech.QUEUE_FLUSH,null,null);
                                j++;
                            }

                            else if (j < 19) {
                                show.setImageResource(0);
                                show.setImageResource(images[j+1]);
                                j++;
                                if(j == 20)
                                {
                                    speaker.speak("Forwards",TextToSpeech.QUEUE_FLUSH,null,null);
                                }
                            }
                            if (i++ < 94)
                            {
                                if(j == 19)
                                    j = 0;
                                //Log.d("XXYYZZ", String.valueOf(j));
                                handler.postDelayed(this, 500);
                            }
                            else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,4500);

        }



        else if (position == 2) //Arm Raises
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.w_arm_raises_1);
                    speaker.speak("Get set for 25 rounds of arm raises", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if (i % 2 == 0) {
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.w_arm_raises_1);
                            } else {
                                show.setImageResource(0);
                                speaker.speak(String.valueOf((i/2)+1),TextToSpeech.QUEUE_FLUSH,null,null);
                                show.setImageResource(R.drawable.w_arm_raises_2);
                            }
                            if (i++ < 50)
                                handler.postDelayed(this, 800);
                            else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,3000);
        }



        else if (position == 3)  //Backward Lunges
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.backward_lunge_1);
                    speaker.speak("Get ready for 15 rounds of backward lunges", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if (j == 0) {
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.backward_lunge_1);
                                j++;
                            } else if(j == 1){
                                speaker.speak(String.valueOf(i/4 + 1), TextToSpeech.QUEUE_FLUSH, null, null);
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.backward_lunge_4);//to make right leg go first
                                j++;
                            } else if(j == 2){
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.backward_lunge_3);
                                j++;
                            } else if(j == 3){
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.backward_lunge_2);
                                j++;
                            }
                            if (i++ < 60)
                            {
                                if(j == 4)
                                    j=0;
                                handler.postDelayed(this, 800);
                            } else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,4000);

        }



        else if (position == 4) //Bicycle Crunches
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.bicycle_crunch_1);
                    speaker.speak("Get set for 15 rounds of bicycle crunches", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if (j == 0) {
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.bicycle_crunch_1);
                                j++;
                            } else if(j == 1){
                                speaker.speak(String.valueOf(i/4 + 1), TextToSpeech.QUEUE_FLUSH, null, null);
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.bicycle_crunch_2);//to make right leg go first
                                j++;
                            } else if(j == 2){
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.bicycle_crunch_3);
                                j++;
                            } else if(j == 3){
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.bicycle_crunch_4);
                                j++;
                            }
                            if (i++ < 60)
                            {
                                if(j == 4)
                                    j=0;
                                handler.postDelayed(this, 800);
                            } else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,4000);

        }



        else if (position == 5)  //Bird Dog
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.bird_dog_1);
                    speaker.speak("Get set for 5 rounds of Bird Dog", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if (j == 0) {
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.bird_dog_1);
                                j++;
                            } else if(j == 1){
                                speaker.speak(String.valueOf(i/4 + 1), TextToSpeech.QUEUE_FLUSH, null, null);
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.bird_dog_2);
                                j++;
                            } else if(j == 2){
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.bird_dog_3);
                                j++;
                            } else if(j == 3){
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.bird_dog_4);
                                j++;
                            }
                            if (i++ < 20)
                            {
                                if(j == 4)
                                    j=0;
                                if(j == 0 || j==2)
                                    handler.postDelayed(this, 3000);
                                else if(j == 1 || j == 3)
                                    handler.postDelayed(this, 1000);
                            } else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,3000);

        }

        else if (position == 6) //Box Push Ups
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.box_push_up_1);
                    speaker.speak("Get into position for 35 rounds of box push ups", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if (i % 2 == 0) {
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.box_push_up_1);
                            } else {
                                show.setImageResource(0);
                                speaker.speak(String.valueOf((i/2)+1),TextToSpeech.QUEUE_FLUSH,null,null);
                                show.setImageResource(R.drawable.box_push_up_2);
                            }
                            if (i++ < 70)
                                handler.postDelayed(this, 900);
                            else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,5000);
        }



        else if (position == 7) //Bridge
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.bridge_1);
                    speaker.speak("Make a bridge for 45 seconds", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if(j > 25)
                                speaker.speak(String.valueOf(45-j),TextToSpeech.QUEUE_FLUSH,null,null);
                            j++;
                            if(j < 46) {
                                handler.postDelayed(this, 1000);
                            }
                            else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,2000);

        }



        else if (position == 8) //Burpees
        {
            i=0;
            final int[] images = {R.drawable.burpee_1, R.drawable.burpee_2, R.drawable.burpee_3, R.drawable.burpee_4,
                    R.drawable.burpee_5, R.drawable.burpee_6, R.drawable.burpee_7, R.drawable.burpee_8,
                    R.drawable.burpee_9, R.drawable.burpee_10, R.drawable.burpee_11};

            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.burpee_1);
                    speaker.speak("Get ready for 10 rounds of burpees", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,450);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            show.setImageResource(images[j]);
                            j++;
                            if(j == 1)
                                speaker.speak(String.valueOf(i/11 + 1),TextToSpeech.QUEUE_FLUSH,null,null);
                            if (i++ < 110)
                            {
                                if(j == 11)
                                    j = 0;
                                //Log.d("XXYYZZ", String.valueOf(j));
                                handler.postDelayed(this, 200);
                            }
                            else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,3500);

        }



        else if (position == 9) //Cobras
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.cobras_1);
                    speaker.speak("Get set for 10 rounds of cobras", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if (j == 0) {
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.cobras_1);
                                j++;
                            } else if(j == 1){
                                speaker.speak(String.valueOf(i/3 + 1), TextToSpeech.QUEUE_FLUSH, null, null);
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.cobras_2);//to make right leg go first
                                j++;
                            } else if(j == 2){
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.cobras_3);
                                j++;
                            }
                            if (i++ < 30)
                            {
                                if(j == 3)
                                    j=0;
                                handler.postDelayed(this, 1000);
                            } else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,3000);

        }



        else if (position == 10) //Plank
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.plank_1);
                    speaker.speak("Get into position for 1 minute of plank", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if(j > 25)
                                speaker.speak(String.valueOf(60-j),TextToSpeech.QUEUE_FLUSH,null,null);
                            j++;
                            if(j < 61) {
                                handler.postDelayed(this, 1000);
                            }
                            else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,3000);

        }



        else if (position == 11) //Push Ups
        {
            i=0;
            runnable1 = new Runnable() {
                public void run() {
                    show.setImageResource(R.drawable.push_up_1);
                    speaker.speak("Do 40 push ups", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            };
            handler.postDelayed(runnable1,500);

            handler.postDelayed(runnable1,500);

            final Runnable runnable2 = new Runnable() {
                public void run() {
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            if (i % 2 == 0) {
                                show.setImageResource(0);
                                show.setImageResource(R.drawable.push_up_1);
                            } else {
                                show.setImageResource(0);
                                speaker.speak(String.valueOf((i/2)+1),TextToSpeech.QUEUE_FLUSH,null,null);
                                show.setImageResource(R.drawable.push_up_2);
                            }
                            if (i++ < 80)
                                handler.postDelayed(this, 950);
                            else {
                                speaker.setSpeechRate(2.0f);
                                speaker.speak("Relax!",TextToSpeech.QUEUE_FLUSH,null,null);
                                Toast.makeText(CurrentWorkoutActivity.this, "Workout Completed", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    };
                    handler.post(runnable);
                }
            };handler.postDelayed(runnable2,3000);
        }
        


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //toast.cancel();

        if(speaker != null)
        {
            speaker.stop();
            speaker.shutdown();
        }
    }

    public void muteandchange() {
//        //Context context = getApplicationContext();
//        if (background){
//            background = false;
//            volume.setBackgroundResource(R.drawable.ic_volume_up_black_24dp);
//            //mAudioManager =  (AudioManager) getSystemService(AUDIO_SERVICE);
//            //mAudioManager.setMode(AudioManager.ADJUST_MUTE);
//            setVolumeControlStream(AudioManager.STREAM_DTMF);
//            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            //audioManager.setStreamMute(AudioManager.STREAM_DTMF, true);
//            audioManager.adjustStreamVolume(stream, AudioManager.ADJUST_MUTE, 0);
//        }
//        else {
//            background = true;
//            volume.setBackgroundResource(R.drawable.ic_volume_off_black_24dp);
//        }

        speaker.shutdown();
        volume.setBackgroundResource(R.drawable.ic_volume_off_black_24dp);

    }
}//v.setBackgroundResource(isButtonClicked ? R.drawable.btn_star_on :