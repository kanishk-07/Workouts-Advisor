package com.example.kanishk.workoutsapp;

import android.content.Context;
import android.content.Intent;;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView Name;

    //SQLiteDatabase myBase = this.openOrCreateDatabase("DATES",MODE_PRIVATE,null);

    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();

        boolean isFirstRun = getSharedPreferences("firstPreference", MODE_PRIVATE).getBoolean("isfirstrun",true);//*******

        if(isFirstRun){
            getSharedPreferences("firstPreference", MODE_PRIVATE).edit().
                    putBoolean("isfirstrun",false).apply();
            getSharedPreferences("Streak", MODE_PRIVATE).edit().
                    putInt("InstallationDay", calendar.get(Calendar.DAY_OF_YEAR)).apply();
            getSharedPreferences("Streak", MODE_PRIVATE).edit().
                    putInt("InstallationMonth", calendar.get(Calendar.MONTH)).apply();
            getSharedPreferences("Streak", MODE_PRIVATE).edit().
                    putInt("InstallationYear", calendar.get(Calendar.YEAR)).apply();

            //myBase.execSQL("CREATE TABLE IF NOT EXISTS dates (day INT(2),month INT(2),year INT(4))");

            Intent IntentFirstRunAct = new Intent(MainActivity.this,FirstRunActivity.class);
            finish();
            startActivity(IntentFirstRunAct);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SENDTO);
                emailIntent.setData(new Uri.Builder().scheme("mailto").build());
                //emailIntent.setType("message/rfc822");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {
                        "kanishksharma809@gmail.com" });
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Regarding WorkoutsApp");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"This app is \n\n From,\n"+FirstRunActivity.user_name);
                //emailIntent.setType("text/plain");
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                    //Toast.makeText(MainActivity.this, "Mail Send, (^_^)", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Mail Sending failed (-_-)", Toast.LENGTH_SHORT).show();                }
            }
        });

        Button btn_gotowork = (Button) findViewById(R.id.btn_gotowork);
        btn_gotowork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWorkoutActivity();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mEditor = mPreferences.edit();

        String name;
        name = mPreferences.getString(getString(R.string.u_name),"");
//
        //name = FirstRunActivity.prefs.getString("USERNAME","");

        //name = getSharedPreferences("myPrefs", Context.MODE_WORLD_READABLE).getString("USERNAME","");

        Name = (TextView)findViewById(R.id.textView2);//////////////////////////////

        //String naam = "Hey "+name+",";
        String naam = "Hey ".concat(name).concat(",");
        Name.setText(naam);

        int prevDay = getSharedPreferences("Streak", MODE_PRIVATE).getInt("InstallationDay", 0);
        int prevYear = getSharedPreferences("Streak", MODE_PRIVATE).getInt("InstallationYear", 0);

        int Day = calendar.get(Calendar.DAY_OF_YEAR);
        int Year = calendar.get(Calendar.YEAR) + 1900;
        //myBase.execSQL("INSERT INTO dates (day,month,year) VALUES ()");
        int streak = getSharedPreferences("Streak", MODE_PRIVATE).getInt("AcStreak", 0);
        if(Day == prevDay && Year == prevYear) { // && Month == prevMonth) {

        }
        else if((Day - prevDay == 1) && Year == prevYear)
            streak++;
        else if(Day - prevDay == -365 && Year - prevYear == 1)
        {
            streak++;
        }
        else if(Day - prevDay == -364)
        {
            if(prevYear % 4 != 0)
                streak++;
            else if(prevYear % 100 == 0 && prevYear % 400 !=0)
                streak++;
        }
        else
            streak = 0;


        getSharedPreferences("Streak", MODE_PRIVATE).edit().putInt("InstallationDay", Day).apply();
        getSharedPreferences("Streak", MODE_PRIVATE).edit().putInt("InstallationYear", Year).apply();
        getSharedPreferences("Streak", MODE_PRIVATE).edit().putInt("AcStreak", streak).apply();

        TextView StreakView = (TextView) findViewById(R.id.StreakView);
        StreakView.setText(String.valueOf(streak));

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.CalanderView) {
            Intent calander = new Intent(MainActivity.this,CalanderActivity.class);
            startActivity(calander);
            return true;
        }
        else if (id == R.id.GraphView) {
            Intent graph = new Intent(MainActivity.this,GraphActivity.class);
            startActivity(graph);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent goprofile = new Intent(MainActivity.this, Profile.class);
            startActivity(goprofile);
        }
        else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {
                    "" });
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "WorkoutsApp");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hi,\n I found this app " +
                    "'WorkoutsApp' \n\n From,\n"+FirstRunActivity.user_name);
            //emailIntent.setType("text/plain");
            startActivity(shareIntent);
        }
        else if (id == R.id.nav_rate) {
//            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
//            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
//            // To count with Play market backstack, After pressing back button,
//            // to taken back to our application, we need to add following flags to intent.
//            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
//                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
//                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//            try {
//                startActivity(goToMarket);
//            } catch (ActivityNotFoundException e) {
//                startActivity(new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
//            https://stackoverflow.com/questions/10816757/rate-this-app-link-in-google-play-store-app-on-the-phone/25121093
//            }
            Toast.makeText(MainActivity.this,"This app has not been published on PlayStore or any other App Market",Toast.LENGTH_LONG).show();

        }else if (id == R.id.nav_go_to_workouts_list) {
            goToWorkoutActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    public void goToWorkoutActivity()
    {
        Intent go_work = new Intent(MainActivity.this,WorkoutsListActivity.class);
        startActivity(go_work);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String naam = "Hey "+mPreferences.getString(getString(R.string.u_name)+",","");
        Name.setText(naam);
        //Name.setText("Hey "+ mPreferences.getString(getString(R.string.u_name) +",",""));
    }

}

