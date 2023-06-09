package com.techelevator.controller;

import com.techelevator.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class ReadDataFile {
  //A2,Ginger Ayle,1.85,Drink

   private String catering = "catering.csv";
   private String catering1 = "catering1.csv";
   private String dataPath = "";

    public List<Product> loadFile() {
        File dataFile = new File(catering1);
        List<Product> importedProductsList = new ArrayList<>();


        try (Scanner dataFileScanner = new Scanner(dataFile)) {
            while (dataFileScanner.hasNextLine()) {
                Product currentProduct = null;
                String currentLine = dataFileScanner.nextLine();
                String[] currentLineSplit = currentLine.split(",");
                if (currentLineSplit.length == 4) {
                    String slotIdentifier = currentLineSplit[0];
                    String name = currentLineSplit[1];
                    String price = currentLineSplit[2];
                    BigDecimal priceAsBigDecimal = new BigDecimal(price);
                    String type = currentLineSplit[3];
                    if (type.equals("Candy")) {
                        currentProduct = new Candy(slotIdentifier, name, priceAsBigDecimal);
                        }
                    else if (type.equals("Drink")) {
                        currentProduct = new Drink(slotIdentifier, name, priceAsBigDecimal);
                    }
                    else if (type.equals("Gum")) {
                        currentProduct = new Gum(slotIdentifier, name, priceAsBigDecimal);
                    }
                    else if (type.equals("Munchy")) {
                        currentProduct = new Munchy(slotIdentifier, name, priceAsBigDecimal);
                    }
                    else {
                        //Bad product;
                    }
                    importedProductsList.add(currentProduct);

                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return importedProductsList;
    }

    public Map<String, Product> loadFileMap() {
        File dataFile = new File(catering1);
        Map<String, Product> importedProductsMap = new HashMap<>();

        try (Scanner dataFileScanner = new Scanner(dataFile)) {
            while (dataFileScanner.hasNextLine()) {
                Product currentProduct = null;
                String currentLine = dataFileScanner.nextLine();
                String[] currentLineSplit = currentLine.split(",");
                if (currentLineSplit.length == 4) {
                    String slotIdentifier = currentLineSplit[0];
                    String name = currentLineSplit[1];
                    String price = currentLineSplit[2];
                    BigDecimal priceAsBigDecimal = new BigDecimal(price);
                    String type = currentLineSplit[3];
                    if (type.equals("Candy")) {
                        currentProduct = new Candy(slotIdentifier, name, priceAsBigDecimal);
                    }
                    else if (type.equals("Drink")) {
                        currentProduct = new Drink(slotIdentifier, name, priceAsBigDecimal);
                    }
                    else if (type.equals("Gum")) {
                        currentProduct = new Gum(slotIdentifier, name, priceAsBigDecimal);
                    }
                    else if (type.equals("Munchy")) {
                        currentProduct = new Munchy(slotIdentifier, name, priceAsBigDecimal);
                    }
                    else {
                        //Bad product;
                    }
                    importedProductsMap.put(slotIdentifier, currentProduct);

                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return importedProductsMap;
    }

}
