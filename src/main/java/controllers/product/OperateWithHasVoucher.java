package controllers.product;

import Model.Product.HasVoucher;
import Model.Product.Voucher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import common.product.Constant;
import dal.ProductDAO.HasVoucherDAO;
import dal.ProductDAO.VoucherDAO;
import helper.JSONHelper;
import java.util.*;
import java.text.*;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */

@WebServlet(name="HasVoucherServlet", urlPatterns={"product/hasvoucher"})
public class OperateWithHasVoucher extends HttpServlet {

    private Gson gson=new Gson();
    private HasVoucherDAO hasVoucherDAO=new HasVoucherDAO();
    private VoucherDAO voucherDAO=new VoucherDAO();
    //add by id
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try{
            StringBuilder json = (StringBuilder) request.getAttribute("json");
            JSONObject jsonObject = new JSONObject(json.toString());
            int user_id=jsonObject.getInt("user_id");
            int voucher_id=jsonObject.getInt("voucher_id");
            Voucher voucher=(Voucher) voucherDAO.getById(voucher_id);
            Date expiration_date= new Date((long) voucher.getExpire_time()*24*60*60+System.currentTimeMillis());
            HasVoucher hasVoucher= new HasVoucher(0, user_id,voucher_id, expiration_date);
            boolean isSuccess = hasVoucherDAO.addObject(hasVoucher);
            if (isSuccess) {
                    res.put("message", "success");	
                    JSONHelper.sendJsonAsResponse(response, 200, res);
            }
            else {
                    res.put("message", "server's error");	
                    JSONHelper.sendJsonAsResponse(response, 500, res);
            }
         }
        catch(NumberFormatException e) {
            res.put("message", "bad request, check url params again");	
            JSONHelper.sendJsonAsResponse(response, 400, res);
        }
    }
    // xoá by id
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try {
            StringBuilder json = (StringBuilder) req.getAttribute("json");
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(json.toString());
            int id=jsonObject.getInt("has_voucher_id");
            boolean isSuccess = hasVoucherDAO.deleteObject(id);
            if (isSuccess) {
                    res.put("message", "success");	
                    JSONHelper.sendJsonAsResponse(resp, 200, res);
            }else {
                    res.put("message", "server's error");	
                    JSONHelper.sendJsonAsResponse(resp, 500, res);
            }
        } 
        catch (NumberFormatException e) {
                res.put("message", "bad request, check url params again");	
                JSONHelper.sendJsonAsResponse(resp, 400, res);
        }
        
    }
    // get by id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(req.getParameter("has_voucher_id"));
            HasVoucher hasVoucher = (HasVoucher) hasVoucherDAO.getById(id);
            JSONHelper.sendJsonAsResponse(resp, 200, hasVoucher);
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