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

        ReadDataFile fileInput = new ReadDataFile();
        List<Product> products = fileInput.loadFile();

        BigDecimal BOGODO = new BigDecimal("1.00");
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
                        boolean isSufficientFunds = true;
                        for (Product product : products) {
                            if (product.getInventory() == 0) {
                                System.out.println("**ITEM NO LONGER AVAILABLE**");
                            }
                            if (product.getPrice().compareTo(moneyProvided) == 1) {
                                isSufficientFunds = false;
                            } else if (itemSelected.equals(product.getSlotIdentifier())) {
                                    counter++;
                                    doesProductExist = true;
                                    BigDecimal afterDiscount = new BigDecimal("0.00");
                                    if (counter % 2 == 0) {
                                        afterDiscount = product.applyDiscount();
                                        System.out.println();
                                    }
                                    System.out.println();
                                    System.out.println(product.getName() + " " + product.getPrice());
                                    System.out.println();
                                    if (afterDiscount.equals(new BigDecimal("0.00"))) {
                                        moneyProvided = moneyProvided.subtract(product.getPrice());
                                    } else {
                                        moneyProvided = moneyProvided.subtract(afterDiscount);
                                        System.out.println("**ONE DOLLAR OFF SALE**");
                                    }
                                    UserOutput.displayMessage(product.message(product.getType()));
                                    System.out.println("Current money provided: " + moneyProvided);
                                    product.setInventory(product.getInventory() - 1);
                                    // check if user input relates to a product in our list
                                }
                        }
                        if (!doesProductExist) {
                            System.out.println("Insufficient Funds...");
                        }

                        if (!doesProductExist) {
                            System.out.println("Choose again!");
                            System.out.println();
                        }
                    }
                        if (option.equals("F")) {
                            System.out.println("Thank you!");
                           // BigDecimal moneyLeft = new BigDecimal("0.00");
                            //moneyProvided;
                            BigDecimal dollar = new BigDecimal("1.00");
                            BigDecimal quarter = new BigDecimal("0.25");
                            BigDecimal dime = new BigDecimal("0.10");
                            BigDecimal nickle = new BigDecimal("0.05");
                            int dollarsGiven = 0;
                            int quartersGiven = 0;
                            int dimesGiven = 0;
                            int nicklesGiven = 0;

                            while (moneyProvided.compareTo(dollar) == 1 || moneyProvided.compareTo(dollar) == 0) {
                                dollarsGiven++;
                                moneyProvided = moneyProvided.subtract(dollar);
                            }
                            while (moneyProvided.compareTo(quarter) == 1 || moneyProvided.compareTo(quarter) == 0) {
                                quartersGiven++;
                                moneyProvided = moneyProvided.subtract(quarter);
                            }
                            while (moneyProvided.compareTo(dime) == 1 || moneyProvided.compareTo(dime) == 0) {
                                dimesGiven++;
                                moneyProvided = moneyProvided.subtract(dime);
                            }
                            while (moneyProvided.compareTo(nickle) == 1 || moneyProvided.compareTo(nickle) == 0) {
                                nicklesGiven++;
                                moneyProvided = moneyProvided.subtract(nickle);

                            }
                            System.out.println("Dispensing: " + dollarsGiven + " dollars, " + quartersGiven + " quarters, "
                                    + dimesGiven + " dimes, and " + nicklesGiven + " nickles.");
                            purchaseFlag = false;

                        }

                    }

            } else if (choice.equals("exit")) {
                System.out.println("Good bye");
                break;
            }
        }

    }


}


