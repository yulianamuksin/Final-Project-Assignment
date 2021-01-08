package com.uts.yulianamuksin2201800091;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MyOrder extends AppCompatActivity {

    int totalP = 0;
    int totalP2 = 0;
    private int balancectr;
    TextView balance2;
    TextView priceReceiver;

    ArrayList<String> dt = OrderData.getInstance().datas;
    ArrayList<Integer> tp = OrderData.getInstance().totalPrices;
    ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        priceReceiver = (TextView)findViewById(R.id.totalPrice2);
        TextView noOrderYet = (TextView)findViewById(R.id.noOrderYet);

        balance2 = (TextView) findViewById(R.id.totalbal2);
        balancectr = TopupData.getTopupBal(this);
        balance2.setText(String.valueOf(balancectr));

        if(dt.size() > 0){
            for(int i = 0; i < dt.size(); i++) {
                list.add(i+1 + ")." + dt.get(i) + "\n");
                totalP += tp.get(i);
            }

            OrderListAdapter adapter = new OrderListAdapter(list, this);

            ListView lvMyOrder = (ListView)findViewById(R.id.listviewmyorder);
            lvMyOrder.setAdapter(adapter);

            priceReceiver.setText(String.valueOf(totalP));
            totalP2 = totalP;
            totalP = 0;
        }
        else if (dt.size() == 0){
            priceReceiver.setText("0");
            noOrderYet.setVisibility(View.VISIBLE);
        }
    }

    public void clickInputAddress(View view) {
        Intent intent = new Intent(this, DestPicker.class);
        this.startActivity(intent);
    }

    public void clickPayNow(View view) {
        if(dt.size() > 0) {
            totalP = 0;
            for (int i = 0; i < dt.size(); i++) {
                totalP += tp.get(i);
            }
            totalP2 = totalP;
            priceReceiver.setText(String.valueOf(totalP));
        }

        if (dt.size() == 0) {
            priceReceiver.setText(String.valueOf(0));
            AlertDialog alertDialog = new AlertDialog.Builder(MyOrder.this).create();
            alertDialog.setTitle("No Orders Yet");
            alertDialog.setMessage("You Haven't Made Any Orders");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else if (totalP2 > balancectr) {
                AlertDialog alertDialog = new AlertDialog.Builder(MyOrder.this).create();
                alertDialog.setTitle("You Don't Have Enough EzyMoney Balance");
                alertDialog.setMessage("Please Top Up Your EzyMoney to continue with your purchase.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
        }
        else if (totalP2 <= balancectr) {
            UpdateTopup();
            totalP = 0;
            Intent intent = new Intent(this, DestPicker.class);
            this.startActivity(intent);
        }
    }

    public void UpdateTopup() {
        balancectr = balancectr - totalP2;
        TopupData.saveTopupBal(getApplicationContext(), balancectr);
    }
}