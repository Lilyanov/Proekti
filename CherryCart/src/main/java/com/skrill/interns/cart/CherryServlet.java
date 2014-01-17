package com.skrill.interns.cart;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class CherryServlet extends HttpServlet {

    private static final long serialVersionUID = -4057767342896388449L;
    protected static List<Item> database = ShopItems.item;
    private static Encryption crypt = new Encryption();
    protected static XmlEncoder encoder = new XmlEncoder();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected Cookie bakeCookie(ClientCart cart, int id, int quantity) {
        cart.getItems().put(id, quantity);
        String cookieValue = encoder.convertCartToXml(cart);
        System.out.println("CART TO XML : " + cookieValue);
        if (cookieValue == null) {
            return null;
        }
        String encryptedCookie = encryptCookie(cookieValue);
        return new Cookie("CART", encryptedCookie);
    }

    protected String encryptCookie(String cookie) {
        byte[] cookieValue = crypt.encrypt(cookie);
        String encryptedCookie = new String(cookieValue);
        return encryptedCookie;
    }

    protected String decryptCookie(Cookie encryptedCookie) {
        byte[] encryptedCookieBytes = encryptedCookie.getValue().getBytes(Charset.forName("UTF-8"));
        String decryptedCookie = crypt.decrypt(encryptedCookieBytes);
        return decryptedCookie;
    }

    protected void respondToClient(HttpServletResponse response, String responseMessage, int status) {
        try {
            response.setStatus(status);
            response.setCharacterEncoding("UTF-8");
            response.setContentLength(responseMessage.getBytes(Charset.forName("UTF-8")).length);
            response.setContentType("text/xml");
            response.setHeader("Connection", "close");
            response.getOutputStream().write(responseMessage.getBytes(Charset.forName("UTF-8")));
            response.flushBuffer();
        } catch (IOException e) {
            System.err.println("Could not respond to client");
            e.printStackTrace();
        }
    }
}
