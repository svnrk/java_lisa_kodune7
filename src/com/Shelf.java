package com;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private String shelfId;
    private List<Product> prod = new ArrayList<>();

    public Shelf(String shelfId, Product prod) {
        this.shelfId = shelfId;
        this.prod.add(prod);
    }


}
