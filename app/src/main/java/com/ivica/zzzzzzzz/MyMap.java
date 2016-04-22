package com.ivica.zzzzzzzz;

import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class MyMap extends AppCompatActivity {


    private static final int ERROR_DIALOG_REQUEST = 9001;
    GoogleMap mMap;
    private static final double
            antofagaste_duzina = 43.519672,
            antofagaste_sirina = 16.435786;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);

        if (servicesOK()){
            setContentView(R.layout.activity_map);
            if (initMap()){
                Toast.makeText(this, "Spojeni ste s lokacijom", Toast.LENGTH_SHORT).show();
                goToLocation(antofagaste_duzina,antofagaste_sirina,15);
            }else {
                Toast.makeText(this, "Niste spojeni s lokacijom", Toast.LENGTH_SHORT).show();
            }

        }else {
            setContentView(R.layout.activity_my_map);
        }
    }

    public boolean servicesOK(){
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if(isAvailable== ConnectionResult.SUCCESS){
            return true;
        }else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog =
                    GooglePlayServicesUtil.getErrorDialog(isAvailable, this ,ERROR_DIALOG_REQUEST);
            dialog.show();

        }else{
            Toast.makeText(this, "Ne mogu se spojiti s lokacijom", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    private boolean initMap(){

        if (mMap == null){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
        }
        return (mMap !=null);
    }

    private void goToLocation(double duzina, double sirina,float zoom){
        LatLng latLng = new LatLng(duzina,sirina);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng,zoom);
        mMap.moveCamera(update);
    }

    private void hideSoftKeyboard(View v){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    public void geoLocate (View v) throws IOException {
        hideSoftKeyboard(v);

        TextView tv = (TextView)findViewById(R.id.editText1);
        String searchString = tv.getText().toString();
        Toast.makeText(this,"Potraga za: " + searchString,Toast.LENGTH_SHORT).show();

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(searchString, 1);

        if(list.size()>0){
            Address add = list.get(0);
            String locality = add.getLocality();
            Toast.makeText(this, "Uspjeli ste naci " + locality,Toast.LENGTH_SHORT).show();

            double lat = add.getLatitude();
            double lng = add.getLongitude();
            goToLocation(lat, lng, 15);
        }

    }
}
