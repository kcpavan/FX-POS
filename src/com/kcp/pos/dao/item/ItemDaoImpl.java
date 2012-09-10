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

}
