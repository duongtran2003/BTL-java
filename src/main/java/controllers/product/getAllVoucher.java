/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import Model.Product.Voucher;
import dal.ProductDAO.VoucherDAO;
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
@WebServlet(name="getAllVoucher", urlPatterns={"/product/voucher/getallvoucher/*"})
public class getAllVoucher extends HttpServlet {
    
    // lấy ra tất cả voucher hiện có của shop
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        VoucherDAO voucherDAO=new VoucherDAO();
        String id = req.getPathInfo().substring(1);
        List <Voucher> vouchers=voucherDAO.queryObjects();
        if(vouchers==null){
            res.put("Messge", "Voucher trống");
            JSONHelper.sendJsonAsResponse(resp, 404, res);
        }
        else{
            res.put("Messge", "Success");
            JSONHelper.sendJsonAsResponse(resp, 404, vouchers);
        }
        
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
