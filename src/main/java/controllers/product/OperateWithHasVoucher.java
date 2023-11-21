package controllers.product;

import Model.Product.HasVoucher;
import Model.Product.Voucher;
import Model.User.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import static common.product.Constant.URL_HAS_VOUCHER_POST_AND_GET;
import dal.ProductDAO.HasVoucherDAO;
import dal.ProductDAO.VoucherDAO;
import dal.UserDAO.UserDAO;
import helper.JSONHelper;
import java.util.*;
import java.sql.Date;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */

@WebServlet(name="HasVoucherServlet", urlPatterns={URL_HAS_VOUCHER_POST_AND_GET})
public class OperateWithHasVoucher extends HttpServlet {

    private Gson gson=new Gson();
    private HasVoucherDAO hasVoucherDAO=new HasVoucherDAO();
    private VoucherDAO voucherDAO=new VoucherDAO();
    private UserDAO userDAO=new UserDAO();
    //add by id
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try{
            JSONObject jsonObject = new JSONObject(JSONHelper.readJSON(request));
            // check admin 
            if(jsonObject.get("user_id_admin")!=null &&!jsonObject.get("user_id_admin").toString().equals("")){
                int user_id_admin=Integer.parseInt(jsonObject.get("user_id_admin").toString());
                User user= (User) userDAO.getById(user_id_admin);
                int user_role= user.getUser_role();
                if(user_role ==2) {
                    int user_id=Integer.parseInt(jsonObject.get("user_id").toString());
                    int voucher_id=Integer.parseInt(jsonObject.get("voucher_id").toString());
                    Voucher voucher=(Voucher) voucherDAO.getById(voucher_id);
                    Date expiration_date= new Date((long) voucher.getExpire_time()*24*60*60*1000+System.currentTimeMillis());
                    HasVoucher hasVoucher= new HasVoucher(0, user_id,voucher_id, expiration_date);
                    boolean isSuccess = hasVoucherDAO.addObject(hasVoucher);
                    if (isSuccess) {
                            res.put("message", "Success");	
                            JSONHelper.sendJsonAsResponse(response, 200, res);
                    }
                    else {
                            res.put("message", "Bạn đã nhập sai thông tin truy vấn");	
                            JSONHelper.sendJsonAsResponse(response, 500, res);
                    }
                }
                else{
                          res.put("message", "Bạn không phải admin, chỉ admin mới có thể thực hiện tác vụ này");	
                          JSONHelper.sendJsonAsResponse(response, 403, res);  
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
    
    // get by id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(req.getParameter("has_voucher_id"));
            Object check =  hasVoucherDAO.getById(id);
            if(check==null){
                res.put("message", "HasVoucher không có trong dữ liệu");
                resp.setStatus(400);
                JSONHelper.sendJsonAsResponse(resp, 404, res);
                
            }
            else {
                HasVoucher hasVoucher=(HasVoucher) check;
                JSONHelper.sendJsonAsResponse(resp, 200, hasVoucher);
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