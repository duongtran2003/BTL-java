/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.ProductDAO;
import Model.Product.Order;
import Model.Product.Product;
import Model.Product.ProductOrder;
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


    public OrderDAO() {
        super();
    }

    @Override
    public Object getById(int order_id) {
        List<ProductOrder> entries = new ProductOrderDAO().getByOrderId(order_id);
        try {
            String sql = "select * from orders where order_id = " + order_id;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order newOrder = new Order(rs.getInt("order_id"), rs.getInt("user_id"), entries, 0);
                newOrder.setDate(rs.getTimestamp("date"));
                newOrder.setStatus(rs.getInt("status"));
                newOrder.setDiscounted(rs.getInt("discounted"));
                return newOrder;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return null;
    }

    public List<Order> getByUserId(int user_id) {
        
        List<Order> res = new ArrayList<> ();
        try {
            List<Integer> orders = new ArrayList<> ();
            String sql = "select * from orders where user_id = " + user_id;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orders.add(rs.getInt("order_id"));
            }
            for (int orderId: orders) {
                res.add((Order) this.getById(orderId));
            }
            return res;
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean addObject(Object object) {
        // {
        //     "user_id": ,
        //     "products": 
        //         [
        //             {
        //                 "product_id": int,
        //                 "nametag": String,
        //                 "color": String,
        //                 "size": int, // 0 - S, 1 - M, 2 - L, 3 - XL, 4 - XXL
        //                 "squad_number": int,
        //                 "quantity": int,
        //             }, 
        //              ...
        //         ]
        // }

        try {
            Order order = (Order) object;
            String sql = "insert into orders(user_id, status) value (?, 0)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, order.getUser_id());
            st.executeUpdate();
            ProductOrderDAO prodOrderDAO = new ProductOrderDAO();
            for (ProductOrder entry: order.getEntries()) {
                prodOrderDAO.addObject(entry);
            }
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }
    

    @Override
    public boolean updateObject(Object object) {
        // Chi cho phep update trang thai cua order
        try {
            Order newOrder = (Order) object;
            String sql = "insert into orders(status) value (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, newOrder.getStatus());
            st.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
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
        //todo
        return null;
    }
    
}
