package com.techelevator.application;

import com.techelevator.controller.ReadDataFile;
import com.techelevator.models.Product;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    public void run() {

        BigDecimal BOGODO = new BigDecimal("1.00");

        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            ReadDataFile fileInput = new ReadDataFile();
            List<Product> products = fileInput.loadFile();

            if (choice.equals("display")) {
                // display the vending machine slots

                for (Product product : products) {
                    System.out.println(product + " " + product.getInventory() + " left.");
                }
            } else if (choice.equals("purchase")) {
                BigDecimal moneyProvided = new BigDecimal("0.00");

                while (true) {
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
                        BigDecimal dollarAmount = new BigDecimal(dollarAmountReceived);
                        moneyProvided = moneyProvided.add(dollarAmount);
                    }
                    if (option.equals("S")) {
                        //  while (true) {
                        for (Product product : products) {
                            System.out.println(product + " " + product.getInventory() + " left.");
                        }
                        System.out.println();
                        System.out.print("Please select an Item: ");
                        String itemInput = purchaseOption.nextLine();
                        //takes care of any spaces and lowercase
                        String itemSelected = itemInput.trim().toUpperCase();
                        boolean doesProductExist = false;
                        int counter = 0;
                        for (Product product : products) {
                            if (product.getInventory() == 0) {
                                System.out.println("**ITEM NO LONGER AVAILABLE**");
                            }
                            if (itemSelected.equals(product.getSlotIdentifier())) {
                                counter++;
                                doesProductExist = true;
                                boolean hasDiscount = false;
                                if (counter % 2 == 0) {
                                    hasDiscount = true;
                                    if (hasDiscount) {
                                        System.out.println("**ONE DOLLAR OFF SALE**");
                                        System.out.println();
                                        System.out.println(product.getName() + " " + product.getPrice().subtract(BOGODO));
                                        System.out.println();
                                        moneyProvided = moneyProvided.subtract(product.getPrice().subtract(BOGODO));
                                    }
                                    } else if (!hasDiscount) {
                                        System.out.println();
                                        System.out.println(product.getName() + " " + product.getPrice());
                                        System.out.println();
                                        moneyProvided = moneyProvided.subtract(product.getPrice());
                                    }


                                UserOutput.displayMessage(product.message(product.getType()));
                                    System.out.println("Current money provided: " + moneyProvided);
                                    product.setInventory(product.getInventory() - 1);
                                    // check if user input relates to a product in our list


                                }

                            } if (!doesProductExist) {
                                System.out.println();
                                System.out.println("Item does not exist. Choose again!");
                                System.out.println();
                        }
                    }
                }
            }


                else if (choice.equals("exit")) {
                    // good bye
                    break;
                }
            }
        }

    }
