package com.uts.yulianamuksin2201800091;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {

    private  static final String TAG = "OrderHistory";

    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderhistory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.listviewpayment);
        mDatabaseHelper = new DatabaseHelper(this);
        
        populateListView();
    }

    private void populateListView() {
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }


    public void clickMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}