/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import Model.Product.HasVoucher;
import static common.product.Constant.URL_HAS_VOUCHER_DELETE_WHEN_EXPIRED;
import dal.ProductDAO.HasVoucherDAO;
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
@WebServlet(name="deleteHasVoucherWhenExpired", urlPatterns={URL_HAS_VOUCHER_DELETE_WHEN_EXPIRED})
public class deleteHasVoucherWhenExpired extends HttpServlet {
   
    // xoá hasvoucher khi hết hạn
    private HasVoucherDAO hasVoucherDAO=new HasVoucherDAO();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        List <HasVoucher> a= hasVoucherDAO.queryObjects();
        if(a==null){
            res.put("message", "HasVoucher trống ");
            resp.setStatus(400);
            JSONHelper.sendJsonAsResponse(resp, 404, res);
        }
        else{
            for(HasVoucher hasVoucher:a){
                if(hasVoucher.getExpiration_date().getTime()<System.currentTimeMillis()){
                    boolean isSuccess = hasVoucherDAO.deleteObject(hasVoucher.getHas_voucher_id());
                    JSONHelper.sendJsonAsResponse(resp, 200, hasVoucher);
                    
                }
            }
            
        }
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
