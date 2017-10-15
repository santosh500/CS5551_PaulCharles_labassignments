package com.example.santosh.lab43;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

public class PlacesApp extends AppCompatActivity{
    //Code was derived and referenced from Google Developers: https://developers.google.com/places/android-api/placepicker
    int PLACE_PICKER_REQUEST = 1;
    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

    TextView a,b,c,d;
    LatLng g;
    CharSequence location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_app);
        a = (TextView) findViewById(R.id.textView);
        b = (TextView) findViewById(R.id.textView2);
        c = (TextView) findViewById(R.id.textView3);
        d = (TextView) findViewById(R.id.textView4);
        try {
            //Code was derived and referenced from Google Developers: https://developers.google.com/places/android-api/placepicker
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public void goToMap(View v)
    {
        //Code was derived and referenced from Google Developers: https://developers.google.com/places/android-api/placepicker
        Intent qu = new Intent(PlacesApp.this, MapsActivity.class);
        qu.putExtra("latlong",g);
        qu.putExtra("location",location);
        startActivity(qu);
    }

    //Code was derived and referenced from Google Developers: https://developers.google.com/places/android-api/placepicker
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Code was derived and referenced from Google Developers: https://developers.google.com/places/android-api/placepicker
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Location: %s", place.getName());


                a.setText(toastMsg);
                b.setText(String.format("Phone Number: %s", place.getPhoneNumber()));
                String q = String.format("LatLang: %s", place.getLatLng());
                d.setText(String.format("Address: %s", place.getAddress()));
                g=place.getLatLng();
                location = place.getName();
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

            }
        }
    }
}
