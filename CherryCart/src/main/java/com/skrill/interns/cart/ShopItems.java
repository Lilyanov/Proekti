package com.skrill.interns.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ShopItems")
public class ShopItems {

    @XmlElement
    protected static List<Item> item;
    static {
        item = new ArrayList<Item>();
        Item[] items = new Item[10];
        items[0] = new Item(0, "Тениска", BigDecimal.valueOf(15));
        items[1] = new Item(1, "Дънки", BigDecimal.valueOf(20));
        items[2] = new Item(2, "Блуза", BigDecimal.valueOf(30));
        items[3] = new Item(3, "Панталон", BigDecimal.valueOf(40));
        items[4] = new Item(4, "Компютър", BigDecimal.valueOf(500));
        items[5] = new Item(5, "Стол", BigDecimal.valueOf(60));
        items[6] = new Item(6, "Маса", BigDecimal.valueOf(150));
        items[7] = new Item(7, "Портокал", BigDecimal.valueOf(1.5));
        items[8] = new Item(8, "Баничка", BigDecimal.valueOf(0.9));
        items[9] = new Item(9, "Яке", BigDecimal.valueOf(80));
        for (int i = 0; i < items.length; i++) {
            item.add(items[i]);
        }
    }

    public ShopItems() {

    }
}
