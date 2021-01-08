package com.uts.yulianamuksin2201800091;

public class Menu {
    private String name;
    private int imageResourceId ,price;

    public static final Menu[] MENUS = {
            new Menu("Drinks", 0, R.drawable.drinks),
            new Menu("Snacks", 0, R.drawable.snacks),
            new Menu("Foods", 0, R.drawable.foods),
            new Menu("Top Up", 0, R.drawable.topup),
            new Menu("Order History", 0, R.drawable.orderhistimg),
            new Menu("Map", 0, R.drawable.map),
            new Menu("Restaurant List", 0, R.drawable.restolist),
    };

    private Menu(String name, int price, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
