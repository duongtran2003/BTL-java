/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import Model.Product.HasVoucher;
import Model.User.User;
import static common.product.Constant.URL_HAS_VOUCHER_EDIT;
import dal.ProductDAO.HasVoucherDAO;
import dal.UserDAO.UserDAO;
import helper.JSONHelper;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
@WebServlet(name="editHasVoucher", urlPatterns={URL_HAS_VOUCHER_EDIT})
public class editHasVoucher extends HttpServlet {
   
    
    private final HasVoucherDAO hasVoucherDAO=new HasVoucherDAO();
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
		if (jSONObject.get("has_voucher_id") == null && jSONObject.get("has_voucher_id").toString().trim().equals("")) {
			res.put("message", "bad request, json khong co id");
			JSONHelper.sendJsonAsResponse(response, 400, res);
			return;
		}
		
		try {
			
                        HasVoucher oldH = (HasVoucher) hasVoucherDAO.getById(Integer.parseInt(jSONObject.get("has_voucher_id").toString()));
                        int admin_user_id=Integer.parseInt(jSONObject.get("admin_user_id").toString());
                        User user= (User) userDAO.getById(admin_user_id);
                        int role= user.getUser_role();
                        if(role==2){
                            if (jSONObject.get("expiration_date") != null && !jSONObject.get("expiration_date").toString().equals("")) {
                                String dateString = jSONObject.get("expiration_date").toString();
                                LocalDate localDate = LocalDate.parse(dateString);
                                Date sqlDate = Date.valueOf(localDate);
                                oldH.setExpiration_date(sqlDate);           
                                   
                            }
                           
                            boolean status = hasVoucherDAO.updateObject(oldH);
                            if (!status) {
                                    res.put("message", "Thông tin sửa chưa đúng");
                                    JSONHelper.sendJsonAsResponse(response, 400, res);
                                   
                            }
                            else {
                                    JSONHelper.sendJsonAsResponse(response, 200, oldH);
                                    
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
