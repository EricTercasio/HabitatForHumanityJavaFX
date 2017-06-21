package model;

/**
 * Created by Kitcatski on 6/18/2017.
 */
public class Customer extends Person{
    private ShoppingCart shoppingCart;

    public Customer(int id, String firstName, String lastName, String username, String password, String phoneNumber, String street, String city, String state, String zip, String email) {
        super(id, firstName, lastName, username, password, phoneNumber, street, city, state, zip, email);
        shoppingCart = new ShoppingCart();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "shoppingCart=" + shoppingCart +
                '}';
    }
}
