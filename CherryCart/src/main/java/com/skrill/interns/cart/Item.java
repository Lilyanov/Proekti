package com.skrill.interns.cart;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement
@XmlType(propOrder={"id", "name", "price"})
public class Item {

    private int id;
    private String name;
    private BigDecimal price;

    public Item() {
    }

    public Item(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String toString() {
        return new StringBuilder().append("Id: ").append(id).append("\n")
                .append("name: ").append(name).append("\n")
                .append("price: ").append(price).append("\n").toString();
    }
}
