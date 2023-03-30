package com.techelevator.models;

import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;

public class Drinks extends Product{

    private int inventory;

    public Drinks(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, "Drinks");
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
