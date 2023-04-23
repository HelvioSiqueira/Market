package com.helvio.market.presentation.ui.cart;

import android.util.Log;

import com.helvio.market.domain.model.CartProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Classe que para armazenar e operar sobre os produtos adicionados no carrinho
// ela é instancia da na MainActivity
public class CartProductHelper {

    public static List<CartProduct> cartProducts = new ArrayList<>();

    public CartProductHelper() {
    }

    public static List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public static void addProductInCart(CartProduct product) {

        Optional<CartProduct> find = cartProducts.stream().filter(p -> p.getId() == product.getId()).findAny();

        if (!find.isPresent()) {
            cartProducts.add(product);
        } else {
               find.get().addOne();
               Log.d("HSV", cartProducts.toString());
        }
    }

    public static void removeProductInCart(CartProduct product) {

        Optional<CartProduct> find = cartProducts.stream().filter(p -> p.getId() == product.getId()).findAny();

        if (find.isPresent()) {
            if (find.get().getCountInCart() == 1) {
                cartProducts.remove(product);
            } else if (find.get().getCountInCart() > 1) {
                find.get().removeOne();
            }
        }
    }
    
    public static Double getTotalPriceInCart(){
        double total = 0.0;

        for(CartProduct product: cartProducts){
            total += product.getPrice() * product.getCountInCart();
        }

        return total;
    }
}
