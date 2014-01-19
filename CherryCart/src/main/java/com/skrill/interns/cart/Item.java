package com.skrill.interns.cart;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"quantity", "name", "price"})
public class Item {

    private int quantity;
    private String name;
    private BigDecimal price;

    public Item() {
    }

    public Item(int quantity, String name, BigDecimal price) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public void setId(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return this.quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String toString() {
        return new StringBuilder().append("name: ").append(name).append("\n")
                .append("price: ").append(price).append("\n").toString();
    }
}
