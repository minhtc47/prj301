package DAO;


import dal.DBConnect;
import entities.staffs;

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
public class DAOStaff extends DBConnect{
    public int addstaffs(staffs sta){
        int n = 0;
        String sql =  "INSERT INTO [dbo].[staffs]\n"
                + "           ([staff_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[active]\n"
                + "           ,[store_id]\n"
                + "           ,[manager_id])\n"
                + "     VALUES\n"
                + "(" + sta.getStaff_id() + ",'"
                + sta.getFirst_name() + "','"
                + sta.getLast_name() + "','"
                + sta.getEmail() + "','"
                + sta.getPhone() + "',"
                + sta.getActive() + ","
                + sta.getStore_id() + ","
                + sta.getManager_id() + ")";
        
        
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
    
    public int getStaffByPrePare(staffs sta) {
    int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n"
                + "           ([staff_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[active]\n"
                + "           ,[store_id]\n"
                + "           ,[manager_id])\n"
                + "     VALUES\n" +"(?,?,?,?,?,?,?,?)";
               
            PreparedStatement pre;
            try{
                pre = conn.prepareStatement(sql);
                pre.setInt(1, sta.getStaff_id());
                pre.setString(2, sta.getFirst_name());
                pre.setString(3, sta.getLast_name());
                pre.setString(4, sta.getEmail());
                pre.setString(5, sta.getPhone());
                pre.setInt(6, sta.getActive());
                pre.setInt(7, sta.getStore_id());
                pre.setInt(8,sta.getManager_id());
                n = pre.executeUpdate();
            
            } catch (SQLException e) {
                Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE,null,e);
                
        }
            return n;
}
    
    public int updatestaffs(staffs sta) {
        int n = 0;
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [first_name] = ?"
                + "      ,[last_name] = ?"
                + "      ,[email] = ?"
                + "      ,[phone] = ?"
                + "      ,[active] = ?"
                + "      ,[store_id] = ?"
                + "      ,[manager_id] = ?"
                + " WHERE [staff_id] = ?";
        
        //run
        PreparedStatement pre;
        
            try {
                pre = conn.prepareStatement(sql);
            
            pre.setString(1, sta.getFirst_name());
            pre.setString(2, sta.getLast_name());
            pre.setString(3, sta.getEmail());
            pre.setString(4, sta.getPhone());
            pre.setInt(5, sta.getActive());
            pre.setInt(6, sta.getStore_id());
            pre.setInt(7, sta.getManager_id());
            pre.setInt(8, sta.getStaff_id());
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
    
    public Vector<staffs> getData(String sql){
        Vector<staffs> vector = new Vector<staffs>();
        
        try {
            //Tao ra Thread Safe
//            synchronized
            
            Statement state = conn.createStatement(
            ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=state.executeQuery(sql);
            while (rs.next()) { 
             
            
            int getStaff_id = rs.getInt(1);
            String First_name = rs.getString(2);
            String Last_name = rs.getString(3);
            String Email = rs.getString(4);
            String Phone = rs.getString(5);
            int Active = rs.getInt(6);
            int Store_id = rs.getInt(7);
            int Manager_id= rs.getInt(8);
            
            
            
            
            staffs sta = new staffs(Store_id, Active, Store_id, Manager_id, First_name, Last_name, Email, Phone);
            vector.add(sta);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOStaff dao= new DAOStaff();
        staffs sta =  new staffs(9, 201, 2, 5,"first_name"," last_name"," email"," phone");
        int n=dao.updatestaffs(sta);
        if(n>0){
            System.out.println("inserted");
        }
        
//        int n=0;
//        n = dao.getStaffByPrePare(sta);
//        if(n>0){
//            System.out.println("inserted");
//        }
    }
}
