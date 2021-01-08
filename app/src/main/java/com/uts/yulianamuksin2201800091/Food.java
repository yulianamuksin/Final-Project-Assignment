package com.uts.yulianamuksin2201800091;

public class Food {
    private String name;
    private int imageResourceId, price;

    public static final Food[] FOODS = {
            new Food("Satay Rice\nRp. 25000", 25000, R.drawable.food1),
            new Food("Padang Rice\nRp. 35000", 35000, R.drawable.food2),
            new Food("Rendang\nRp. 45000", 45000, R.drawable.food3),
            new Food("Kalasan Rice\nRp. 55000", 55000, R.drawable.food4)
    };

    private Food(String name, int price, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }
    public String getName() {
        return name;
    }

    public int getPrice() { return price; }

    public int getImageResourceId() { return imageResourceId; }
}
