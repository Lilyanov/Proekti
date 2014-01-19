package com.skrill.interns.cart;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class CherryServlet extends HttpServlet {

	private static final long serialVersionUID = -4057767342896388449L;
	protected static Map<Integer, Item> database;
	private static Encryption crypt;
	protected static CartXmlEncoder encoder;
	
	static {
		crypt = new Encryption();
		encoder = new CartXmlEncoder();
		database = new HashMap<Integer, Item>();
		database.put(1, new Item(-1, "Тениска", BigDecimal.valueOf(15)));
		database.put(2, new Item(-1, "Дънки", BigDecimal.valueOf(20)));
		database.put(3, new Item(-1, "Блуза", BigDecimal.valueOf(30)));
		database.put(4, new Item(-1, "Панталон", BigDecimal.valueOf(40)));
		database.put(5, new Item(-1, "Компютър", BigDecimal.valueOf(500)));
		database.put(6, new Item(-1, "Стол", BigDecimal.valueOf(60)));
		database.put(7, new Item(-1, "Маса", BigDecimal.valueOf(150)));
		database.put(8, new Item(-1, "Портокал", BigDecimal.valueOf(1.5)));
		database.put(9, new Item(-1, "Баничка", BigDecimal.valueOf(0.9)));
		database.put(10, new Item(-1, "Яке", BigDecimal.valueOf(80)));	
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
		byte[] encryptedCookieBytes = encryptedCookie.getValue().getBytes(
				Charset.forName("UTF-8"));
		String decryptedCookie = crypt.decrypt(encryptedCookieBytes);
		return decryptedCookie;
	}

	protected void respondToClient(HttpServletResponse response,
			String responseMessage, int status) {
		try {
			response.setStatus(status);
			response.setCharacterEncoding("UTF-8");
			response.setContentLength(responseMessage.getBytes(Charset
					.forName("UTF-8")).length);
			response.setContentType("text/plain");
			response.setHeader("Connection", "close");
			response.getOutputStream().write(
					responseMessage.getBytes(Charset.forName("UTF-8")));
			response.flushBuffer();
		} catch (IOException e) {
			System.err.println("Could not respond to client");
			e.printStackTrace();
		}
	}
}
