package com.uts.yulianamuksin2201800091;

public class Drink {
    private String name;
    private int imageResourceId, price;

    public static final Drink[] drinks = {
            new Drink("Lemonade\nRp. 15000", 15000, R.drawable.drink1),
            new Drink("Choco Shake\nRp. 20000", 20000, R.drawable.drink2),
            new Drink("Boba Milk\nRp. 25000", 25000, R.drawable.drink3),
            new Drink("Cherry Soda\nRp. 30000", 30000, R.drawable.drink4)
    };

    private Drink(String name, int price, int imageResourceId) {
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