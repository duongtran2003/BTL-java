/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import Model.Product.Voucher;
import Model.User.User;
import static common.product.Constant.URL_VOUCHER_EDIT;
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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
@WebServlet(name="editVoucher", urlPatterns={URL_VOUCHER_EDIT})
public class editVoucher extends HttpServlet {
    private final VoucherDAO voucherDAO=new VoucherDAO();
    private final UserDAO userDAO=new UserDAO();
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("PATCH")) {
            this.doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String jsonFromRequest = JSONHelper.readJSON(request);
		JSONObject jSONObject= new JSONObject(jsonFromRequest);
		Map<String, String> res = new HashMap<> ();
		if (jSONObject.get("voucher_id") == null && jSONObject.get("voucher_id").toString().trim().equals("")) {
			res.put("message", "bad request, json khong co id");
			JSONHelper.sendJsonAsResponse(response, 400, res);
			return;
		}
		
		try {
			
                        Voucher oldV = (Voucher) voucherDAO.getById(Integer.parseInt(jSONObject.get("voucher_id").toString()));
                        int user_id=Integer.parseInt(jSONObject.get("admin_user_id").toString());
                        User user= (User) userDAO.getById(user_id);
                        int role= user.getUser_role();
                        if(role==2){
                            if (jSONObject.get("discount_amount") != null && !jSONObject.get("discount_amount").toString().equals("")) {
                                    int discount= Integer.parseInt(jSONObject.get("discount_amount").toString());
                                    if(discount<=100 && discount>=0) oldV.setDiscount_amount(discount);
                                   
                            }
                            if (jSONObject.get("expire_time") != null && !jSONObject.get("expire_time").toString().equals("")) {
                                    oldV.setExpire_time(Integer.parseInt(jSONObject.get("expire_time").toString()));
                            }

                            boolean status = voucherDAO.updateObject(oldV);
                            if (!status) {
                                    res.put("message", "bad request");
                                    JSONHelper.sendJsonAsResponse(response, 400, res);
                                   
                            }
                            else {
                                    JSONHelper.sendJsonAsResponse(response, 200, oldV);
                                    
                            }
                        }
                        else{
                            res.put("message", "Chỉ Admin mới có thể thực hiện tác vụ này");
                            JSONHelper.sendJsonAsResponse(response, 403, res);
                            
                        }
                            
		}
		catch (IOException | NumberFormatException | JSONException e) {
			res.put("message", "bad request, sai kieu du lieu");
			JSONHelper.sendJsonAsResponse(response, 400, res);
			
		}
	}
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
