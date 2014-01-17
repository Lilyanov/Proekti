package com.skrill.interns.cart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class Update extends CherryServlet {

    private static final long serialVersionUID = -4556201665035076096L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String idParameter = request.getParameter("id");
        if (idParameter == null) {
            String responseMessage = "400 Bad Request\n\nId parameter not set";
            respondToClient(response, responseMessage, 400);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            String responseMessage = "400 Bad Request\n\nIncorrectly set id parameter";
            respondToClient(response, responseMessage, 400);
            return;
        }

        String quantityParameter = request.getParameter("quantity");
        int quantity;
        if (quantityParameter == null) {
            String responseMessage = "Please set quantity parameter.";
            respondToClient(response, responseMessage, 200);
            return;
        }
        try {
            quantity = Integer.parseInt(quantityParameter);
        } catch (NumberFormatException e) {
            String responseMessage = "400 Bad Request\n\nIncorrectly set qunatity parameter";
            respondToClient(response, responseMessage, 400);
            return;
        }

        if (quantity < 1) {
            String responseMessage = "Quantity cannot be less than 1";
            respondToClient(response, responseMessage, 200);
            return;
        }

        Item selectedItem = null;
        // search for required item in dataBase
        for (Item item : database) {
            if (item.getId() == id) {
                selectedItem = item;
                break;
            }
        }
        if (selectedItem == null) {
            String responseMessage = "Sorry, you don't have this item. Please choose something else.";
            respondToClient(response, responseMessage, 200);
            return;
        }
        Cookie[] cookies = request.getCookies();
        boolean hasCartCookie = false;

        if (cookies == null) {
            String responseMessage = "Could not update. Please add something to the cart first!";
            respondToClient(response, responseMessage, 200);
            return;
        }
        for (Cookie cookie : cookies) {
            if ("CART".equals(cookie.getName())) {
                ClientCart cart = null;
                cart = encoder.convertXmlToCart(decryptCookie(cookie));
                if (cart == null) {
                    String responseMessage = "406 Not Acceptable\n\nCorrupted cookie! It will be deleted";
                    respondToClient(response, responseMessage, 406);
                    cart = new ClientCart();
                }
                if (cart.getItems().containsKey(id)) {
                    Cookie newCookie = bakeCookie(cart, id, quantity);
                    response.addCookie(newCookie);
                    hasCartCookie = true;
                    break;
                }

            }
            if (!hasCartCookie) {
                String responseMessage = "Could not update. Please add the item to the cart first!";
                respondToClient(response, responseMessage, 200);
                return;
            }
        }
        String responseMessage = "Updated to " + quantity + " item(s):\n" + encoder.viewItemAsXML(selectedItem);
        respondToClient(response, responseMessage, 200);
    }
}
