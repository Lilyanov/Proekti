package com.skrill.interns.cart;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class View extends CherryServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder responseMessage = new StringBuilder();
        Iterator<Map.Entry<Integer, Item>> entries = database.entrySet().iterator();
        responseMessage.append("Items in the shop:\n");
        while(entries.hasNext()) {
        	 Map.Entry<Integer, Item> entry = entries.next();
        	 int id = entry.getKey();
        	 Item item = entry.getValue();
        	 responseMessage.append("Id: ").append(id).append("\n")
        	 				.append(item.toString()).append("\n");
        }

        respondToClient(response, responseMessage.toString(), 200);
    }
}
