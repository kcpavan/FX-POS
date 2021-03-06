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
import com.kcp.pos.utils.KCPUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            prest.setInt(2, item.getInvoiceItemIdFk());
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

   
    
     public int getInvoiceId()
    {
        int auto_id=0;
       try {
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO invoice ("
                   
                    + "invoice_total_items ,"
                    + "invoice_total_amount,"
                    + "invoice_modified_user_id_fk,"
                    + "invoice_modified_date )"
                    +"  VALUES(?,?,?,?) ";
            PreparedStatement prest = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            prest.setInt(1, 1);
            prest.setDouble(2, 0.0);
            prest.setInt(3, 1);
            Timestamp timeStamp=new Timestamp(new Date().getTime());
            prest.setTimestamp(4,timeStamp);
            
            
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
     
      public boolean addInvoiceItem(InvoiceDetails invoiceDetails,Item item)
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
          
            System.out.println("invoiceDetails.getInvoiceItemIdFk():"+invoiceDetails.getInvoiceItemIdFk());
            prest.setInt(2,invoiceDetails.getInvoiceItemIdFk());
            prest.setInt(3, invoiceDetails.getInvoiceItemQuantity());
            prest.setDouble(4,invoiceDetails.getInvoiceItemQuantity()*item.getSellingPrice());
            
            
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
    
       
       public int saveInvoice(List<InvoiceDetails> invoiceDetailsList)
       {
           int returnValue=0;
       try {
           double total=0.0;
           int invoiceIdPk=0;
           for(InvoiceDetails data:invoiceDetailsList)
           {
               total=total+data.getInvoiceItemTotalPrice();
               invoiceIdPk=data.getInvoiceIdFk();
           }
            Connection con = DBConnect.getConnection();
            String sql = "update invoice "
                   
                    + " set  invoice_total ="+total
                    +"  where invoice_id_pk="+invoiceIdPk;
            PreparedStatement prest = con.prepareStatement(sql);
            
            
            returnValue=prest.executeUpdate();
            
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
           return returnValue;
       }
       
       public List<InvoiceDetails> getInvoiceItems(String invoiceNumber)
       {
            List<InvoiceDetails> invoiceDetailsList=new ArrayList<InvoiceDetails>();
            Connection con=null;
            PreparedStatement prest=null;
            Map<Integer,Item> itemsMap=new HashMap<Integer,Item>();
            
       try {
             con = DBConnect.getConnection();
            
               String sql= "select item_id_pk,item_name,item_mrp,item_barcode,item_weight,"
                       + "item_weight_unit,item_actual_price,item_hasfree from items";
               
             prest = con.prepareStatement(sql);
            ResultSet  resultSet= prest.executeQuery();
            while(resultSet.next()){
               
                Item item= new  Item();
                item.setItemId(resultSet.getInt("item_id_pk"));
                item.setName(resultSet.getString("item_name"));
                item.setMrp(Double.valueOf(resultSet.getString("item_mrp")));
                item.setBarcode(resultSet.getString("item_barcode"));
                item.setWeight(resultSet.getDouble("item_weight"));
                item.setWeightUnit(resultSet.getString("item_weight_unit"));
                item.setActualPrice(resultSet.getDouble("item_actual_price"));
                item.setHasGift(resultSet.getBoolean("item_hasfree"));
                itemsMap.put(new Integer(item.getItemId()),item); 
             
            }
       }
       catch(SQLException es)
       { 
           es.printStackTrace();
       }
       
            catch(Exception e)
            {
                e.printStackTrace();
                
            }
       finally
       {
            try {
                con.close();
                prest.close();
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
       }    
       
       
       try{
            
             con = DBConnect.getConnection();
            if(KCPUtils.isNullString(invoiceNumber))
                invoiceNumber="1";
            String sql = "select * from "
                    + " invoice_details where invoice_det_invoice_id_fk="+invoiceNumber;
            System.out.println("sql:"+sql);
            prest = con.prepareStatement(sql);
            
            ResultSet rs=prest.executeQuery();
            InvoiceDetails data=null;
            while(rs.next())
            {
                data=new InvoiceDetails();
                data.setInvoiceDetailsIdPk(rs.getInt(1));
                
                data.setInvoiceIdFk(rs.getInt(2));
                data.setInvoiceItemIdFk(rs.getInt(3));
                data.setInvoiceItemQuantity(rs.getInt(4));
                data.setInvoiceItemTotalPrice(rs.getInt(5));
                
                Item item=itemsMap.get(data.getInvoiceIdFk());
                
                data.setInvoiceItemIdFk(item.getItemId());
        data.setName(item.getName());
        data.setBarcode(item.getBarcode());
        data.setMrp(item.getMrp());
        data.setWeight(item.getWeight());
        
               
                invoiceDetailsList.add(data);
        
                
            }
       }
            catch(SQLException es)
            {
                Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, es);
                es.printStackTrace();;
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
       {
            try {
                prest.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       }   
          
           
            //need to return auto incremented id
        
       return invoiceDetailsList;
       }
       
      
}
