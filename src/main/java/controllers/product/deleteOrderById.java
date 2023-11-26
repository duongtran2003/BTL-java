/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import Model.Product.Order;
import Model.User.User;

import java.util.List;

import dal.ProductDAO.OrderDAO;
import dal.UserDAO.UserDAO;
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
@WebServlet(name = "deleteOrderById", urlPatterns = { "/product/deleteOrderById/*" })
public class deleteOrderById extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getPathInfo().substring(1);
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			OrderDAO orderDao = new OrderDAO();
			// Cookie[] cookies = request.getCookies();
			// if (cookies == null) {
			// 	res.put("message", "thieu cookie");
			// 	JSONHelper.sendJsonAsResponse(response, 400, res);
			// }
			// for (Cookie cookie : cookies) {
			// 	if (cookie.getName().equals("user_id")) {
			// 		int user_id = Integer.parseInt(cookie.getValue());
			// 		UserDAO userDAO = new UserDAO();
			// 		User currentUser = (User) userDAO.getById(user_id);
			// 		if (currentUser == null) {
			// 			res.put("message", "wrong user id");
			// 			JSONHelper.sendJsonAsResponse(response, 400, res);
			// 			return;
			// 		}
			// 		List<Order> orders = orderDao.getByUserId(user_id);
			// 		if ((orders == null || orders.size() == 0) && currentUser.getUser_role() != 2) {
			// 			res.put("message", "ko phai order cua ban, ban cung chang phai la admin");
			// 			JSONHelper.sendJsonAsResponse(response, 401, res);
			// 			return;
			// 		}
			// 		break;
			// 	}
			// }
			int _id = Integer.parseInt(id);
			orderDao.deleteObject(_id);
			res.put("message", "success");
			JSONHelper.sendJsonAsResponse(response, 200, res);
			return;
		} catch (NumberFormatException e) {
			res.put("message", "bad request, check url params again");
			response.setStatus(400);
			JSONHelper.sendJsonAsResponse(response, 400, res);
			return;
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
