package DAO;




import dal.DBConnect;
import entities.order_items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;





/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MH
 */
public class DAOOrderItem extends DBConnect{
    public int addOrderItem(order_items odI){
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n" +
"           ([order_id]\n" +
"           ,[item_id]\n" +
"           ,[product_id]\n" +
"           ,[quantity]\n" +
"           ,[list_price]\n" +
"           ,[discount])\n" +
"     VALUES\n" +
                "("+odI.getOrder_id()+","
                +odI.getItem_id()+","
                +odI.getProduct_id()+","
                +odI.getQuantity()+","
                +odI.getList_price()+","
                +odI.getDiscount()+")";
        
        //Statement
        Statement state;
        try {
            state = conn.createStatement();
            //run
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;

    }
    
    public int getOrderItemByPrePare(order_items odI) {
    int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n" +
"           ([order_id]\n" +
"           ,[item_id]\n" +
"           ,[product_id]\n" +
"           ,[quantity]\n" +
"           ,[list_price]\n" +
"           ,[discount])\n" +
"     VALUES\n" + "(?,?,?,?,?,?)";
           
        
            PreparedStatement pre;
        
            try {
                pre = conn.prepareStatement(sql);
            pre.setInt(1 ,odI.getOrder_id());
            pre.setInt(2 ,odI.getItem_id());
            pre.setInt(3 ,odI.getProduct_id());
            pre.setInt(4 ,odI.getQuantity());
            pre.setInt(5 ,odI.getList_price());
            pre.setInt(6 ,odI.getDiscount());
            
            
            n = pre.executeUpdate();
            
            } catch (SQLException e) {
                Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE,null,e);
                
        }
            return n;
}
    
    
    public int updateOrderItem(order_items odI) {
        int n = 0;
        String sql = "UPDATE [dbo].[order_items]\n"
                + "   SET [item_id] = ?"
                + "      ,[product_id] = ?"
                + "      ,[quantity] = ?"
                + "      ,[list_price] = ?"
                + "      ,[discount] = ?"
                + " WHERE [order_id] = ?";
        
        //run
        PreparedStatement pre;
        
            try {
                pre = conn.prepareStatement(sql);
            
            pre.setInt(1, odI.getItem_id());
            pre.setInt(2, odI.getProduct_id());
            pre.setInt(3, odI.getQuantity());
            pre.setInt(4, odI.getList_price());
            pre.setInt(5, odI.getDiscount());
            pre.setInt(6, odI.getOrder_id());
            n = pre.executeUpdate();
            
            } catch (SQLException e) {
                e.printStackTrace();
                
            }
        return n;
    }
    
    public int deleteOrderItem(int id){
        int n = 0;
        String sql = "Delete from products where OrderItem_ID="+id;
        try {
            Statement state = conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }   
    
    public Vector<order_items> getData(String sql){
        Vector<order_items> vector = new Vector<order_items>();
        
        try {
            //Tao ra Thread Safe
//            synchronized
            
            Statement state = conn.createStatement(
            ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=state.executeQuery(sql);
            while (rs.next()) { 
             
            int order_id = rs.getInt(1);
            int item_id = rs.getInt(2);
            int product_id = rs.getInt(3);
            int quantity = rs.getInt(4);
            int list_price = rs.getInt(5);
            int discount = rs.getInt(6);
            
            
            
            order_items odI = new order_items(order_id, item_id, product_id, quantity, list_price, discount);
            vector.add(odI);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOOrderItem dao = new DAOOrderItem();
//        order_items odI = new order_items(50, 1112, 1003,1114, 6, 7);
//        int n=dao.addOrderItem(odI);

//        if(n>0){
//            System.out.println("inserted");
//        }
        Vector<order_items> items = dao.getData("select * from order_items");
        for (order_items item : items) {
            System.out.println(item.toString());
        }
//        int n=0;
//        n = dao.getOrderItemByPrePare(odI);
//        if(n>0){
//            System.out.println("inserted");
//        }
    }
    
    
    
}
