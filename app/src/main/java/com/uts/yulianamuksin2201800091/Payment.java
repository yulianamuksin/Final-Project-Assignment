package com.uts.yulianamuksin2201800091;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Payment extends AppCompatActivity {
    //    SQLite DATABASE
    DatabaseHelper mDatabaseHelper;
//    Geocoder geocoder;
//    List<Address> addresses;
//
//    String bestProvider;
//    List<Address> user = null;
//    double lat;
//    double lng;

    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> dt = OrderData.getInstance().datas;
    ArrayList<Integer> tp = OrderData.getInstance().totalPrices;
    int totalP = 0;
    private int balancectr;
    TextView balance2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mDatabaseHelper = new DatabaseHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView priceReceiver = (TextView)findViewById(R.id.totalPrice2);

        balance2 = (TextView) findViewById(R.id.totalbal2);
        balancectr = TopupData.getTopupBal(this);
        balance2.setText(String.valueOf(balancectr));

        if(tp.size() > 0){
            for(int i = 0; i < tp.size(); i++) {
                AddData(i+1 + ")." + dt.get(i));
                list.add(i+1 + ")." + dt.get(i) + "\n");
                totalP += tp.get(i);
            }

            OrderListAP adapter = new OrderListAP(list, this);

            ListView lvPayment = (ListView)findViewById(R.id.listviewpayment);
            lvPayment.setAdapter(adapter);

            priceReceiver.setText(String.valueOf(totalP));
        }
        else if (tp.size() == 0){
            priceReceiver.setText("0");
        }

        AddData("Total: Rp. " +String.valueOf(totalP));
        AddData("\n");

        dt.clear();
        tp.clear();
    }

    public void clickMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    public void AddData(String newEntry) {
        mDatabaseHelper.addData(newEntry);
    }

    public void clickOrderHistory(View view) {
        Intent intent = new Intent(this, OrderHistory.class);
        this.startActivity(intent);
    }
}