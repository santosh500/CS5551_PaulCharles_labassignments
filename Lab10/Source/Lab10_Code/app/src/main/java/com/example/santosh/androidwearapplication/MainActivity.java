package com.example.santosh.androidwearapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;


//This code is derived and modified from the CS5590 Tutorial 10 Source Code (AndroidWearapplication)
public class MainActivity extends Activity {

    private TextView a;
    private EditText b;
    private EditText c;
    public static Properties properties = new Properties();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_main);
        a = (TextView) findViewById(R.id.locationView);
        //This code is derived and modified from the CS5590 Tutorial 10 Source Code (AndroidWearapplication)
        Button button = (Button) findViewById(R.id.searchButton);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    //This code is derived and modified from the CS5590 Tutorial 10 Source Code (AndroidWearapplication)
    public void search(View v) {
        b = (EditText) findViewById(R.id.editext_enterWord);
        c = (EditText) findViewById(R.id.editext_enterWord2);
        String search = b.getText().toString();
        String url = "https://api.foursquare.com/v2/venues/search?client_id=Z2TN2KU0NO0KFPAQT4AXV1UHCAT4YTPGITT2TFDJQMRGCOOQ&client_secret=CFUSS1WAR3S0IMJICNJEBCYEXT23JP5MZ3ZWZFZDV1PH4ZZB&v=20160215&limit=5&near=";
        String url3 = "&query=";
        String location = c.getText().toString();
        url = url + location + url3+search;
        String response = null;
        BufferedReader q = null;
        try {
            //This code is derived and modified from the CS5590 Tutorial 10 Source Code (AndroidWearapplication)
            URL url2 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            
            q = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder catch1 = new StringBuilder();
            String line = null;
            
            while ((line = q.readLine()) != null) {
                catch1.append(line + " ");
            }
            response = catch1.toString();
            //This code is derived and modified from the CS5590 Tutorial 10 Source Code (AndroidWearapplication)
            JSONObject result;
            try {
                result = new JSONObject(response);
                JSONObject a = result.getJSONObject("response");
                JSONArray array = a.optJSONArray("venues");
                this.a.setText("Location: " + array.getJSONObject(0).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
        } finally {
            try {
                q.close();
            } catch (Exception ex) {

            }
        }


    }
}
