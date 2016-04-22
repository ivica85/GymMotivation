package com.ivica.zzzzzzzz;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class koraci extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView brojac;
    boolean aktivnoTrcanje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koraci);




        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarKoraci);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Steps");








        brojac = (TextView) findViewById(R.id.count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        aktivnoTrcanje = true;

        Sensor countsensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (countsensor != null) {
            sensorManager.registerListener(this, countsensor, SensorManager.SENSOR_DELAY_UI);

        } else {
            Toast.makeText(this, "Brojac nije dostupam", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        aktivnoTrcanje = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (aktivnoTrcanje){
            brojac.setText(String.valueOf(event.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
