package com.techelevator.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

public class Audit {
    public static void log(String message, String slot, BigDecimal money, BigDecimal moneyLeft){

            File auditFile = new File("audit.txt");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        Locale usa = new Locale("en", "US");
        Currency dollar = Currency.getInstance(usa);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
        String columnFormat = "%-18s %2s";

            if(auditFile.exists()){
                try(PrintWriter writer = new PrintWriter(new FileOutputStream(auditFile, true))){
                    writer.write(dtf.format(now));
                    writer.printf(columnFormat, " " + message, slot);
                    writer.printf("%8s"," "+ dollarFormat.format(money));
                    writer.printf("%8s"," " + dollarFormat.format(moneyLeft) + "\n");
                   // writer.printf(columnFormat, message, slot, money, moneyLeft);

                } catch (FileNotFoundException e) {
                    System.out.println("ERROR");
                    //e.printStackTrace();
                }
            }
            else {
                try(PrintWriter writer = new PrintWriter(auditFile)) {
                    writer.write(dtf.format(now));
                    writer.printf(columnFormat, " " + message, slot);
                    writer.printf("%8s"," "+ dollarFormat.format(money));
                    writer.printf("%8s"," " + dollarFormat.format(moneyLeft) + "\n");
             //       writer.printf(columnFormat, message, slot, money, moneyLeft);
                } catch (FileNotFoundException e) {
                    System.out.println("ERROR");
                   // e.printStackTrace();
                }
            }
}
}