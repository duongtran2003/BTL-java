/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Product.Order;
import dal.ProductDAO.OrderDAO;
import helper.JSONHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
@WebServlet(name = "getOrderByUserId", urlPatterns = {"/product/getOrderByUserId/*"})
public class getOrderByUserId extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getPathInfo().substring(1);
		Map<String, Object> res = new HashMap<String, Object> ();
		try {
			int _id = Integer.parseInt(id);
			OrderDAO orderDao = new OrderDAO();
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals("user_id")) {
					int user_id = Integer.parseInt(cookie.getValue());
					if (user_id != _id) {
						res.put("message", "ko phai order cua ban");
						JSONHelper.sendJsonAsResponse(response, 403, res);
					}
					break;
				}
			}
			List<Order> orders = orderDao.getByUserId(_id);
			JSONHelper.sendJsonAsResponse(response, 200, orders);
		}
		catch (NumberFormatException e) {
			res.put("message", "bad request, check url params again");
			response.setStatus(400);
			JSONHelper.sendJsonAsResponse(response, 400, res);
		}
	}	

}
