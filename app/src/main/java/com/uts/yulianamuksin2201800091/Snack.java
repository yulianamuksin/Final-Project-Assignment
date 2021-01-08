package com.uts.yulianamuksin2201800091;

public class Snack {
    private String name;
    private int imageResourceId, price;

    public static final Snack[] SNACKS = {
            new Snack("French Fries\nRp. 10000", 10000, R.drawable.snack1),
            new Snack("Nachos (OUT OF STOCK)\nRp. 20000", 20000, R.drawable.snack2),
            new Snack("Cookie Chips\nRp. 35000", 35000, R.drawable.snack3),
            new Snack("Cream Cookies\nRp. 25000", 25000, R.drawable.snack4)
    };

    private Snack(String name, int price, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }
    public String getName() {
        return name;
    }

    public int getPrice() {return price; }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
