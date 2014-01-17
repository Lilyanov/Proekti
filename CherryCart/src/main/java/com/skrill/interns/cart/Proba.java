package com.skrill.interns.cart;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Proba {

    public static void main(String[] args) throws JAXBException {
        //
        // Inventory inventory = new Inventory();
        // Item[] items = new Item[10];
        // items[0] = new Item(1, "Тениска", BigDecimal.valueOf(15));
        // items[1] = new Item(2, "Дънки", BigDecimal.valueOf(20));
        // items[2] = new Item(3, "Блуза", BigDecimal.valueOf(30));
        // items[3] = new Item(4, "Панталон", BigDecimal.valueOf(40));
        // items[4] = new Item(5, "Компютър", BigDecimal.valueOf(500));
        // items[5] = new Item(6, "Стол", BigDecimal.valueOf(60));
        // items[6] = new Item(7, "Маса", BigDecimal.valueOf(150));
        // items[7] = new Item(8, "Портокал", BigDecimal.valueOf(1.5));
        // items[8] = new Item(9, "Баничка", BigDecimal.valueOf(0.9));
        // items[9] = new Item(10, "Яке", BigDecimal.valueOf(80));
        // for (int i = 0; i < items.length; i++) {
        // inventory.getItems().add(items[i]);
        // }
        // BufferedWriter bw = null;
        // try {
        // dataBase = new File("dataBase.txt");
        // if (!dataBase.exists()) {
        // dataBase.createNewFile();
        // }
        // FileWriter fw = new FileWriter(dataBase.getAbsoluteFile());
        // bw = new BufferedWriter(fw);
        // bw.write(convertInventoryToXml(inventory));
        // } catch (IOException e) {
        // e.printStackTrace();
        // System.out.println("Errorrr!!!!!");
        // } finally {
        // try {
        // bw.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }

        ClientCart c = new ClientCart();
        c.getItems().put(2, 15);
        c.getItems().put(10, 35);
        StringWriter serializedItem = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(ClientCart.class);
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(c, serializedItem);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(serializedItem.toString());

    }

}
