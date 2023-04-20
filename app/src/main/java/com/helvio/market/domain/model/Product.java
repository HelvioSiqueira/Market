package com.helvio.market.domain.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Product{
    public int id;
    public String title;
    public String description;
    public int price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public String brand;
    public String category;
    public String thumbnail;
    public ArrayList<String> images;

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}