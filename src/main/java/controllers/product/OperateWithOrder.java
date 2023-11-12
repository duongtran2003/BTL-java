/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.product;

import Model.Product.Order;
import Model.Product.Product;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import common.product.Constant;
import dal.ProductDAO.OrderDAO;
import dal.ProductDAO.ProductDAO;
import helper.JSONHelper;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name="OperateWithOrder", urlPatterns={Constant.URL_ORDER_ADD,Constant.URL_ORDER_DELETE,Constant.URL_ORDER_GET})
public class OperateWithOrder extends HttpServlet {
   
    private Gson gson=new Gson();
    private SimpleDateFormat date=new SimpleDateFormat("yyyy/MM/dd");
    private OrderDAO orderDAO=new OrderDAO();
    private ProductDAO productDAO =new ProductDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String urlPath=request.getServletPath();
            switch (urlPath) {
                case Constant.URL_ORDER_ADD:
                    addOrder(request,response);
                    break;
                    
                case Constant.URL_ORDER_DELETE:
                    deleteOrder(request,response);
                    break;
                case Constant.URL_ORDER_GET:
                    getOrder(request,response);
                    break;
                    
            }
        } catch (ParseException ex) {
            Logger.getLogger(OperateWithOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void getOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(request.getParameter("oder_id"));
            Order order = (Order) orderDAO.getById(id);
            JSONHelper.sendJsonAsResponse(response, 200, order);
        } 
        catch (NumberFormatException e) {
                res.put("message", "bad request, check url params again");
                response.setStatus(400);
                JSONHelper.sendJsonAsResponse(response, 400, res);
        }
    }
    
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> res = new HashMap<> ();
        try {
            int id=Integer.parseInt(request.getParameter("order_id"));
            boolean isSuccess = orderDAO.deleteObject(id);
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
    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
        Map<String, Object> res = new HashMap<> ();
        try{
            int id=Integer.parseInt(request.getParameter("order_id"));
            Product product =(Product) productDAO.getById(Integer.parseInt("product_id"));
            int user_id=Integer.parseInt(request.getParameter("user_id"));
            int has_voucher_id=Integer.parseInt(request.getParameter("has_voucher_id"));
            int quantity=Integer.parseInt(request.getParameter("quantity"));
            String nametag=request.getParameter("nametag");
            String color=request.getParameter("color");
            int size=Integer.parseInt(request.getParameter("size"));
            int squad_number=Integer.parseInt(request.getParameter("squad_number"));
            Date date_time=new Date(System.currentTimeMillis());
            
            
            Order order=new Order(id,product,user_id,has_voucher_id,quantity,nametag,color,size,squad_number,date_time);
            boolean isSuccess = orderDAO.addObject(order);
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
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
