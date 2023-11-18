/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.ProductDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Product.ProductOrder;
import dal.DAO.DAO;

/**
 *
 * @author pc
 */
public class ProductOrderDAO extends DAO {

    public ProductOrderDAO() {
        super();
    }

    @Override
    public Object getById(int objectId) {
        try {
            String sql = "select * from products_orders where products_orders_id = " + objectId;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new ProductOrder(rs.getInt("products_orders_id"), rs.getInt("product_id"), rs.getInt("order_id"), rs.getInt("quantity"));
            }
        }
        catch (SQLException e) {
            return null;
        }
        return null;
    }


    public List<ProductOrder> getByOrderId(int orderId) {
        try {
            String sql = "select * from products_orders where order_id = " + orderId;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<ProductOrder> res = new ArrayList<> ();
            while (rs.next()) {
                res.add(new ProductOrder(rs.getInt("product_order_id"), rs.getInt("product_id"), rs.getInt("order_id"), rs.getInt("quantity")));
            }
            return res;
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean addObject(Object object) {
        try {
            ProductOrder prodOrder = (ProductOrder) object;
            String sql = "insert into products_orders (product_id, order_id, quantity) value (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, prodOrder.getProduct_id());
            st.setInt(2, prodOrder.getOrder_id());
            st.setInt(3, prodOrder.getQuantity());
            st.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    @Override 
    public boolean updateObject(Object object) {
        try {
            ProductOrder newProdOrder = (ProductOrder) object;
            String sql = "update products_orders set quantity = ? where product_order_id = " + newProdOrder.getProduct_order_id();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, newProdOrder.getQuantity());
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
            String sql = "delete from products_orders where product_order_id = " + objectId;
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

	@Override 
	public List<Object> getAllObjects() {
		// ko cast duoc
		return null;
	}
}
