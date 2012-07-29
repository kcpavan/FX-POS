/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kcp.pos.dao.item;

import com.kcp.pos.modal.Item;
import com.kcp.pos.utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            String sql = "INSERT INTO items (item_name,item_barcode,item_mrp,item_weight,item_actual_price,item_selling_price,item_weight_unit,item_hasfree) VALUES(?,?,?,?,?,?,'KG',0) ";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setString(1, item.getName());
            prest.setString(2, item.getBarcode());
            prest.setDouble(3, item.getMrp());
            prest.setDouble(4, item.getWeight());
            prest.setDouble(5, item.getActualPrice());
            prest.setDouble(6, item.getSellingPrice());
            
            int count = prest.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

}
