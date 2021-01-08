package com.uts.yulianamuksin2201800091;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SnackFragment extends Fragment {

    public static final String ITEM_TYPE = "itemType";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView menuRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_menu, container, false);

        String[] snackNames = new String[Snack.SNACKS.length];

        for (int i = 0; i < snackNames.length; i++) {
            snackNames[i] = Snack.SNACKS[i].getName();
        }

        int[] snackPrices = new int[Food.FOODS.length];

        for (int i = 0; i< snackPrices.length; i++){
            snackPrices[i] = Snack.SNACKS[i].getPrice();
        }

        int[] snackImages = new int[Snack.SNACKS.length];

        for (int i = 0; i < snackImages.length; i++) {
            snackImages[i] = Snack.SNACKS[i].getImageResourceId();
        }

        ImageAdapter adapter = new ImageAdapter(snackNames, snackPrices, snackImages);
        menuRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        menuRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new ImageAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ItemQuantity.class);
                intent.putExtra(ItemQuantity.ITEM_ID, position);
                intent.putExtra(SnackFragment.ITEM_TYPE, "snacks");
                getActivity().startActivity(intent);
            }
        });

        return menuRecycler;
    }
}
