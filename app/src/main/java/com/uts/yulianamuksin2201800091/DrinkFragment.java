package com.uts.yulianamuksin2201800091;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;

public class DrinkFragment extends Fragment {
    public static final String ITEM_TYPE = "itemType";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView menuRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_menu, container, false);

        String[] drinkNames = new String[Drink.drinks.length];

        for (int i = 0; i < drinkNames.length; i++) {
            drinkNames[i] = Drink.drinks[i].getName();
        }

        int[] drinkPrices = new int[Drink.drinks.length];

        for (int i = 0; i < drinkPrices.length; i++) {
            drinkPrices[i] = Drink.drinks[i].getPrice();
        }

        int[] drinkImages = new int[Drink.drinks.length];

        for (int i = 0; i < drinkImages.length; i++) {
            drinkImages[i] = Drink.drinks[i].getImageResourceId();
        }

        ImageAdapter adapter = new ImageAdapter(drinkNames, drinkPrices, drinkImages);
        menuRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        menuRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new ImageAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ItemQuantity.class);
                intent.putExtra(ItemQuantity.ITEM_ID, position);
                intent.putExtra(DrinkFragment.ITEM_TYPE, "drinks");
                getActivity().startActivity(intent);
            }
        });

        return menuRecycler;
    }
}
