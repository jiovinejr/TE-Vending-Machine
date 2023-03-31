package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends Product{

    private int inventory;


    public Drink(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, "Drink");
        inventory = super.getInventory();
    }


    @Override
    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }



    }

