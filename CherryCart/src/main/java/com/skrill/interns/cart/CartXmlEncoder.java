package com.skrill.interns.cart;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class CartXmlEncoder {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public CartXmlEncoder() {
        try {
        	JAXBContext context = JAXBContext.newInstance(ClientCart.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    protected String convertCartToXml(ClientCart cart) {
        StringWriter serializedItem = new StringWriter();
        try {
            marshaller.marshal(cart, serializedItem);
        } catch (JAXBException e) {
            return null;
        }
        return serializedItem.toString();
    }

    protected ClientCart convertXmlToCart(String xml) {
        ClientCart cartObject = new ClientCart();
        try {
            cartObject = (ClientCart) unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes(Charset
                    .forName("UTF-8"))));
        } catch (JAXBException e) {
            return null;
        }
        return cartObject;
    }
}
