package com.uts.yulianamuksin2201800091;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;

public class MenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView menuRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_menu, container, false);

        String[] menuNames = new String[Menu.MENUS.length];

        for (int i = 0; i < menuNames.length; i++) {
            menuNames[i] = Menu.MENUS[i].getName();
        }

        int[] menuPrices = new int[Menu.MENUS.length];

        for (int i = 0; i < menuPrices.length; i++) {
            menuPrices[i] = Menu.MENUS[i].getPrice();
        }

        int[] menuImages = new int[Menu.MENUS.length];

        for (int i = 0; i < menuImages.length; i++) {
            menuImages[i] = Menu.MENUS[i].getImageResourceId();
        }

        ImageAdapter adapter = new ImageAdapter(menuNames, menuPrices, menuImages);
        menuRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        menuRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new ImageAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), MenuDetailActivity.class);
                intent.putExtra(MenuDetailActivity.ITEM_ID, position);
                getActivity().startActivity(intent);
            }
        });

        return menuRecycler;
    }
}