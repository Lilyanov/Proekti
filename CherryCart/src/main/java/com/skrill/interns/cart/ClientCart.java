package com.skrill.interns.cart;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cart")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientCart {

    HashMap<Integer, Integer> items;

    public ClientCart() {
        items = new HashMap<Integer, Integer>();
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }
}
