package model;

/**
 * Created by Eric on 6/18/2017.
 */
public class Guest {
    private ShoppingCart shoppingCart;

    public Guest(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}
