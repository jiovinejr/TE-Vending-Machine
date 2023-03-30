package com.techelevator.models;

import java.math.BigDecimal;

public class Munchy extends Product{

    private int inventory;

    public Munchy(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, "Munchy");
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
