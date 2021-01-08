package com.uts.yulianamuksin2201800091;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MenuDetailActivity extends AppCompatActivity {
    public static final String ITEM_ID = "itemId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int itemId = (Integer)getIntent().getExtras().get(ITEM_ID);
        String menuName = Menu.MENUS[itemId].getName();

        if(menuName == "Drinks") {
            Intent intent = new Intent(this, DetailDrinks.class);
            this.startActivity(intent);
        }
        else if(menuName == "Snacks"){
            Intent intent = new Intent(this, DetailSnacks.class);
            this.startActivity(intent);
        }
        else if(menuName == "Foods"){
            Intent intent = new Intent(this, DetailFoods.class);
            this.startActivity(intent);
        }
        else if(menuName == "Top Up"){
            Intent intent = new Intent(this, TopUp.class);
            this.startActivity(intent);
        }
        else if(menuName == "Map"){
            Intent intent = new Intent(this, Map.class);
            this.startActivity(intent);
        }
        else if(menuName == "Restaurant List"){
            Intent intent = new Intent(this, RestaurantList.class);
            this.startActivity(intent);
        }
        else if(menuName == "Order History"){
            Intent intent = new Intent(this, OrderHistory.class);
            this.startActivity(intent);
        }
    }
}
