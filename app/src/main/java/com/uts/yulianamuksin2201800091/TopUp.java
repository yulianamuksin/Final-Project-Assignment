package com.uts.yulianamuksin2201800091;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TopUp extends AppCompatActivity {

    EditText inputQty;
    TextView balance;
    TextView balance2;
    private int balancectr;
    int qtyint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        balance = (TextView) findViewById(R.id.balance);
        balance2 = (TextView) findViewById(R.id.balance2);

        inputQty = (EditText) findViewById(R.id.input_quantity);
        balancectr = TopupData.getTopupBal(this);
        balance2.setText(String.valueOf(balancectr));
    }

    public void clickTopUp(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(TopUp.this).create();
        String qty = inputQty.getText().toString();
        if(!"".equals(qty) && !"0".equals(qty)) {
            qtyint = Integer.parseInt(qty);
            if(qtyint <= 2000000) {
                alertDialog.setTitle("Success!");
                alertDialog.setMessage("You Received Top Up Amount:\nRp. " + qty);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                UpdateTopup();
            } else if(qtyint > 2000000){
                alertDialog.setTitle("Maximum Top Up Amount is Rp. 2.000.000");
                alertDialog.setMessage("Maximum Top Up Amount is Rp. 2.000.000");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
        else if("".equals(qty) || "0".equals(qty)){
            alertDialog.setTitle("Minimum Top Up Amount is Rp. 1");
            alertDialog.setMessage("Minimum Top Up Amount is Rp. 1");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void UpdateTopup() {
        balancectr += qtyint;
        TopupData.saveTopupBal(getApplicationContext(), balancectr);
        balance2.setText(String.valueOf(balancectr));
    }
}