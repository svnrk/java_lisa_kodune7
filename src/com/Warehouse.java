package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Warehouse {
    static private List<String> shelves = new ArrayList<>();
    static private List<Product> prod = new ArrayList<>();
    static private final String DEFAULT_FILE_NAME = "PRODUCT_LIST.csv";

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

    public String convertToCSV(String[] data) {
        return String.join(",", data);
    }

    public void createCSVFromProductList() {
        createCSVFromProductList(DEFAULT_FILE_NAME);
    }

    public void createCSVFromProductList(String fileName) {
        List<String[]> dataLines = new ArrayList<>();
        for (Product p : prod) dataLines.add(p.getDataLine());

        File csvOutputFile = new File(fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
        catch (IOException e){
            System.out.println("IOException");
        }
    }

    public void createProductsFromCSV(String fileName){

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                createProductFromString(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createProductFromString(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
            prod.add(new Product(values.get(0), Integer.parseInt(values.get(1)), values.get(2)));
        }
    }
}
