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
public class DAOProduct extends DBConnect{
    //dao: database acess object
    //insert, update, delete, getData,find;
    public int addProduct(Product pro){
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
"           ("+pro.getProduct_id()+",'demo',2022,100,'Trek','Mountain Bikes')\n";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public int getProductByPrepare(Product pro){
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
    public void update(Product pro){
        int n = 0;
        String sql = 
"UPDATE [dbo].[products]\n" +
"   SET \n" +
"      ,[product_name] = ?\n" +
"      ,[model_year] = ?\n" +
"      ,[list_price] = ?\n" +
"      ,[brand_name] = ?\n" +
"      ,[category_name] = ?\n" +
" WHERE [product_id] = ?\n" +
"GO";
        
        try {
            PreparedStatement pre ;
        pre = conn.prepareStatement(sql);
        pre.setInt(1, pro.getProduct_id());
            pre.setString(2, pro.getProduct_name());
            pre.setInt(3, pro.getModel_year());
            pre.setInt(4, pro.getList_price());
            pre.setString(5, pro.getBrand_name());
            pre.setString(6, pro.getCategory_name());
            ResultSet rs= pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int deleteProduct(int id){
        int n = 0;
        String sql="delete from products where product_id ="+ id;
        Statement state;
        //StringBuilder,StringBuffer
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            n=state.executeUpdate(sql);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
     public Vector<Product> getDataFromProduct(String sql){
        Vector<Product> vector = new Vector<Product>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);//int id = rs.getInt("product_id");
                String name = rs.getString(2);
                int model = rs.getInt("model_year");
                int price = rs.getInt("list_price");
                String brand = rs.getString("brand_name");
                String category = rs.getString("category_name");
                Product pro = new Product(id, model, price, name, brand, category);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }return vector;
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
    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        
//        int n = dao.deleteProduct(1000);
       
//        Vector<Product> vec = dao.getDataFromProduct("select * from Products");
        Vector<customers> vec2 = dao.getDataFromCusomners("select * from customers");
        for (customers cus : vec2) {
            System.out.println(cus.toString());
        }
        
    }
}
