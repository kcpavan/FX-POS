/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kcp.pos.dao.item;

import com.kcp.pos.modal.Item;
import com.kcp.pos.utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author K.C.Pavan
 */
public class ItemDaoImpl implements ItemDao{

    @Override
    public Item saveItems(Item item) {
        try {
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO items (item_name,item_barcode,item_mrp,item_weight,item_actual_price,item_selling_price,item_weight_unit,item_hasfree) VALUES(?,?,?,?,?,?,?,0) ";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, item.getName());
            prest.setString(2, item.getBarcode());
            prest.setDouble(3, item.getMrp());
            prest.setDouble(4, item.getWeight());
            prest.setDouble(5, item.getActualPrice());
            prest.setDouble(6, item.getSellingPrice());
            prest.setString(7, item.getWeightUnit());
            
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
               String sql= "select item_name,item_mrp,item_barcode from items";
               
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
    
   
    
     public int getIdByName(String Name)
     {
          int auto_id=0;
       try {
            Connection con = DBConnect.getConnection();
            String sql = "select item_id_pk from"
                    + " items where item_name="+Name;
            
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
         Item item=null;
       try {
           /*
            * item_id_pk integer primary key not null auto_increment,
item_name varchar(250) not null,
item_barcode varchar(250) not null,
item_mrp double not null,
item_weight double not null,
item_weight_unit varchar(50) not null,
item_actual_price double not null,
item_selling_price double not null,
item_hasfree boolean not null
            */
            Connection con = DBConnect.getConnection();
            String sql = "select item_selling_price from"
                    + " items where item_name="+Name;
            
            PreparedStatement prest = con.prepareStatement(sql);
            
            ResultSet rs=prest.executeQuery();
            while(rs.next())
            {
                item.setSellingPrice(rs.getInt(1));
            }
            prest.close();
            con.close();
           
          
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
       return item;
      }
      
     
}
