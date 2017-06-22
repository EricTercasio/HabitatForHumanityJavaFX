package model;

import java.util.ArrayList;

/**
 * Created by Kitcatski on 6/18/2017.
 */
public class InventoryBag {
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<>();

    public InventoryBag(){

    }
    public void add(InventoryItem inventoryItem){
        inventoryItems.add(inventoryItem);
    }
    public void remove(InventoryItem inventoryItem){
        for (int i = 0; i < inventoryItems.size(); i++){
            inventoryItems.remove(inventoryItem);
        }
    }
//    public InventoryItem findById(int id){
//        for (int i = 0; i < inventoryItems.size(); i++){
//            if(inventoryItems.get(i).getId() == id){
//                return inventoryItems.get(i);
//            }
//        }
//        return null;
//    }
    public void display(){
        for (int i = 0; i < inventoryItems.size(); i++){
            System.out.print(inventoryItems.get(i).toString());
        }
    }
}
