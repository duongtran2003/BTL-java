/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.voucherDAO;
import Model.Voucher.HasVoucher;
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

public class HasVoucherDAO extends DAO{
    @Override
    public Object getById(int has_Voucher_id) {
        String sql = "select * from has_vouchers where has_voucher_id = " + has_Voucher_id;
            try {
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
                if (rs.next()){
		    return new HasVoucher(rs.getInt("has_voucher_id"), 
                        rs.getInt("user_id"),
                        rs.getInt("voucher_id"),
                        rs.getDate("expiration_date"));
                       
                    }
		}
		catch (SQLException e) {

		}
		return null;   
    }
    @Override
    public boolean addObject(Object newHasVoucher) {
        try {
                HasVoucher hasVoucher = (HasVoucher) newHasVoucher;
                String sql = "insert into has_vouchers(user_id, voucher_id, expiration_date)"
                        + " value(?, ?, ?)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1,hasVoucher.getUser_id());
                st.setInt(2,hasVoucher.getVoucher_id());
                st.setDate(3,  hasVoucher.getExpiration_date());
                st.executeUpdate();
                return true;
		}
	catch (SQLException e) {
            return false;
	}
    }
    @Override
    public boolean updateObject(Object newHasVoucher){
        try{
                HasVoucher hasVoucher = (HasVoucher) newHasVoucher;
                String sql = "update into has_vouchers(user_id=?, voucher_id=?, expiration_date=?  where has_voucher_id= "+ hasVoucher.getHas_voucher_id();
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1,hasVoucher.getUser_id());
                st.setInt(2,hasVoucher.getVoucher_id());
                st.setDate(3,hasVoucher.getExpiration_date());
                st.executeUpdate();
                return true;
		}
	catch (SQLException e) {
            return false;
	}
    }
    
    @Override
    public boolean deleteObject(int has_Voucher_id) {
        try {
                String sql = "delete from has_vouchers where has_voucher_id = " + has_Voucher_id;
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

            return null;
    }
    public List<HasVoucher> queryObjects() {
        try {
            String sql = "select * from has_vouchers";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<HasVoucher> o = new ArrayList<HasVoucher> ();
            while (rs.next()) {
                    o.add(new HasVoucher(rs.getInt("has_voucher_id"), 
                        rs.getInt("user_id"),
                        rs.getInt("voucher_id"),
                        rs.getDate("expiration_date")));
                       
            }
            return o;
        }
        catch (SQLException e) {
                return null;
        }
    }  
}
