package com.techelevator.application;

import com.techelevator.controller.Audit;
import com.techelevator.controller.Financial;
import com.techelevator.controller.ReadDataFile;
import com.techelevator.models.Product;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    public void run() {

        ReadDataFile fileInput = new ReadDataFile();
        List<Product> products = fileInput.loadFile();
        ReadDataFile inputMap = new ReadDataFile();
        Map<String, Product> productsMap = inputMap.loadFileMap();

        int counter = 0;
        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();


            if (choice.equals("display")) {
                // display the vending machine slots

                for (Product product : products) {
                    System.out.println(product + " " + product.getInventory() + " left.");
                }
            } else if (choice.equals("purchase")) {
                BigDecimal moneyProvided = new BigDecimal("0.00");

                boolean purchaseFlag = true;
                while (purchaseFlag) {
                    // make a purchase
                    System.out.println("(M) Feed Money");
                    System.out.println("(S) Select Item");
                    System.out.println("(F) Finish Transaction");

                    System.out.println();
                    System.out.print("Current Money Provided: " + moneyProvided);
                    System.out.println();

                    Scanner purchaseOption = new Scanner(System.in);
                    System.out.print("Please choose an option: ");
                    String selectedOption = purchaseOption.nextLine();
                    String option = selectedOption.trim().toUpperCase();

                    if (option.equals("M")) {
                        System.out.println();
                        System.out.print("Please enter amount in whole dollars: ");
                        String dollarAmountReceived = purchaseOption.nextLine();
                        System.out.println("***************************************************");
                        BigDecimal dollarAmount = new BigDecimal(dollarAmountReceived);
                        moneyProvided = moneyProvided.add(dollarAmount);
                        Audit.log("MONEY FED: ", "  ", dollarAmount, moneyProvided);
                    }
                    if (option.equals("S")) {
                        System.out.println("***************************************************");
                        //  while (true) {
                        for (Product product : products) {
                            System.out.println(product + " " + product.getInventory() + " left.");
                        }
                        System.out.println();
                        System.out.print("Please select an Item: ");
                        String itemInput = purchaseOption.nextLine();
                        //takes care of any spaces and lowercase
                        String itemSelected = itemInput.trim().toUpperCase();
                        if (!productsMap.containsKey(itemSelected)) {
                            System.out.println("***************************************************");
                            System.out.println("Item does not exist. Choose again!");
                            System.out.println();
                        } else {
                            System.out.println("***************************************************");
                            boolean isSufficientFunds = false;
                            for (Product product : products) {
                                if (product.getPrice().compareTo(moneyProvided) == -1) {//price > money provided.
                                    isSufficientFunds = true;
                                    if (product.getInventory() == 0) {
                                        System.out.println("**ITEM NO LONGER AVAILABLE**");
                                        break;
                                    } else if (itemSelected.equals(product.getSlotIdentifier())) {
                                        counter++;
                                        BigDecimal afterDiscount = new BigDecimal("0.00");
                                        if (counter % 2 == 0) {
                                            afterDiscount = Financial.applyDiscount(product.getPrice());
                                        }
                                        System.out.println(product.getName() + " " + product.getPrice());
                                        if (afterDiscount.equals(new BigDecimal("0.00"))) {
                                            moneyProvided = moneyProvided.subtract(product.getPrice());
                                        } else {
                                            moneyProvided = moneyProvided.subtract(afterDiscount);
                                            System.out.println();
                                            System.out.println("**ONE DOLLAR OFF SALE**");
                                            System.out.println();
                                            System.out.println(product.getName() + " " + afterDiscount);
                                        }
                                        UserOutput.displayMessage(product.message(product.getType()));
                                        System.out.println("Current money provided: " + moneyProvided);
                                        System.out.println("***************************************************");
                                        product.setInventory(product.getInventory() - 1);
                                        Audit.log(product.getName(), product.getSlotIdentifier(), moneyProvided.add(product.getPrice()), moneyProvided);

                                    }
                                }
                            } if (!isSufficientFunds) {
                                System.out.println("Insufficient Funds...");
                                System.out.println("***************************************************");

                            }
                        }
                    }
                    if (option.equals("F")) {
                        BigDecimal moneyLeft = new BigDecimal("0.00");
                        moneyLeft = moneyProvided;
                        String change = Financial.giveChange(moneyProvided);
                        System.out.println(change);
                        moneyProvided = new BigDecimal("0.00");
                        counter = 0;
                        purchaseFlag = false;
                        Audit.log("CHANGE: ","  ", moneyLeft, moneyProvided);
                        System.out.println("Thank you!");
                    }


                }

            } else if (choice.equals("exit")) {
                System.out.println("Good bye");
                break;
            }
        }

    }


}


