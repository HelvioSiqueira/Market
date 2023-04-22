package com.helvio.market.domain.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CartProduct {

    public int id;
    public String title;
    public int price;
    public String brand;
    public String thumbnail;
    public int countInCart;

    public CartProduct(int id, String title, int price, String brand, String thumbnail, int countInCart) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.brand = brand;
        this.thumbnail = thumbnail;
        this.countInCart = countInCart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getCountInCart() {
        return countInCart;
    }

    public void setCountInCart(int countInCart) {
        this.countInCart = countInCart;
    }

    public void addOne(){
        countInCart++;
    }

    public void removeOne(){
        if(countInCart > 0){
            countInCart--;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return title + countInCart;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
