package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Product{

    private int inventory;

    public Candy(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, "Candy");
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
