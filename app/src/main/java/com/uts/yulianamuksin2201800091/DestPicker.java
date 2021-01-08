package com.uts.yulianamuksin2201800091;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DestPicker extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    EditText adr;
    String fullAddress = "";
    List<Address> addressList;
    String adrstr="";
    Geocoder g;
    double lat;
    double lng;
    ArrayList<String> list = new ArrayList<>();
    float[] listdist = new float[5];
    int whi = 0;
    String chosenresto="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.destpicker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabaseHelper = new DatabaseHelper(this);

        adr = (EditText) findViewById(R.id.destadr);

    }

    public void clickProceedPayment(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(DestPicker.this).create();

        adrstr = adr.getText().toString();

        if ("".equals(adrstr) || "0".equals(adrstr)) {
            alertDialog.setTitle("Input a Valid Road Name");
            alertDialog.setMessage("Road name must be Valid!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            g = new Geocoder(this);
            addressList = null;
            String searchRoad = adrstr;
            try {
                addressList = g.getFromLocationName(searchRoad, 1);
            } catch (IOException e) {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT)
                        .show();
                e.printStackTrace();

            } finally {
                if (addressList.size()==0) {
//                    Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT)
//                            .show();
                    alertDialog.setTitle("Location Not Found or Invalid Location");
                    alertDialog.setMessage("Please input a Valid Street Name (Example: Jl. Thamrin, Jl. Kebon Jeruk, etc.)");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else{
                    String addr = addressList.get(0).getAddressLine(0);
                    fullAddress = addr;
                    String currDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                    AddData("Order Created: " + currDate);
                    AddData("Destination: " + fullAddress);
                    convertAddress();
                    get_json();
                    if(chosenresto.equals("")) chosenresto = list.get(0);
                    AddData("Restaurant: " + chosenresto);
                    Intent intent = new Intent(this, Payment.class);
                    this.startActivity(intent);
                }
            }
        }
    }

    public void AddData(String newEntry) {
        mDatabaseHelper.addData(newEntry);
    }

    public void clickChooseResto(View v){
        chosenresto = "";
        whi = 0;
        convertAddress();
        get_json();
        AlertDialog.Builder builder = new AlertDialog.Builder(DestPicker.this);
        builder.setTitle("Choose an animal");

        final String[] allrestos = {list.get(0),list.get(1),list.get(2),list.get(3),list.get(4)};
        builder.setItems(allrestos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // 1st nearest adr
                    case 1: // 2nd nearest adr
                    case 2: // 3rd nearest adr
                    case 3: // 4th nearest adr
                    case 4: // 5th nearest adr
                }
                //Toast.makeText(DestPicker.this, "hasil: " + allrestos[which], Toast.LENGTH_LONG).show();

                if(String.valueOf(which) == null) {
                    chosenresto += allrestos[0];
                }
                else {
                    chosenresto += allrestos[which];
                }
                //whi = which;
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //AddData("Restaurant: " + list.get(0));
                chosenresto += allrestos[0];
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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

        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void convertAddress() {
        adrstr = adr.getText().toString();

        g = new Geocoder(this);
        String searchRoad = adrstr;
        try {
            addressList = g.getFromLocationName(searchRoad, 1);
            if (addressList.size()==0) {
                Toast.makeText(this, "Delivery Location Not Found!", Toast.LENGTH_SHORT)
                        .show();
            }else if (addressList.size() > 0){
                lat = addressList.get(0).getLatitude();
                lng = addressList.get(0).getLongitude();
                Toast.makeText(DestPicker.this, "Delivery Location: " + lat + " " + lng, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Please Input Destination Address First!", Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }
}
