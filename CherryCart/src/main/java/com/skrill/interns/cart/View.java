package com.skrill.interns.cart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class View extends CherryServlet {

    private static final long serialVersionUID = 1L;
    private static XmlEncoder encoder = new XmlEncoder();
    private static ShopItems items = new ShopItems();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder responseMessage = new StringBuilder();
        responseMessage.append(encoder.viewShopItemsAsXML(items));

        respondToClient(response, responseMessage.toString(), 200);
    }
}
