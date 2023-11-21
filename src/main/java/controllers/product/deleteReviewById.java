

package controllers.product;

import static common.product.Constant.URL_REVIEW_DELETE_BY_ID;
import dal.ProductDAO.ReviewDAO;
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
@WebServlet(name="deleteReviewById", urlPatterns={URL_REVIEW_DELETE_BY_ID})
public class deleteReviewById extends HttpServlet {
   
    // xoá Review theo id
    private ReviewDAO reviewDAO=new ReviewDAO();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         Map<String, Object> res = new HashMap<> ();
        try {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            boolean isSuccess = reviewDAO.deleteObject(id);
            if (isSuccess) {
                    res.put("message", "Success");	
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}