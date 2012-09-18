/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kcp.pos.dao.item;

import com.kcp.pos.modal.invoice.InvoiceDetails;
import com.kcp.pos.modal.item.Item;
import com.kcp.pos.utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author K.C.Pavan
 */
public class ItemDaoImpl implements ItemDao{

    public ItemDaoImpl() {
    }
    
    

    @Override
    public Item saveItems(Item item) {
        try {
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO items (item_name,item_barcode,item_mrp,item_weight,item_actual_price,"
                    + "item_selling_price,item_weight_unit,item_hasfree,item_modified_user_id_fk,item_modified_date) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, item.getName());
            prest.setString(2, item.getBarcode());
            prest.setDouble(3, item.getMrp());
            prest.setDouble(4, item.getWeight());
            prest.setDouble(5, item.getActualPrice());
            prest.setDouble(6, item.getBillingPrice());
            prest.setString(7, item.getWeightUnit());
            prest.setInt(8, 0);
            prest.setInt(9,1);
            
            Timestamp timeStamp=new Timestamp(new Date().getTime());
                
            prest.setTimestamp(10, timeStamp);
            
         
            System.out.println("sql:"+sql);
            
            int count = prest.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items=new ArrayList<Item>();
         try {
               Connection con = DBConnect.getConnection();
               String sql= "select item_name,item_mrp,item_barcode,item_weight,"
                       + "item_weight_unit,item_actual_price,"
                       + "item_selling_price,item_hasfree from items";
               
               System.out.println("sql:"+sql);
               
            PreparedStatement prest = con.prepareStatement(sql);
            ResultSet  resultSet= prest.executeQuery();
            while(resultSet.next()){
               
                Item item= new  Item();
                item.setName(resultSet.getString("item_name"));
                item.setMrp(Double.valueOf(resultSet.getString("item_mrp")));
                item.setBarcode(resultSet.getString("item_barcode"));
                item.setWeight(resultSet.getDouble("item_weight"));
                item.setWeightUnit(resultSet.getString("item_weight_unit"));
                item.setActualPrice(resultSet.getDouble("item_actual_price"));
                item.setBillingPrice(resultSet.getDouble("item_selling_price"));
                
                item.setHasGift(resultSet.getBoolean("item_hasfree"));
              System.out.println("item.getname():"+item.getName());
              System.out.println("item.getBarcode():"+item.getBarcode());
                
                
                
                item.itemName.set(item.getName());
                item.itemBarcode.set(item.getBarcode());
                item.itemMrp.set(item.getMrp());
                System.out.println("item.getMrp():"+item.getMrp());
                item.itemWeight.set(item.getWeight());
                item.itemWeightUnit.set(item.getWeightUnit());
                item.itemActualPrice.set(item.getActualPrice());
                item.itemBillingPrice.set(item.getBillingPrice());
                item.itemHasFree.set(item.isHasGift());
                
                items.add(item); 
            } 
            
         } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return items;
    }
    

   
    
     public int getIdByName(String Name)
     {
          int auto_id=0;
       try {
            Connection con = DBConnect.getConnection();
            String sql = "select item_id_pk from"
                    + " items where item_name='"+Name+"'";
            
            System.out.println("item id sql:"+sql);
            PreparedStatement prest = con.prepareStatement(sql);
            
            ResultSet rs=prest.executeQuery();
            while(rs.next())
            {
                auto_id=rs.getInt(1);
            }
            prest.close();
            con.close();
           
          
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto_id; 
     }
     
      public Item getItemByName(String Name)
      {
         Item item=new Item();
       try {
           
            Connection con = DBConnect.getConnection();
            String sql = "select * from"
                    + " items where item_name='"+Name+"'";
            
            PreparedStatement prest = con.prepareStatement(sql);
            
            ResultSet rs=prest.executeQuery();
            while(rs.next())
            {
                item.setItemId(rs.getInt("item_id_pk"));
                 item.setName(rs.getString("item_name"));
                item.setMrp(Double.valueOf(rs.getString("item_mrp")));
                item.setBarcode(rs.getString("item_barcode"));
                item.setWeight(rs.getDouble("item_weight"));
                item.setWeightUnit(rs.getString("item_weight_unit"));
                item.setActualPrice(rs.getDouble("item_actual_price"));
                item.setBillingPrice(rs.getDouble("item_selling_price"));
                
                item.setHasGift(rs.getBoolean("item_hasfree"));
            }
            prest.close();
            con.close();
           
          
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
       return item;
      }
      
     

    
     public int getInvoiceNumber()
     {
          List<Item> items=new ArrayList<Item>();
         try {
               Connection con = DBConnect.getConnection();
               String sql= "insert into invoice";
               
            PreparedStatement prest = con.prepareStatement(sql);
            ResultSet  resultSet= prest.executeQuery();
            while(resultSet.next()){
                Item item= new  Item();
                item.setName(resultSet.getString("item_name"));
                item.setMrp(Double.valueOf(resultSet.getString("item_mrp")));
                item.setBarcode(resultSet.getString("item_barcode"));
                item.itemName.set(item.getName());
                item.itemBarcode.set(item.getBarcode());
                item.itemMrp.set(item.getMrp());
                items.add(item); 
            } 
            
         } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 1;
     }


     public List<Item> getItemListByInvoiceId(List<InvoiceDetails> invoiceDetailsList)
     {
         List<Item> items=new ArrayList<Item>();
         try {
               Connection con = DBConnect.getConnection();
               StringBuilder itemIdFk=null;
               for(InvoiceDetails invoiceData:invoiceDetailsList)
               {
                   System.out.println("invoiceData.getInvoiceItemIdFk():"+invoiceData.getInvoiceItemIdFk());
                   if(itemIdFk==null)
                   {
                       itemIdFk=new StringBuilder(Integer.toString(invoiceData.getInvoiceItemIdFk()));
                        System.out.println("new itemIdFk:"+itemIdFk);
                   }
                   else
                   {
                       itemIdFk=itemIdFk.append(","+invoiceData.getInvoiceItemIdFk());
                   }
               }
               String sql= "select item_name,item_mrp,item_barcode from items"
                       + " where item_id_pk in("+itemIdFk+")";
                System.out.println("getItemListByInvoiceId() query:"+sql);
               System.out.println("itemIdFk:"+itemIdFk);
            PreparedStatement prest = con.prepareStatement(sql);
            ResultSet  resultSet= prest.executeQuery();
            while(resultSet.next()){
                Item item= new  Item();
                item.setName(resultSet.getString("item_name"));
                item.setMrp(Double.valueOf(resultSet.getString("item_mrp")));
                item.setBarcode(resultSet.getString("item_barcode"));
                item.itemName.set(item.getName());
                item.itemBarcode.set(item.getBarcode());
                item.itemMrp.set(item.getMrp());
                items.add(item); 
            } 
            
         } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return items;
     }
  
}
