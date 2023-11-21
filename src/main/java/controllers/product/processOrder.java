/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import Model.Product.Order;
import Model.User.User;
import dal.ProductDAO.OrderDAO;
import dal.UserDAO.UserDAO;
import helper.JSONHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
@WebServlet(name = "processOrder", urlPatterns = {"/product/processOrder"})
public class processOrder extends HttpServlet {
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("PATCH")) {
            this.doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }

	protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> jsonMap = new JSONObject(JSONHelper.readJSON(request)).toMap();
		Map<String, String> res = new HashMap<> ();
		if (jsonMap.get("user_id") == null || jsonMap.get("user_id").toString().equals("")) {
			res.put("message", "Bad request");
			JSONHelper.sendJsonAsResponse(response, 400, res);
		}
		if (jsonMap.get("order_id") == null || jsonMap.get("order_id").toString().equals("")) {
			res.put("message", "Bad request");
			JSONHelper.sendJsonAsResponse(response, 400, res);
		}
		if (jsonMap.get("status") == null || jsonMap.get("status").toString().equals("")) {
			res.put("message", "Bad request");
			JSONHelper.sendJsonAsResponse(response, 400, res);
		}

		try {
			UserDAO userDAO = new UserDAO();
			int user_id = Integer.parseInt(jsonMap.get("user_id").toString());
			int order_id = Integer.parseInt(jsonMap.get("order_id").toString());
			int status = Integer.parseInt(jsonMap.get("status").toString());
			User currentUser = (User) userDAO.getById(user_id);
			if (currentUser.getUser_role() != 2) {
				res.put("message", "khong phai la admin");
				JSONHelper.sendJsonAsResponse(response, 403, res);
			}
			OrderDAO orderDAO = new OrderDAO();
			Order order = (Order) orderDAO.getById(order_id);
			order.setStatus(status);
			orderDAO.updateObject(order);
		} 
		catch (Exception e) {
			res.put("message", "Bad request");
			JSONHelper.sendJsonAsResponse(response, 400, res);
		}
	}
}