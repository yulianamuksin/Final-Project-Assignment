package com.uts.yulianamuksin2201800091;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FoodFragment extends Fragment {

    public static final String ITEM_TYPE = "itemType";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView menuRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_menu, container, false);

        String[] foodNames = new String[Food.FOODS.length];

        for (int i = 0; i < foodNames.length; i++) {
            foodNames[i] = Food.FOODS[i].getName();
        }

        int[] foodPrices = new int[Food.FOODS.length];

        for (int i = 0; i< foodPrices.length; i++){
            foodPrices[i] = Food.FOODS[i].getPrice();
        }

        int[] foodImages = new int[Food.FOODS.length];

        for (int i = 0; i < foodImages.length; i++) {
            foodImages[i] = Food.FOODS[i].getImageResourceId();
        }

        ImageAdapter adapter = new ImageAdapter(foodNames, foodPrices, foodImages);
        menuRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        menuRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new ImageAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ItemQuantity.class);
                intent.putExtra(ItemQuantity.ITEM_ID, position);
                intent.putExtra(FoodFragment.ITEM_TYPE, "foods");
                getActivity().startActivity(intent);
            }
        });

        return menuRecycler;
    }
}
