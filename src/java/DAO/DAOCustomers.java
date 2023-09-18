/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import dal.DBConnect;
import entities.Product;
import entities.customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author H
 */
public class DAOCustomers extends DBConnect{
    public int getCustomersByPrepare(Product pro){
        int n = 0;
        String sql = 
"INSERT INTO [dbo].[products]\n" +
"           ([product_id]\n" +
"           ,[product_name]\n" +
"           ,[model_year]\n" +
"           ,[list_price]\n" +
"           ,[brand_name]\n" +
"           ,[category_name])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?)\n";
        PreparedStatement pre ;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, pro.getProduct_id());
            pre.setString(2, pro.getProduct_name());
            pre.setInt(3, pro.getModel_year());
            pre.setInt(4, pro.getList_price());
            pre.setString(5, pro.getBrand_name());
            pre.setString(6, pro.getCategory_name());
            //run
            n=pre.executeUpdate();
            ResultSet rs= pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public Vector<customers> getDataFromCusomners(String sql){
        Vector<customers> vector = new Vector<customers>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);//int id = rs.getInt("product_id");
                String fisrt_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state_ = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                customers cus = new customers(id, fisrt_name, last_name, phone, email, street, city, state_, zip_code);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }return vector;
    }
}
