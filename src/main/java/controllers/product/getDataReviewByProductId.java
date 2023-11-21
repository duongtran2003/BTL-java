/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import Model.Product.Review;
import static common.product.Constant.URL_DATA_REVIEW_GET_BY_PRODUCT_ID;
import dal.ProductDAO.ReviewDAO;
import helper.JSONHelper;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
@WebServlet(name = "getDataReviewByProductId", urlPatterns = { URL_DATA_REVIEW_GET_BY_PRODUCT_ID })
public class getDataReviewByProductId extends HttpServlet {
    private ReviewDAO reviewDAO = new ReviewDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<>();
        try {
            
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            List<Review> check = reviewDAO.getListReviewByProduct(id);
            if (check == null || check.isEmpty()) {
                res.put("message", "Product này chưa được ai đánh giá");
                res.put("star", "0");
                res.put("data", "0");
                JSONHelper.sendJsonAsResponse(resp, 404, res);
            } else {
                int star = 0;
                for (Review review : check) {
                    star += review.getStar();
                }
                double star_tb = (double) star / check.size() * 1.0;
                res.put("star", String.format("%.1f", star_tb));
                res.put("data", String.format("%d", check.size()));
                JSONHelper.sendJsonAsResponse(resp, 200, res);
            }
        } catch (NumberFormatException e) {
            res.put("message", "Bad request, check URL params again");
            JSONHelper.sendJsonAsResponse(resp, 400, res);
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
