package com.techelevator.controller;

import com.techelevator.models.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFile {
  //A2,Ginger Ayle,1.85,Drink

   private String catering = "catering.txt";
   private String catering1 = "catering1.txt";
   private String dataPath = "";

    public List<Product> loadFile() {
        File dataFile = new File(catering);
        List<Product> importedProductsList = new ArrayList<>();


        return importedProductsList;
    }

}