/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.DAO;

/**
 *
 * @author pc
 */
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
/**
 *
 * @author admin
 */
abstract class DAO {
    protected static Connection con ;
    public DAO (){
        if(con == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
                String username = "root";
                String password = "nvhanh263";
                con = DriverManager.getConnection(url,username,password);
//                System.out.println("sucessfully");
            }
            catch(Exception e){
//                System.out.println("FAIL");
            }
        }
    }
    
//    public static void main(String[] args) {
//        DAO d = new DAO();
//    }
    public abstract Object getById(int Record_id);
    public abstract void addObject(Object object);
    public abstract void updateObject(Object object);
    public abstract void deleteObject(int objectId);
    public abstract List<Object> getAllObjects();
}