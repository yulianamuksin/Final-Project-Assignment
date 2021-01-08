package com.uts.yulianamuksin2201800091;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RestaurantList extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    float[] listdist = new float[5];

    Geocoder geocoder;
    String bestProvider;
    List<Address> user = null;
    double lat;
    double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        get_json();

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        bestProvider = lm.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(bestProvider);

        if (location == null){
            Toast.makeText(this,"Location Not found",Toast.LENGTH_LONG).show();
        }else{
            geocoder = new Geocoder(this);
            try {
                user = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                lat=(double)user.get(0).getLatitude();
                lng=(double)user.get(0).getLongitude();
                //System.out.println(" DDD lat: " +lat+",  longitude: "+lng);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void get_json(){
        String json;
        try{
            InputStream is = getAssets().open("configmap.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i<jsonArray.length(); i++){

                JSONObject obj = jsonArray.getJSONObject(i);

                float[] results = new float[1];
                Location.distanceBetween(lat, lng, obj.getDouble("lat"), obj.getDouble("lang"), results);
                float distance = results[0]/1000;

                listdist[i] = distance;
                list.add(obj.getString("name") + "\n" + obj.getString("address") + "\nDistance: " + distance + " km");
            }

            // sort nearest restaurants
            float temp;
            for (int i = 0; i < 5; i++)
            {
                for (int j = i + 1; j < 5; j++) {
                    if (listdist[i] > listdist[j])
                    {
                        temp = listdist[i];
                        listdist[i] = listdist[j];
                        listdist[j] = temp;
                        Collections.swap(list, i, j);
                    }
                }
            }

            RestaurantListAdapter adapter = new RestaurantListAdapter(list, this);

            ListView lvPayment = (ListView)findViewById(R.id.listviewpayment);
            lvPayment.setAdapter(adapter);

        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clickMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}

