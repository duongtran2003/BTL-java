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

/**
 *
 * @author DELL
 */

@WebServlet(name="OperateWithHasVoucher", urlPatterns={Constant.URL_HAS_VOUCHER_ADD,Constant.URL_HAS_VOUCHER_DELETE,Constant.URL_HAS_VOUCHER_GET})
public class OperateWithHasVoucher extends HttpServlet {

    private Gson gson=new Gson();
    private SimpleDateFormat date=new SimpleDateFormat("yyyy/MM/dd");
    private HasVoucherDAO hasVoucherDAO=new HasVoucherDAO();
    private VoucherDAO voucherDAO=new VoucherDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String urlPath=request.getServletPath();
            switch (urlPath) {
                case Constant.URL_HAS_VOUCHER_ADD:  
                    addHasVoucher(request,response);          
                    break;    
                case Constant.URL_HAS_VOUCHER_DELETE:
                    deleteHasVoucher(request,response);
                    break;
                case Constant.URL_HAS_VOUCHER_GET:
                    getHasVoucher(request,response);
                    break;
                    
            }
        } catch (ParseException ex) {
            Logger.getLogger(OperateWithHasVoucher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void getHasVoucher(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(request.getParameter("has_voucher_id"));
            HasVoucher hasVoucher = (HasVoucher) hasVoucherDAO.getById(id);
            JSONHelper.sendJsonAsResponse(response, 200, hasVoucher);
        } 
        catch (NumberFormatException e) {
                res.put("message", "bad request, check url params again");
                response.setStatus(400);
                JSONHelper.sendJsonAsResponse(response, 400, res);
        }
    }
    private void deleteHasVoucher(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(request.getParameter("has_voucher_id"));
            boolean isSuccess = hasVoucherDAO.deleteObject(id);
            if (isSuccess) {
                    res.put("message", "success");	
                    JSONHelper.sendJsonAsResponse(response, 200, res);
            }
            else {
                    res.put("message", "server's error");	
                    JSONHelper.sendJsonAsResponse(response, 500, res);
            }
        } 
        catch (NumberFormatException e) {
                res.put("message", "bad request, check url params again");	
                JSONHelper.sendJsonAsResponse(response, 400, res);
        }
        
    }
    private void addHasVoucher(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
        Map<String, Object> res = new HashMap<> ();
        try{
            int id=Integer.parseInt(request.getParameter("has_voucher_id"));
            int user_id=Integer.parseInt(request.getParameter("user_id"));
            int voucher_id=Integer.parseInt(request.getParameter("voucher_id"));
            Voucher voucher=(Voucher) voucherDAO.getById(voucher_id);
            Date expiration_date= new Date((long) voucher.getExpire_time()*24*60*60+System.currentTimeMillis());
            HasVoucher hasVoucher= new HasVoucher(id, user_id,voucher_id, expiration_date);
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}