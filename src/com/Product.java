package com;

import java.util.Objects;

public class Product {
    private String name;
    private Integer size;
    private String shelfId;

    public Product(String name, Integer size) {
        this.name = name;
        this.size = size;
        this.shelfId = null;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfId() {
        return shelfId;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) &&
                size.equals(product.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
