package com.uts.yulianamuksin2201800091;

import java.util.ArrayList;

public class OrderData {
    final ArrayList<String> datas = new ArrayList();
    final ArrayList<Integer> totalPrices = new ArrayList();

    private OrderData() {}

    static OrderData getInstance() {
        if( instance == null ) {
            instance = new OrderData();
        }
        return instance;
    }

    private static OrderData instance;
}