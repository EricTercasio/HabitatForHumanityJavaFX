package model;

import java.awt.*;

/**
 * Created by Kitcatski on 6/18/2017.
 */
public class InventoryItem {
    private final int id;
    private int quantity;
    private Image image;
    private String name;
    private double price;
    private String description;
    private String size;
    private static int idInt = 0;

    public InventoryItem(Image image, String name, double price, String description, String size) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
        this.size = size;
        id = idInt++;
    }
    public int getId(){
        return id;
    }
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static int getIdInt() {
        return idInt;
    }

    public static void setIdInt(int idInt) {
        InventoryItem.idInt = idInt;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
