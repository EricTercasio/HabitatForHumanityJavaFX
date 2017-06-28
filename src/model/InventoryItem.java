package model;

import java.awt.*;

/**
 * Created by Kitcatski on 6/18/2017.
 */
public class InventoryItem {
    private int productID;
    private String type;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private int quantity;

    public InventoryItem(int productID, String type, String name, double price, String description, String imageUrl, int quantity) {
        this.productID = productID;
        this.type = type;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                 + '\'' +
                '}';
    }
}
