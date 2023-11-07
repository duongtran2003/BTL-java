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
    ProductDAO ad =new ProductDAO();

    @Override
    public Object getById(int order_id) {
        String sql = "select * from orders where order_id = " + order_id;
            try {
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
                if (rs.next()){
		    return new Order(rs.getInt("order_id"), 
                        (Product) ad.getById(rs.getInt("product_id")),
                        rs.getInt("user_id"),
                        rs.getInt("has_voucher_id"),
                        rs.getInt("quantity"),
                        rs.getString("nametag"),
                        rs.getString("color"),
                        rs.getInt("size"),
                        rs.getInt("squad_number"),
                        rs.getDate("date_time"));
                       
                    }
		}
		catch (SQLException e) {

		}
		return null;   
    }
    @Override
    public boolean addObject(Object object) {
        try {
            Order order = (Order)object;
            String sql = "insert into orders( product_id, user_id, has_voucher_id, quantity, nametag,"
                    + " color, size,squad_number, date_time) value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, order.getProduct().getProduct_id());
            st.setInt(2, order.getUser_id());
            st.setInt(3,order.getHas_voucher_id());
            st.setInt(4, order.getQuantity());
            st.setString(5, order.getNametag());
            st.setString(6, order.getColor());
            st.setInt(7, order.getSize());
            st.setInt(8, order.getSquad_number());
            st.setDate(9, order.getDate_time());
            st.executeUpdate();
//            System.out.println("scs");
            return true;
        } catch (SQLException e) {
//            System.out.println(e);
            return false;
        }
    }
    @Override
    public boolean updateObject(Object object) {
        try {
            Order order = (Order)object;
            String sql = "update comments set product_id=?, user_id=?, has_voucher_id=?, quantity=?,"
                    + " nametag=?, color=?, size=?, squad_number=?, date_time=? where order_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, order.getProduct().getProduct_id());
            st.setInt(2, order.getUser_id());
            st.setInt(3,order.getHas_voucher_id());
            st.setInt(4, order.getQuantity());
            st.setString(5, order.getNametag());
            st.setString(6, order.getColor());
            st.setInt(7, order.getSize());
            st.setInt(8, order.getSquad_number());
            st.setDate(9, order.getDate_time());
            st.executeUpdate();      
//            System.out.println("scs");
            return true;
        } catch (SQLException e) {
//            System.out.println(e);
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
        try {
            String sql = "select * from orders";
            PreparedStatement st = con.prepareStatement(sql);
            List<Object> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt("order_id"), (Product) ad.getById(rs.getInt("product_id")), 
                        rs.getInt("user_id"),rs.getInt("has_voucher_id"), rs.getInt("quantity"), rs.getString("nametag"), 
                        rs.getString("color"),rs.getInt("size"),rs.getInt("squad_number"),rs.getDate("date_time")));
                     
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
}
