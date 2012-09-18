/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.invoice;

import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.dao.stocks.StocksDao;
import com.kcp.pos.dao.stocks.StocksDaoImpl;

import com.kcp.pos.modal.invoice.Invoice;
import com.kcp.pos.modal.invoice.InvoiceDetails;

import com.kcp.pos.modal.item.Item;
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
    public List<InvoiceDetails> getAllItems()
    {
        List<InvoiceDetails> invoiceDetailsList=new ArrayList<InvoiceDetails>();
         try {
               Connection con = DBConnect.getConnection();
               String sql= "select  invoice_det_id_pk,"
                       + "invoice_det_invoice_id_fk ,"+
                       "invoice_det_item_id_fk," +
                   " invoice_det_quantity,"+
                    "invoice_det_total  "
                       + "from invoice_details";
               
            PreparedStatement prest = con.prepareStatement(sql);
            ResultSet  resultSet= prest.executeQuery();
            while(resultSet.next()){
               
                InvoiceDetails invoceDet= new  InvoiceDetails();
                invoceDet.getItem().setName(resultSet.getString("item_name"));
                invoceDet.getItem().setMrp(Double.valueOf(resultSet.getString("item_mrp")));
                invoceDet.getItem().setBarcode(resultSet.getString("item_barcode"));
                invoceDet.getItem().setWeight(resultSet.getDouble("item_weight"));
                invoceDet.getItem().setWeightUnit(resultSet.getString("item_weight_unit"));
                invoceDet.getItem().setActualPrice(resultSet.getDouble("item_actual_price"));
                invoceDet.getItem().setBillingPrice(resultSet.getDouble("item_selling_price"));
                
                invoceDet.getItem().setHasGift(resultSet.getBoolean("item_hasfree"));
             
                
                System.out.println("invoceDet.getName():"+invoceDet.getItem().getName());
                
                invoceDet.itemName.set(invoceDet.getItem().getName());
                invoceDet.itemBarcode.set(invoceDet.getItem().getBarcode());
                invoceDet.itemMrp.set(invoceDet.getItem().getMrp());
                System.out.println("item.getWeight():"+invoceDet.getItem().getWeight());
                invoceDet.itemWeight.set(invoceDet.getItem().getWeight());
                invoceDet.itemWeightUnit.set(invoceDet.getItem().getWeightUnit());
                invoceDet.itemActualPrice.set(invoceDet.getItem().getActualPrice());
                invoceDet.itemBillingPrice.set(invoceDet.getItem().getBillingPrice());
                invoceDet.itemHasFree.set(invoceDet.getItem().isHasGift());
                
                invoiceDetailsList.add(invoceDet); 
            } 
            
         } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return invoiceDetailsList;
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
            System.out.println("item id:"+item.getItemId());
            prest.setInt(2,item.getItemId());
            prest.setInt(3, invoiceDetails.getInvoiceItemQuantity());
            prest.setDouble(4,invoiceDetails.getInvoiceItemQuantity()*item.getBillingPrice());
            
            
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
                invoice.setInvoiceNumber(rs.getInt(1));
            }
            prest.close();
            con.close();
           
          
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
       return invoice;
       }
    
       
       /*public int saveInvoice(List<InvoiceDetails> invoiceDetailsList)
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
       */
       
       public int saveInvoice(List<InvoiceDetails> invoiceDetailsList)
       {
           int returnValue=0;
           StocksDao stocksDao=new StocksDaoImpl();
           
       try {
           double total=0.0;
           int invoiceIdPk=0;
           for(InvoiceDetails data:invoiceDetailsList)
           {
               //public int updateStocksOnInvoice(int itemId,int quantity)
               stocksDao.updateStocksOnInvoice(data.getInvoiceItemIdFk(),data.getInvoiceItemQuantity());
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
                       + "item_weight_unit,item_actual_price,item_selling_price,item_hasfree from items";
               
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
                item.setBillingPrice(resultSet.getDouble("item_selling_price"));
                
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
                
                
                data.setItem(new Item());
                
                Item item=itemsMap.get(data.getInvoiceItemIdFk());
                
                data.setInvoiceItemIdFk(item.getItemId());
                data.getItem().setName(item.getName());
                data.getItem().setBarcode(item.getBarcode());
                data.getItem().setMrp(item.getMrp());
                data.getItem().setWeight(item.getWeight());
                data.getItem().setBillingPrice(item.getBillingPrice());
        
                data.itemName.set(item.getName());
                data.itemBarcode.set(item.getBarcode());
                data.itemMrp.set(item.getMrp());
                System.out.println("**item.getSellingPrice():"+item.getBillingPrice());
                data.itemWeight.set(item.getWeight());
                data.itemQuantity.set(data.getInvoiceItemQuantity());
                data.itemWeightUnit.set(item.getWeightUnit());
                data.itemActualPrice.set(item.getActualPrice());
                data.itemBillingPrice.set(item.getBillingPrice());
                data.itemHasFree.set(item.isHasGift());
                
                Double totalAmount=item.getBillingPrice()*data.getInvoiceItemQuantity();
                data.setInvoiceItemTotalPrice(totalAmount);     
                data.itemTotalAmount.set(totalAmount);
        
        
        
        
               
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
       
       
       public int deleteInvoiceItem(int invoiceId,int itemId)
       {
             
           
           
           
           int result=0;
       try {
            Connection con = DBConnect.getConnection();
            
            
            
               String sql= "delete from invoice_details where  invoice_det_invoice_id_fk="+invoiceId
                       +" invoice_det_item_id_fk="+itemId;
               
            
                PreparedStatement prest = con.prepareStatement(sql);
         
             result=prest.executeUpdate();
            
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result; 
       }
      
}
