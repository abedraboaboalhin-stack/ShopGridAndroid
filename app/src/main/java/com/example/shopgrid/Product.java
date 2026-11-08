package com.example.shopgrid;

import java.util.Objects;

public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final int imageResId;

    public Product(int id, String name, double price, int imageResId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getImageResId() { return imageResId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product other = (Product) o;
        return id == other.id
                && Double.compare(price, other.price) == 0
                && imageResId == other.imageResId
                && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, imageResId);
    }
}
