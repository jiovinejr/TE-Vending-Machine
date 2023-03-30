package com.techelevator.application;

import com.techelevator.controller.ReadDataFile;
import com.techelevator.models.Product;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class VendingMachine 
{
    public void run()
    {
        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
                // display the vending machine slots
                ReadDataFile fileInput = new ReadDataFile();
                List<Product> products = fileInput.loadFile();
                for (Product product : products) {
                    System.out.println(product + " " + product.getInventory() + " left.");
                }
            }
            else if(choice.equals("purchase"))
            {
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
                        System.out.print("Please enter amount in whole dollars: ");
                        String dollarAmountReceived = purchaseOption.nextLine();
                        BigDecimal dollarAmount = new BigDecimal(dollarAmountReceived);
                        moneyProvided = moneyProvided.add(dollarAmount);
                    }

                }
            }
            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }
    }
    
}
