package com.skrill.interns.cart;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlEncoder {

    private JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    private void createCartMarshaller() {
        try {
            context = JAXBContext.newInstance(ClientCart.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    protected String convertCartToXml(ClientCart cart) {
        createCartMarshaller();
        StringWriter serializedItem = new StringWriter();
        try {
            marshaller.marshal(cart, serializedItem);
        } catch (JAXBException e) {
            return null;
        }
        return serializedItem.toString();
    }

    protected ClientCart convertXmlToCart(String xml) {
        createCartMarshaller();
        ClientCart cartObject = new ClientCart();
        try {
            cartObject = (ClientCart) unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes(Charset
                    .forName("UTF-8"))));
        } catch (JAXBException e) {
            return null;
        }
        return cartObject;
    }

    protected String viewShopItemsAsXML(ShopItems items) {
        try {
            context = JAXBContext.newInstance(ShopItems.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter serializedItem = new StringWriter();
            marshaller.marshal(items, serializedItem);
            return serializedItem.toString();
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    protected String viewItemAsXML(Item item) {
        try {
            context = JAXBContext.newInstance(Item.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter serializedItem = new StringWriter();
            marshaller.marshal(item, serializedItem);
            return serializedItem.toString();
        } catch (JAXBException e1) {
            System.out.println("ADFGAGAG");
            e1.printStackTrace();
        }
        return null;
    }
}
