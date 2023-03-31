package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends Product{

    private int inventory;



    public Gum(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, "Gum");
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
