package DAO;



import dal.DBConnect;
import entities.stocks;
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
public class DAOStock extends DBConnect {

    public int addStook(stocks sto) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stocks]\n"
                + "           ([store_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n" + "(" + sto.getStore_id() + ","
                + sto.getProduct_id() + ","
                + sto.getQuantity() + ")";
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
    
     public int getstocksByPrePare(stocks sto) {
    int n = 0;
        String sql =     "INSERT INTO [dbo].[stocks]\n"
                + "           ([store_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n" + "(?,?,?)" ;
               
            PreparedStatement pre;
            try{
                pre = conn.prepareStatement(sql);
                pre.setInt(1, sto.getStore_id());
                pre.setInt(2, sto.getProduct_id());
                pre.setInt(3, sto.getQuantity());
                n = pre.executeUpdate();
            
            } catch (SQLException e) {
                Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE,null,e);
                
        }
            return n;
}
     
    public int updateStaff(stocks sto) {
        int n = 0;
        String sql = "UPDATE [dbo].[stocks]\n"
                 + "   SET [product_id] = ?"
                 + "      ,[quantity] = ?"
                 + " WHERE [store_id] = ?";
        
        //run
        PreparedStatement pre;
        
            try {
                pre = conn.prepareStatement(sql);
            
            pre.setInt(1, sto.getProduct_id());
            pre.setInt(2, sto.getQuantity());
            pre.setInt(3, sto.getStore_id());
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
    
    public Vector<stocks> getData(String sql){
        Vector<stocks> vector = new Vector<stocks>();
        
        try {
            //Tao ra Thread Safe
//            synchronized
            
            Statement state = conn.createStatement(
            ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=state.executeQuery(sql);
            while (rs.next()) { 
            
            int Store_id = rs.getInt(1);
            int Product_id = rs.getInt(2);
            int Quantity = rs.getInt(3);
                
            
            
            
            
            stocks sto = new stocks(Store_id, Product_id, Quantity);
            vector.add(sto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOStock dao = new DAOStock();
        Vector<stocks> stocks = dao.getData("select * from stocks");
        for (stocks stock : stocks) {
            System.out.println(stock.toString());
        }
    }
}
