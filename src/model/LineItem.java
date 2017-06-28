package model;

/**
 * Created by Kitcatski on 6/26/2017.
 */
public class LineItem {
    private int id;
    private int productID;
    private int quantity;
    public LineItem(int id, int productID, int quantity) {
        this.id = id;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", productID='" + productID + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
