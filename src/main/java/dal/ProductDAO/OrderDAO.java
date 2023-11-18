/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.ProductDAO;
import Model.Product.Order;
import Model.Product.Product;
import dal.DAO.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DELL
 */
public class OrderDAO extends DAO{

    @Override
    public Object getById(int order_id) {
          
    }



    @Override
    public boolean addObject(Object object) {
    }
    

    @Override
    public boolean updateObject(Object object) {
    

    }


    @Override
    public boolean deleteObject(int objectId) {
        try {
            String sql = "delete from orders where order_id = " + objectId;
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
//            System.out.println(e);
            return false;
        }
    }
    @Override
    public List<Object> getAllObjects() {
    
    }
    
}
