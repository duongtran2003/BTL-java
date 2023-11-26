/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import Model.Product.HasVoucher;
import Model.Product.Voucher;
import Model.Product.Order;
import Model.Product.Product;
import Model.Product.ProductOrder;
import dal.ProductDAO.HasVoucherDAO;
import dal.ProductDAO.OrderDAO;
import dal.ProductDAO.ProductDAO;
import dal.ProductDAO.ProductOrderDAO;
import dal.ProductDAO.VoucherDAO;
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
@WebServlet(name = "createOrder", urlPatterns = { "/product/createOrder" })
public class createOrder extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> jsonMap = new JSONObject(JSONHelper.readJSON(request)).toMap();
		// {
		// "user_id": ,
		// "products":
		// [
		// {
		// "product_id": int,
		// "nametag": String,
		// "color": String,
		// "size": int, // 0 - S, 1 - M, 2 - L, 3 - XL, 4 - XXL
		// "squad_number": int,
		// "quantity": int,
		// },
		// ...
		// ],
		// "address": ,
		// "contact": ,
		// "has_voucher_id": ,
		// }
		Map<String, String> res = new HashMap<>();
		try {
			int has_voucher_id = Integer.parseInt(jsonMap.get("has_voucher_id").toString());
			HasVoucherDAO hasVoucherDAO = new HasVoucherDAO();
			HasVoucher hasVoucher = (HasVoucher) hasVoucherDAO.getById(has_voucher_id);
			int discounted = 0;
			if (hasVoucher != null) {
				Date exp = new Date(hasVoucher.getExpiration_date().getTime());
				Date dateNow = new Date(System.currentTimeMillis());
				if (dateNow.getTime() <= exp.getTime()) {
					VoucherDAO voucherDAO = new VoucherDAO();
					int voucher_id = hasVoucher.getVoucher_id();
					Voucher voucher = (Voucher) voucherDAO.getById(voucher_id);
					discounted = voucher.getDiscount_amount();
				}
			}
			OrderDAO orderDAO = new OrderDAO();
			int user_id = Integer.parseInt(jsonMap.get("user_id").toString());
			String address = jsonMap.get("address").toString();
			String contact = jsonMap.get("contact").toString();
			List<ProductOrder> prods = new ArrayList<>();
			int order_id = orderDAO.addOrder(new Order(0, user_id, prods, discounted, address, contact));
			ProductDAO productDAO = new ProductDAO();
			ProductOrderDAO productOrderDAO = new ProductOrderDAO();
			if (order_id != -1) {
				List<Map<String, Object>> prodsInfo = (ArrayList<Map<String, Object>>) jsonMap.get("products");
				for (Map<String, Object> prod : prodsInfo) {
					int product_id = Integer.parseInt(prod.get("product_id").toString());
					String nametag = prod.get("nametag").toString();
					String color = prod.get("color").toString();
					int size = Integer.parseInt(prod.get("size").toString());
					int squad_number = Integer.parseInt(prod.get("squad_number").toString());
					int quantity = Integer.parseInt(prod.get("quantity").toString());
					Product newProd = (Product) productDAO.getById(product_id);
					productOrderDAO.addObject(
							new ProductOrder(0, newProd, order_id, nametag, color, size, squad_number, quantity));
				}
			} else {
				res.put("message", "Co van de xay ra trong luc tao order");
				JSONHelper.sendJsonAsResponse(response, 500, res);
				return;
			}
		} catch (Exception e) {
			res.put("message", e.getMessage());
			JSONHelper.sendJsonAsResponse(response, 400, res);
			return;
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
