/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import static common.product.Constant.URL_VOUCHER_DELETE_VOUCHER_BY_ID;
import dal.ProductDAO.VoucherDAO;
import helper.JSONHelper;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
@WebServlet(name="deleteVoucherById", urlPatterns={URL_VOUCHER_DELETE_VOUCHER_BY_ID})
public class deleteVoucherById extends HttpServlet {
    // xoá voucher theo id
    private VoucherDAO voucherDAO=new VoucherDAO();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         Map<String, Object> res = new HashMap<> ();
        try {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            
            boolean isSuccess = voucherDAO.deleteObject(id);
            if (isSuccess) {
                    res.put("message", "success");	
                    JSONHelper.sendJsonAsResponse(resp, 200, res);
            }
            else {
                    res.put("message", "server's error");	
                    JSONHelper.sendJsonAsResponse(resp, 500, res);
            }
        } 
        catch (NumberFormatException e) {
                res.put("message", "bad request, check url params again");	
                JSONHelper.sendJsonAsResponse(resp, 400, res);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
