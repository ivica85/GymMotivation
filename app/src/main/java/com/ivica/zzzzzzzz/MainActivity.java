package com.ivica.zzzzzzzz;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private long timeWhenStopped = 0;
    private boolean stopClicked;
    private Chronometer krono;


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        krono = (Chronometer) findViewById(R.id.chronometer1);




        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.drawer_open,R.string.drawer_closed);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);




        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_contener, new HomeFragment());
        fragmentTransaction.commit();

        getSupportActionBar().setTitle("GymMotivation");
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.id_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_contener,new HomeFragment());
                        fragmentTransaction.commit();
                        Intent intent = new Intent(MainActivity.this,test_proba.class);
                        startActivity(intent);
                        getSupportActionBar().setTitle("GymMotivation");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.id_kompas:
                        //fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        //fragmentTransaction.replace(R.id.main_contener,new Kompas());
                        //fragmentTransaction.commit();
                        Intent intent1 = new Intent(MainActivity.this,CompassActivity.class);
                        startActivity(intent1);
                        getSupportActionBar().setTitle("GymMotivation");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.id_karta:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_contener,new Karte());
                        fragmentTransaction.commit();
                        Intent intent2 = new Intent(MainActivity.this,MyMap.class);
                        startActivity(intent2);
                        getSupportActionBar().setTitle("GymMotivation");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

    }

    public void resetGumb(View view) {
        krono.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped = 0;
        TextView donjiText = (TextView) findViewById(R.id.hmsTekst);
        donjiText.setText("0 Sekundi");

    }



    public void startGumb(View v) {
        krono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        krono.start();
        stopClicked = false;
    }



    public void stopGumb(View view){
        if(!stopClicked){
            TextView donjiText =(TextView)findViewById(R.id.hmsTekst);
            timeWhenStopped = krono.getBase() - SystemClock.elapsedRealtime();
            int sekunde = (int) timeWhenStopped / 1000;
            donjiText.setText(Math.abs(sekunde) + "  sekunde");
            krono.stop();
            stopClicked = true;

        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

}
