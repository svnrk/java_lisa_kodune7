package com;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    static List<String> shelves = new ArrayList<>();
    static List<Product> prod = new ArrayList<>();


    public void receive(Product prod, String loc){
        if (!shelves.contains(loc)) shelves.add(loc);
        prod.setShelfId(loc);
        Warehouse.prod.add(prod);
    }

    public Integer getItemCount(Product prod){
       int itemCount = 0;
       for (Product p : Warehouse.prod){
           if (prod.equals(p)) itemCount++;
       }
       return itemCount;
    }

    public String dispatch(Product prod){
        String shelfId = null;

        for (Product p : Warehouse.prod){
            if (prod.equals(p)) {
                shelfId= p.getShelfId();
                Warehouse.prod.remove(p);
                break;
            }
        }

        return shelfId;
    }
}
