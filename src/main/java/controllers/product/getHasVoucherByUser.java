/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import Model.Product.HasVoucher;
import dal.ProductDAO.HasVoucherDAO;
import helper.JSONHelper;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name="getHasVoucherByUser", urlPatterns={"/product/hasvoucher/getbyuser/*"})
public class getHasVoucherByUser extends HttpServlet {
   
    // lấy ra những voucher mà User đang có
    private HasVoucherDAO hasVoucherDAO=new HasVoucherDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            List <HasVoucher> check =  hasVoucherDAO.getListHasVouchersByUser(id);
            if(check==null){
                res.put("message", "HasVoucher không có trong dữ liệu");
                resp.setStatus(400);
                JSONHelper.sendJsonAsResponse(resp, 404, res);
                
            }
            else {
                JSONHelper.sendJsonAsResponse(resp, 200,check);
            }
            
        } 
        catch (NumberFormatException e) {
                res.put("message", "bad request, check url params again");
                resp.setStatus(400);
                JSONHelper.sendJsonAsResponse(resp, 400, res);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
