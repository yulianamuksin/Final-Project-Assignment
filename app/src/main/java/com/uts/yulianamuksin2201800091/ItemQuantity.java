package com.uts.yulianamuksin2201800091;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemQuantity extends AppCompatActivity {
    public static final String ITEM_ID = "itemId";
    public static final String ITEM_TYPE = "itemType";

    EditText inputQty;
    String itemName;
    int itemId, itemPrice, itemImage;
    String orderItem = "";
    ArrayList<String> dt = OrderData.getInstance().datas;
    ArrayList<Integer> tp = OrderData.getInstance().totalPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemqty);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String itemType = getIntent().getStringExtra(ITEM_TYPE);

        if(itemType.equals("drinks")) {
            itemId = (Integer)getIntent().getExtras().get(ITEM_ID);
            itemName = Drink.drinks[itemId].getName();
            TextView textView = (TextView)findViewById(R.id.itm_text);
            textView.setText(itemName);
            itemPrice = Drink.drinks[itemId].getPrice();
            itemImage = Drink.drinks[itemId].getImageResourceId();
            ImageView imageView = (ImageView)findViewById(R.id.itm_image);
            imageView.setImageDrawable(ContextCompat.getDrawable(this, itemImage));
            imageView.setContentDescription(itemName);
        }

        else if(itemType.equals("foods")){
            itemId = (Integer)getIntent().getExtras().get(ITEM_ID);
            itemName = Food.FOODS[itemId].getName();
            TextView textView = (TextView)findViewById(R.id.itm_text);
            textView.setText(itemName);
            itemPrice = Food.FOODS[itemId].getPrice();
            itemImage = Food.FOODS[itemId].getImageResourceId();
            ImageView imageView = (ImageView)findViewById(R.id.itm_image);
            imageView.setImageDrawable(ContextCompat.getDrawable(this, itemImage));
            imageView.setContentDescription(itemName);
        }

        else if(itemType.equals("snacks")){
            itemId = (Integer)getIntent().getExtras().get(ITEM_ID);
            itemName = Snack.SNACKS[itemId].getName();
            TextView textView = (TextView)findViewById(R.id.itm_text);
            textView.setText(itemName);
            itemPrice = Snack.SNACKS[itemId].getPrice();
            itemImage = Snack.SNACKS[itemId].getImageResourceId();
            ImageView imageView = (ImageView)findViewById(R.id.itm_image);
            imageView.setImageDrawable(ContextCompat.getDrawable(this, itemImage));
            imageView.setContentDescription(itemName);
        }

        inputQty = (EditText) findViewById(R.id.input_quantity);
    }

    public void clickAddToCart(View view) {
        String qty = inputQty.getText().toString();
        int qty2 = 0;

        if(itemName.equals("Nachos (OUT OF STOCK)\nRp. 20000")){
            AlertDialog alertDialog = new AlertDialog.Builder(ItemQuantity.this).create();
            alertDialog.setTitle("Item Out of Stock");
            alertDialog.setMessage("This item is Out of Stock. Please choose other available menus.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

        else if(!"".equals(qty)) {
            qty2 = Integer.parseInt(qty);
            orderItem = itemName + " x " + qty2;
            dt.add(orderItem);

            int tlp = itemPrice * qty2;
            tp.add(tlp);

            AlertDialog alertDialog = new AlertDialog.Builder(ItemQuantity.this).create();
            alertDialog.setTitle("Success!");
            alertDialog.setMessage("Item Added to Cart");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else if("".equals(qty)){
            AlertDialog alertDialog = new AlertDialog.Builder(ItemQuantity.this).create();
            alertDialog.setTitle("Please Input Quantity");
            alertDialog.setMessage("Quantity Cannot Be Empty");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void clickOrderMore(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    public void clickMyOrder(View view) {

        Intent intent = new Intent(this, MyOrder.class);
        intent.putExtra("integer-array", (ArrayList<Integer>) tp);
        startActivity(intent);
    }
}