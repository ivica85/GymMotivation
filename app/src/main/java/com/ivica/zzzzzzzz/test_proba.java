package com.ivica.zzzzzzzz;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;


public class test_proba extends AppCompatActivity {
    private long timeWhenStopped = 0;
    private boolean stopClicked;
    private Chronometer krono;
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_proba_layout);






        mViewPager = (ViewPager)findViewById(R.id.pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);



        krono = (Chronometer)findViewById(R.id.chronometer1);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tulbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Get Started");




    }

    public void resetGumb(View view){
        krono.setBase(SystemClock.elapsedRealtime());
        timeWhenStopped =0;
        TextView donjiText = (TextView)findViewById(R.id.hmsTekst);
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


}
