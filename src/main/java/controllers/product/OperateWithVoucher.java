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

/**
 *
 * @author DELL
 */

@WebServlet(name="OperateWithVoucher", urlPatterns={Constant.URL_VOUCHER_ADD,Constant.URL_VOUCHER_DELETE,Constant.URL_VOUCHER_GET})
public class OperateWithVoucher extends HttpServlet {

    private Gson gson=new Gson();
    private VoucherDAO voucherDAO=new VoucherDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String urlPath=request.getServletPath();
        switch (urlPath) {
            case Constant.URL_VOUCHER_ADD:
               addVoucher(request,response);
            break;
            case Constant.URL_VOUCHER_DELETE:
                deleteVoucher(request,response);
            break;
            case Constant.URL_VOUCHER_GET:
                getVoucher(request,response);
            break;
               
        }
    }
    private void getVoucher(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(request.getParameter("voucher_id"));
            Voucher voucher = (Voucher) voucherDAO.getById(id);
            JSONHelper.sendJsonAsResponse(response, 200, voucher);
        } 
        catch (NumberFormatException e) {
                res.put("message", "bad request, check url params again");
                response.setStatus(400);
                JSONHelper.sendJsonAsResponse(response, 400, res);
        }
    }
    private void deleteVoucher(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(request.getParameter("voucher_id"));
            boolean isSuccess = voucherDAO.deleteObject(id);
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
    private void addVoucher(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> res = new HashMap<> ();
        try{
            int id=Integer.parseInt(request.getParameter("voucher_id"));
            int discount_amount=Integer.parseInt(request.getParameter("discount_amount"));
            int expire_time=Integer.parseInt(request.getParameter("expire_time"));
            Voucher voucher= new Voucher(id, discount_amount, expire_time);
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}