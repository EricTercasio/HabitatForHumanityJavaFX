package model;

/**
 * Created by Eric on 6/28/2017.
 */
public class Invoice {
    private int invoiceID;
    private int userID;
    private double totalAmount;
    private String items;
    private boolean isPaid;

    public Invoice(int invoiceID, int userID, double totalAmount, String items, boolean isPaid) {
        this.invoiceID = invoiceID;
        this.userID = userID;
        this.totalAmount = totalAmount;
        this.items = items;
        this.isPaid = isPaid;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
