package com.example.santosh.clarifai1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Items extends AppCompatActivity {

    String storeId,item;
    TextView name,desc,category,aisle,store,address;
    String name1,desc1,category1,aisle1,store1,address1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        name1="";
        desc1="";
        category1="";
        aisle1="";
        name = (TextView)findViewById(R.id.itemName);
        desc = (TextView)findViewById(R.id.desc);
        category = (TextView)findViewById(R.id.category);
        aisle = (TextView)findViewById(R.id.aisle);
        store = (TextView)findViewById(R.id.textView7);
        address = (TextView)findViewById(R.id.textView9);
        item = getIntent().getStringExtra("mainItem");
        storeId = getIntent().getStringExtra("storeIdMain");
        name1 = getIntent().getStringExtra("name1");
        desc1 = getIntent().getStringExtra("desc1");
        category1 = getIntent().getStringExtra("category1");
        aisle1 = getIntent().getStringExtra("aisle1");
        store1= getIntent().getStringExtra("store1");
        address1=getIntent().getStringExtra("address1");
        name.setText("Item: " + name1);
        desc.setText("Description: " + desc1);
        category.setText("Category: " + category1);
        aisle.setText("Aisle: " + aisle1);
        store.setText("Store: " + store1);
        address.setText("Address: " + address1);

    }



}
