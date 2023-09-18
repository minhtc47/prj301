package DAO;



import dal.DBConnect;
import entities.stores;
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
public class DAOStore extends DBConnect{
    public int addstores(stores stor){
        int n = 0;
        String sql =    "INSERT INTO [dbo].[stores]\n" +
"           ([store_id]\n" +
"           ,[store_name]\n" +
"           ,[phone]\n" +
"           ,[email]\n" +
"           ,[street]\n" +
"           ,[city]\n" +
"           ,[state]\n" +
"           ,[zip_code])\n" +
"     VALUES\n" +
            "("+stor.getStore_id()+",'"
            +stor.getStore_name()+"','"
            +stor.getPhone()+"','"
            +stor.getEmail()+"','"
            +stor.getStreet()+"','"
            +stor.getCity()+"','"
            +stor.getState()+"','"
            +stor.getZip_code()+"')";
                   
        //Statement
        Statement state;

        try {
            state = conn.createStatement();
            //run
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public int getStockByPrePare(stores stor) {
    int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n" +
"           ([store_id]\n" +
"           ,[store_name]\n" +
"           ,[phone]\n" +
"           ,[email]\n" +
"           ,[street]\n" +
"           ,[city]\n" +
"           ,[state]\n" +
"           ,[zip_code])\n" +
"     VALUES\n" +
            "(?,?,?,?,?,?,?,?";   
               
            PreparedStatement pre;
            try{
                pre = conn.prepareStatement(sql);
                pre.setInt(1, stor.getStore_id());
                pre.setString(2, stor.getStore_name());
                pre.setString(3, stor.getPhone());
                pre.setString(4, stor.getEmail());
                pre.setString(5, stor.getStreet());
                pre.setString(6, stor.getCity());
                pre.setString(7, stor.getState());
                pre.setString(8, stor.getZip_code());
                n = pre.executeUpdate();
            
            } catch (SQLException e) {
                Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE,null,e);
                
        }
            return n;
}
    
    public int updateStaff(stores stor) {
        int n = 0;
        String sql = "UPDATE [dbo].[stores]\n"
                + "   SET [store_name] = ?"
                + "      ,[phone] = ?"
                + "      ,[email] = ?"
                + "      ,[street] = ?"
                + "      ,[city] = ?"
                + "      ,[state] = ?"
                + "      ,[zip_code] = ?n"
                + " WHERE [store_id] = ?";
        
        //run
        PreparedStatement pre;
        
            try {
                pre = conn.prepareStatement(sql);
            
            pre.setString(1, stor.getStore_name());
            pre.setString(2, stor.getPhone());
            pre.setString(3, stor.getEmail());
            pre.setString(4, stor.getStreet());
            pre.setString(5, stor.getCity());
            pre.setString(6, stor.getState());
            pre.setString(7, stor.getZip_code());
            pre.setInt(8, stor.getStore_id());
            
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
    
    public Vector<stores> getData(String sql){
        Vector<stores> vector = new Vector<stores>();
        
        try {
            //Tao ra Thread Safe
//            synchronized
            
            Statement state = conn.createStatement(
            ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=state.executeQuery(sql);
            while (rs.next()) { 
            
          
            
            int stores_id = rs.getInt(1);
            String stores_name = rs.getString(2);
            String Phone = rs.getString(3);
            String Email = rs.getString(4);
            String Street = rs.getString(5);
            String City = rs.getString(6);
            String State = rs.getString(7);
            String Zip_code = rs.getString(8);
            
            
            
            
            stores stor = new stores(stores_id, stores_name, Phone, Email, Street, City, State, Zip_code);
            vector.add(stor);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOStore dao= new DAOStore();
        stores stor =  new stores(4, "first_name"," last_name"," email"," phone"," 1003"," 1002", "1004");
        int n=dao.addstores(stor);
        if(n>0){
            System.out.println("inserted");
        }
        
//        int n=0;
//        n = dao.getStoreByPrePare(stor);
//        if(n>0){
//            System.out.println("inserted");
//        }
    }
}
