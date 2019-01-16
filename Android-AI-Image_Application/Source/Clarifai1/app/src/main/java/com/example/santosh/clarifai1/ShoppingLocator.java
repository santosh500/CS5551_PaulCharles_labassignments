package com.example.santosh.clarifai1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.XML;

import org.json.JSONObject;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ShoppingLocator extends AppCompatActivity {

    String item,storeIdMain,storeAddress,storeName,name1,desc1,category1,aisle1,cityLoc,stateLoc,address1,store1;
    TextView city,state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_locator);
        city = (EditText) findViewById(R.id.editText);
        state = (EditText) findViewById(R.id.editText2);
        item = getIntent().getStringExtra("mainItem");
    }

    public void viewItems(View v) throws InterruptedException {
        cityLoc = city.getText().toString();
        stateLoc = state.getText().toString();
        new locationSearch().execute();
        TimeUnit.SECONDS.sleep(3);
        new itemSearch().execute();
        TimeUnit.SECONDS.sleep(3);
        Intent itemInfo = new Intent(ShoppingLocator.this,Items.class);
        itemInfo.putExtra("mainItem",item);
        itemInfo.putExtra("storeIdMain",storeIdMain);
        itemInfo.putExtra("name1",name1);
        itemInfo.putExtra("desc1",desc1);
        itemInfo.putExtra("category1",category1);
        itemInfo.putExtra("aisle1",aisle1);
        itemInfo.putExtra("store1",storeName);
        itemInfo.putExtra("address1",storeAddress);
        startActivity(itemInfo);
    }

    private class locationSearch extends AsyncTask<String, String,String> {
        protected String doInBackground(String... urls) {

            String str = "http://www.supermarketapi.com/api.asmx/StoresByCityState?APIKEY=024ee6d49f&SelectedCity="+cityLoc+"&SelectedState="+stateLoc;
            //This code is Referenced and Derived from: https://stackoverflow.com/questions/3812091/parsing-external-xml-to-json-in-java
            URL searchURl = null;
            try {
                searchURl = new URL(str);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStream stream = null;
            try {
                stream = searchURl.openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int counter = 0;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                while ((counter = stream.read()) != -1) {
                    stringBuilder.append((char) counter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String xml = stringBuilder.toString();
            JSONObject jsonObject = null;
            try {
                jsonObject = XML.toJSONObject(xml);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject storeId = jsonObject.getJSONObject("ArrayOfStore");
                JSONObject statistics = storeId.getJSONObject("Store");
                storeIdMain = statistics.getString("StoreId");
                storeAddress =statistics.getString("Address");
                storeName=statistics.getString("Storename");
                ;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private class itemSearch extends AsyncTask<String, String,String> {
        protected String doInBackground(String... urls) {
            String main = "http://www.supermarketapi.com/api.asmx/SearchForItem?APIKEY=024ee6d49f&StoreId=" + storeIdMain + "&ItemName=" + item;
            //This code is Referenced and Derived from: https://stackoverflow.com/questions/3812091/parsing-external-xml-to-json-in-java
            URL searchURL = null;
            try {
                searchURL = new URL(main);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStream stream = null;
            try {
                stream = searchURL.openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int counter = 0;
            StringBuilder stringBuilder = new StringBuilder();
            try {
                while ((counter = stream.read()) != -1) {
                    stringBuilder.append((char) counter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String xml = stringBuilder.toString();
            JSONObject jsonObject = null;
            try {
                jsonObject = XML.toJSONObject(xml);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                JSONObject storeId = jsonObject.getJSONObject("ArrayOfProduct");
                String v = storeId.toString();
                JSONArray product = storeId.getJSONArray("Product");
                JSONObject jsonObj = product.getJSONObject(0);
                name1 =jsonObj.getString("Itemname");
                desc1= jsonObj.getString("ItemDescription");
                category1=jsonObj.getString("ItemCategory");
                aisle1=jsonObj.getString("AisleNumber");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
