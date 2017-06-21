package model;

import java.util.ArrayList;

/**
 * Created by Kitcatski on 6/18/2017.
 */
public class ShoppingCart {
    private ArrayList<InventoryItem> items;

    public ShoppingCart(){
        items = new ArrayList<>();
    }
    public void add(InventoryItem item){
        items.add(item);
    }
    public void remove(InventoryItem item){
        items.remove(item);
    }
    public void display(){
        for(int i = 0; i < items.size(); i++){
            System.out.println(items.get(i).toString());
        }
    }
    public void empty(){
        for(int i = 0; i < items.size(); i++){
            items.remove(i);
        }
    }

}
