package com.skrill.interns.cart;

import java.util.Iterator;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showcart")
public class Read extends CherryServlet {

    private static final long serialVersionUID = 3126355397241435674L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        boolean hasCartCookie = false;
        StringBuilder shownCart = new StringBuilder();
        if (cookies == null) {
            String responseMessage = "Your cart is empty.";
            respondToClient(response, responseMessage, 200);
            return;
        }
        for (Cookie cookie : cookies) {
            if ("CART".equals(cookie.getName())) {
                ClientCart cart = null;
                cart = encoder.convertXmlToCart(decryptCookie(cookie));
                if (cart == null) {
                    String responseMessage = "406 Not Acceptable\n\nCorrupted cookie! It will be deleted.";
                    respondToClient(response, responseMessage, 406);
                    return;
                }
                Map<Integer, Integer> items = cart.getItems();
                if(items.isEmpty()) {
                    String responseMessage = "Your cart is empty.";
                    respondToClient(response, responseMessage, 200);
                    return;
                }
                Iterator<Map.Entry<Integer, Integer>> entries = items.entrySet().iterator();
                shownCart.append("Items added in the cart:\n");
                while (entries.hasNext()) {
                    Map.Entry<Integer, Integer> entry = entries.next();
                    int id = entry.getKey();
                    int quantity = entry.getValue();
                    Item selectedItem = database.get(id);
                    
                    shownCart.append("Id: ").append(entry.getKey()).append("\n");
                    shownCart.append(selectedItem.toString());
                    shownCart.append("Quantity: ").append(quantity).append("\n\n");
                }
                hasCartCookie = true;
                break;
            }
        }
        if (!hasCartCookie) {
            String responseMessage = "Your cart is empty.";
            respondToClient(response, responseMessage, 200);
            return;
        }

        String responseMessage = shownCart.toString();
        respondToClient(response, responseMessage, 200);
    }

}
