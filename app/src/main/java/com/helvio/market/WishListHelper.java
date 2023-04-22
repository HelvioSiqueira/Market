package com.helvio.market;

import com.helvio.market.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class WishListHelper {

    public static List<Product> wishProductsList = new ArrayList<>();

    public WishListHelper() {
    }

    public static List<Product> getWishProductsList() {
        return wishProductsList;
    }

    public static void addProductInWishList(Product product) {
        wishProductsList.add(product);
    }
}
