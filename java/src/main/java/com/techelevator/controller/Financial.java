package com.techelevator.controller;

import java.math.BigDecimal;

public class Financial {

    public static BigDecimal applyDiscount(BigDecimal price) {
        BigDecimal BOGODO = new BigDecimal("1.00");
        BigDecimal discountedPrice = new BigDecimal("0.00");
        if(price.compareTo(BOGODO) >= 0) {
            discountedPrice = price.subtract(BOGODO);
        } else {
            discountedPrice = price;
        } return discountedPrice;
    }



    public static String giveChange(BigDecimal moneyProvided) {
    BigDecimal quarter = new BigDecimal("0.25");
    BigDecimal dollar = new BigDecimal("1.00");
    BigDecimal dime = new BigDecimal("0.10");
    BigDecimal nickle = new BigDecimal("0.05");
    int dollarsGiven = 0;
    int quartersGiven = 0;
    int dimesGiven = 0;
    int nicklesGiven = 0;
    String resultString = "";


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
        resultString = "Dispensing: " + dollarsGiven + " dollars, " + quartersGiven + " quarters, "
                + dimesGiven + " dimes, and " + nicklesGiven + " nickles.";
        return resultString;
    }

}
