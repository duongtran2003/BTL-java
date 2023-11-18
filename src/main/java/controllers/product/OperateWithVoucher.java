package controllers.product;

import Model.Product.Voucher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import common.product.Constant;
import dal.ProductDAO.VoucherDAO;
import helper.JSONHelper;
import java.util.*;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */

@WebServlet(name="VoucherServlet", urlPatterns={"product/voucher"})
public class OperateWithVoucher extends HttpServlet {

    private Gson gson=new Gson();
    private VoucherDAO voucherDAO=new VoucherDAO();
    // tạo voucher mới
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       Map<String, Object> res = new HashMap<> ();
       
        try{
            StringBuilder json = (StringBuilder) request.getAttribute("json");
            JSONObject jsonObject = new JSONObject(json.toString());
            int discount_amount=jsonObject.getInt("discount_amount");
            int expire_time=jsonObject.getInt("expire_time");
            Voucher voucher= new Voucher(0, discount_amount, expire_time);
            boolean isSuccess = voucherDAO.addObject(voucher);
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
    // xoá voucher theo id
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         Map<String, Object> res = new HashMap<> ();
        try {
            StringBuilder json = (StringBuilder) req.getAttribute("json");
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(json.toString());
            int id=jsonObject.getInt("voucher_id");
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
    // lấy voucher theo id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(req.getParameter("voucher_id"));
            Voucher voucher = (Voucher) voucherDAO.getById(id);
            JSONHelper.sendJsonAsResponse(resp, 200, voucher);
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