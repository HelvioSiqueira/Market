package com.helvio.market.presentation.ui.wishlist;

import com.helvio.market.domain.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Classe que para armazenar e operar sobre os produtos adicionados na WishList
// ela é instancia da na MainActivity
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

    public static void removeProductFromWishList(Product product) {
        Optional<Product> find = wishProductsList.stream().filter(p -> p.getId() == product.getId()).findAny();
        find.ifPresent(value -> wishProductsList.remove(value));
    }
}
