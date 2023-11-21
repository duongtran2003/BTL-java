package controllers.product;

import Model.Product.Voucher;
import Model.User.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import common.product.Constant;
import static common.product.Constant.URL_VOUCHER_POST_AND_GET;
import dal.ProductDAO.VoucherDAO;
import dal.UserDAO.UserDAO;
import helper.JSONHelper;
import java.util.*;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */

@WebServlet(name="VoucherServlet", urlPatterns={URL_VOUCHER_POST_AND_GET})
public class OperateWithVoucher extends HttpServlet {

    private Gson gson=new Gson();
    private VoucherDAO voucherDAO=new VoucherDAO();
    private UserDAO userDAO=new UserDAO();
    // tạo voucher mới
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       Map<String, Object> res = new HashMap<> ();
       
        try{
            JSONObject jsonObject = new JSONObject(JSONHelper.readJSON(request));
            if(jsonObject.get("user_id_admin")!=null &&!jsonObject.get("user_id_admin").toString().equals("")){
                int user_id=Integer.parseInt(jsonObject.get("user_id").toString());
                User user= (User) userDAO.getById(user_id);
                int user_role= user.getUser_role();
                int discount_amount=Integer.parseInt(jsonObject.get("discount_amount").toString());
                int expire_time=Integer.parseInt(jsonObject.get("expire_time").toString());
                Voucher voucher= new Voucher(0, discount_amount, expire_time);
                if(user_role ==2) {
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
                else{
                          res.put("message", "Chỉ Admin mới có thể thực hiện tác vụ này");	
                          JSONHelper.sendJsonAsResponse(response, 500, res);  
                            }
                }
            else{
                res.put("message", "Bạn chưa nhập id của admin, vui lòng nhập nhanh còn kịp");	
                JSONHelper.sendJsonAsResponse(response, 500, res);
            }
            
         }
        catch(NumberFormatException e) {
            res.put("message", "bad request, check url params again");	
            JSONHelper.sendJsonAsResponse(response, 400, res);
        }
    }
    
    
    // lấy voucher theo id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(req.getParameter("voucher_id"));
            Object check =  voucherDAO.getById(id);
            if(check==null){
                res.put("message", "Voucher không có trong dữ liệu");
                resp.setStatus(400);
                JSONHelper.sendJsonAsResponse(resp, 404, res);
                
            }
            else {
                Voucher voucher=(Voucher) check;
                JSONHelper.sendJsonAsResponse(resp, 200, voucher);
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