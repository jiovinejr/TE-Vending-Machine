package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Product {
    private String slotIdentifier;
    private String name;
    private BigDecimal price;
    private String type;
    private final int MAXIMUM_INVENTORY = 6;
    private int inventory = MAXIMUM_INVENTORY;

    public Product(String slotIdentifier, String name, BigDecimal price, String type) {
        this.slotIdentifier = slotIdentifier;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getSlotIdentifier() {
        return slotIdentifier;
    }

    public void setSlotIdentifier(String slotIdentifier) {
        this.slotIdentifier = slotIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return  slotIdentifier + " " + name + " " + price +
                " " + type;
    }
}
