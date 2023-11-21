/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import com.google.gson.Gson;
import static common.product.Constant.URL_HAS_VOUCHER_DELETE_BY_ID;
import dal.ProductDAO.HasVoucherDAO;
import dal.ProductDAO.VoucherDAO;
import dal.UserDAO.UserDAO;
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
@WebServlet(name="deleteHasVoucherById", urlPatterns={URL_HAS_VOUCHER_DELETE_BY_ID})
public class deleteHasVoucherById extends HttpServlet {
    private Gson gson=new Gson();
    private HasVoucherDAO hasVoucherDAO=new HasVoucherDAO();
    private VoucherDAO voucherDAO=new VoucherDAO();
    private UserDAO userDAO=new UserDAO();
    // xoá by id
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try {
            
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            boolean isSuccess = hasVoucherDAO.deleteObject(id);
            if (isSuccess) {
                    res.put("Message", "Success");	
                    JSONHelper.sendJsonAsResponse(resp, 200, res);
            }else {
                    res.put("Message", "Server's error");	
                    JSONHelper.sendJsonAsResponse(resp, 500, res);
            }
        } 
        catch (NumberFormatException e) {
                res.put("Message", "Bad request, check url params again");	
                JSONHelper.sendJsonAsResponse(resp, 400, res);
        }
        
    }
    
}
