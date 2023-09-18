package DAO;




import dal.DBConnect;
import entities.orders;
import java.sql.Date;
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
public class DAOOrder extends DBConnect{
    public int addorders(orders od){
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n" +
"           ([order_id]\n" +
"           ,[customer_id]\n" +
"           ,[order_status]\n" +
"           ,[order_date]\n" +
"           ,[required_date]\n" +
"           ,[shipped_date]\n" +
"           ,[store_id]\n" +
"           ,[staff_id])\n" +
"     VALUES\n" + 
                "("+ od.getOrder_id()+","
                +od.getCustomer_id()+","
                +od.getOrder_status()+","
                +od.getOrder_date()+","
                +od.getRequired_date()+","
                +od.getShipped_date()+","
                +od.getStore_id()+","
                +od.getStaff_id()+")"
;
        
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
    
     public int getOrderByPrePare(orders od) {
    int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n" +
"           ([order_id]\n" +
"           ,[customer_id]\n" +
"           ,[order_status]\n" +
"           ,[order_date]\n" +
"           ,[required_date]\n" +
"           ,[shipped_date]\n" +
"           ,[store_id]\n" +
"           ,[staff_id])\n" +
"     VALUES\n" + "(?,?,?,?,?,?,?,?)";
               
            PreparedStatement pre;
            try{
                pre = conn.prepareStatement(sql);
                pre.setInt(1, od.getOrder_id());
                pre.setInt(2, od.getCustomer_id());
                pre.setInt(3, od.getOrder_status());
                pre.setDate(4, od.getOrder_date());
                pre.setDate(5, od.getRequired_date());
                pre.setDate(6, od.getShipped_date());
                pre.setInt(7, od.getStore_id());
                pre.setInt(8, od.getStaff_id());
                n = pre.executeUpdate();
            
            } catch (SQLException e) {
                Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE,null,e);
                
        }
            return n;
}
     
public int updateorders(orders od) {
        int n = 0;
       String sql = "UPDATE [dbo].[orders]\n"
            + "   SET "
            + "      ,[customer_id] = ?"
            + "      ,[order_status] = ?"
            + "      ,[order_date] = ?"
            + "      ,[required_date] = ?"
            + "      ,[shipped_date] = ?"
            + "      ,[store_id] = ?"
            + "      ,[staff_id] = ?"
            + " WHERE [order_id] = ?";
        
        //run
        PreparedStatement pre;
        
            try {
                pre = conn.prepareStatement(sql);
            
            pre.setInt(1, od.getCustomer_id());
            pre.setInt(2, od.getOrder_status());
            pre.setDate(3, od.getOrder_date());
            pre.setDate(4, od.getRequired_date());
            pre.setDate(5, od.getShipped_date());
            pre.setInt(6, od.getStore_id());
            pre.setInt(7, od.getStaff_id());
            pre.setInt(8, od.getOrder_id());
            n = pre.executeUpdate();
            
            } catch (SQLException e) {
                e.printStackTrace();
                
            }
        return n;
    } 

public int deleteorders(int id){
        int n = 0;
        String sql = "Delete from products where orders_ID="+id;
        try {
            Statement state = conn.createStatement();
            n=state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }   
    
    public Vector<orders> getData(String sql){
        Vector<orders> vector = new Vector<orders>();
        
        try {
            //Tao ra Thread Safe
//            synchronized
            
            Statement state = conn.createStatement(
            ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=state.executeQuery(sql);
            while (rs.next()) { 
            
            int orders_id = rs.getInt(1);    
            int Customer_id = rs.getInt(2);
            int orders_status=  rs.getInt(3);
            Date order_date = rs.getDate(4);
            Date required_date = rs.getDate(5);
            Date shipped_date = rs.getDate(6);
            int Store_id = rs.getInt(7);
            int getStaff_id = rs.getInt(8);
            
            
            
            
            orders od = new orders(orders_id, Customer_id, orders_status, Store_id, getStaff_id, order_date, required_date, shipped_date);
            vector.add(od);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
      public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();
//        orders od = new orders(1013, 1112, 1003,1114, 6, 7,9,10);
//        int n=dao.addorders(od);
//        if(n>0){
//            System.out.println("inserted");
//        }
//        
//        int n=0;
//        n = dao.getOrderItemByPrePare(odI);
//        if(n>0){
//            System.out.println("inserted");
//        }
        Vector<orders> orders = dao.getData("select * from orders");
          for (orders order : orders) {
              System.out.println(order.toString());
          }
    }
}
