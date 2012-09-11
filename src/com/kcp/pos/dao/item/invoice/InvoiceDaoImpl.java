/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.item.invoice;

import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;

import com.kcp.pos.modal.Item;
import com.kcp.pos.utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prakash
 */
public class InvoiceDaoImpl implements InvoiceDao {
    
    /*
     * 
     *  private int invoiceIdFk;
    private int itemIdFk;
    private double invoiceItemQuantity;
    private double invoiceItemTotal;
     */
     
     public Invoice addItem(InvoiceDetails item)
     {
         Invoice invoice=null;
         
         try {
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO invoice_details (invoice_id_fk,"
                    + "item_id_fk,"
                    + "quantity,"
                    + "total_amount) VALUES(?,?,?,?) ";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setInt(1, item.getInvoiceIdFk());
            prest.setInt(2, item.getItemIdFk());
            prest.setInt(3, item.getInvoiceItemQuantity());
            prest.setDouble(4, item.getInvoiceItemTotalPrice());
            
            
            int count = prest.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
         return invoice;
     }
    public List<Invoice> getAllItems()
    {
        List<Invoice> invoiceList=new ArrayList<Invoice>();
        
        return invoiceList;
    }
    
   

    @Override
    public Invoice addItems(Invoice item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Invoice> saveInvoice() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
     public int getInvoiceId()
    {
        int auto_id=0;
       try {
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO invoice ("
                   
                    + "invoice_user_id_fk ,"
                    + "invoice_date,"
                    + " invoice_total )"
                    +"  VALUES(?,?,?) ";
            PreparedStatement prest = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            prest.setInt(1, 1);
            Timestamp timeStamp=new Timestamp(new Date().getTime());
            prest.setTimestamp(2,timeStamp);
            prest.setDouble(3, 0.0);
            
            prest.executeUpdate();
            
           ResultSet rs=prest.getGeneratedKeys();
           rs.next();
           auto_id=rs.getInt(1);
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto_id; 
    }
     
      public boolean addInvoiceItem(InvoiceDetails invoiceDetails)
      {
          int auto_id=0;
       try {
           /*
            * invoice_det_invoice_id_fk integer not null,
invoice_det_item_id_fk integer not null,
invoice_det_quantity double not null,
invoice_det_total double not null
            */
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO invoice_details ("
                   
                    + "invoice_det_invoice_id_fk ,"
                    + "invoice_det_item_id_fk,"
                    + " invoice_det_quantity,"
                    + "invoice_det_total )"
                    +"  VALUES(?,?,?,?) ";
            PreparedStatement prest = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            prest.setInt(1, invoiceDetails.getInvoiceIdFk());
          
            prest.setInt(2,invoiceDetails.getItemIdFk());
            prest.setInt(3, invoiceDetails.getInvoiceItemQuantity());
            
            prest.executeUpdate();
            
           ResultSet rs=prest.getGeneratedKeys();
           rs.next();
           auto_id=rs.getInt(1);
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true; 
      }
      
       public Invoice getInvoiceById()
       {
           Invoice invoice=null;
       try {
        /*
         * invoice_id_pk integer primary key not null auto_increment,
invoice_total_items integer not null,
invoice_user_id_fk integer not null,
invoice_date timestamp not null,
invoice_total_amount double not null
         */
           String name=null;
            Connection con = DBConnect.getConnection();
            String sql = "select item_selling_price from"
                    + " invoice where item_name="+name;
            
            PreparedStatement prest = con.prepareStatement(sql);
            
            ResultSet rs=prest.executeQuery();
            while(rs.next())
            {
                invoice.setSellingPrice(rs.getInt(1));
            }
            prest.close();
            con.close();
           
          
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
       return invoice;
       }
    

}
