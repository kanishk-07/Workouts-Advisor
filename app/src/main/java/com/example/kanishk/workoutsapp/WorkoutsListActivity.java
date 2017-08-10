package com.example.kanishk.workoutsapp;

import android.content.Intent;
import android.content.res.Resources;;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class WorkoutsListActivity extends AppCompatActivity {

    public static String[] Tiles;
    int[] imagess = {R.drawable.icon_abdominal_crunches,
            R.drawable.icon_arm_circles,
            R.drawable.icon_arm_raises,
            R.drawable.icon_backward_lunges,
            R.drawable.icon_bicycle_crunch,R.drawable.icon_bird_dog,
            R.drawable.icon_box_push_up,R.drawable.icon_bridge,R.drawable.icon_burpee,
            R.drawable.icon_cobras,R.drawable.icon_plank,R.drawable.icon_push_up};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton upbutton = (ImageButton)findViewById(R.id.upButton);
        upbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView worklist = (ListView)findViewById(R.id.worklist);
        Log.d("WorkoutsApp","LINE 45");

        Resources res = getResources();
        Tiles = res.getStringArray(R.array.TITLES_OF_WORKOUTS);

        WorkoutsListAdapter adapter = new WorkoutsListAdapter(this,Tiles,imagess);
        Log.d("WorkoutsApp","LINE 51");
        worklist.setAdapter(adapter);

        worklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(WorkoutsListActivity.this,CurrentWorkoutActivity.class);
                i.putExtra("Position",position);
                startActivity(i);
            }
        });

    }

}




