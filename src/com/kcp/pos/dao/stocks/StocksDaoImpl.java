/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.stocks;

import com.kcp.pos.dao.invoice.InvoiceDaoImpl;
import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.modal.item.Item;
import com.kcp.pos.modal.purchase.PurchaseDetails;
import com.kcp.pos.modal.stocks.Stocks;
import com.kcp.pos.utils.DBConnect;
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
public class StocksDaoImpl implements StocksDao {

    public List<Stocks> getStocksList() {
        List<Stocks> stocks = new ArrayList<Stocks>();

        Connection con = null;
        PreparedStatement prest = null;
        Map<Integer, Item> itemsMap = new HashMap<Integer, Item>();

        try {
            con = DBConnect.getConnection();

            String sql = "select item_id_pk,item_name,item_mrp,item_barcode,item_weight,"
                    + "item_weight_unit,item_actual_price,item_selling_price,item_hasfree from items";

            prest = con.prepareStatement(sql);
            ResultSet resultSet = prest.executeQuery();
            while (resultSet.next()) {

                Item item = new Item();
                item.setItemId(resultSet.getInt("item_id_pk"));
                item.setName(resultSet.getString("item_name"));
                item.setMrp(Double.valueOf(resultSet.getString("item_mrp")));
                item.setBarcode(resultSet.getString("item_barcode"));
                item.setWeight(resultSet.getDouble("item_weight"));
                item.setWeightUnit(resultSet.getString("item_weight_unit"));
                item.setActualPrice(resultSet.getDouble("item_actual_price"));
                item.setHasGift(resultSet.getBoolean("item_hasfree"));
                item.setBillingPrice(resultSet.getDouble("item_selling_price"));

                itemsMap.put(new Integer(item.getItemId()), item);

            }
        } catch (SQLException es) {
            es.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
                prest.close();
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



        try {

            con = DBConnect.getConnection();

            String sql = "select stock_id_pk,"
                    + "stock_item_id_fk,"
                    + "stock_quantity_case,"
                    + "stock_quantity_units,"
                    + "stock_quantity_freeunits,"
                    + "stock_modified_user_id_fk,"
                    + "stock_modified_date from stocks";

            System.out.println("sql:" + sql);

            prest = con.prepareStatement(sql);
            ResultSet resultSet = prest.executeQuery();
            while (resultSet.next()) {

                Stocks stock = new Stocks();

                stock.setItemId(resultSet.getInt("stock_item_id_fk"));

                Item item = itemsMap.get(stock.getItemId());
                stock.setItem(item.getName());
                stock.setMrp(Double.valueOf(item.getMrp()));
                stock.setBarcode(item.getBarcode());
                stock.setCaseQuantity(resultSet.getInt("stock_quantity_case"));
                stock.setUnitsQuantity(resultSet.getInt("stock_quantity_units"));
                stock.setFreeUnits(resultSet.getInt("stock_quantity_freeunits"));



                System.out.println("item.getBarcode():" + stock.getBarcode());



                stock.itemName.set(stock.getItem());
                stock.itemBarcode.set(stock.getBarcode());
                stock.itemMrp.set(stock.getMrp());
                System.out.println("item.getMrp():" + stock.getMrp());
                stock.itemCaseQuantity.set(stock.getCaseQuantity());
                stock.itemUnitsQuantity.set(stock.getUnitsQuantity());
                stock.itemFreeUnits.set(stock.getFreeUnits());


                stocks.add(stock);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stocks;
    }

    public boolean addorUpdateStockOnPurcase(PurchaseDetails purchaseDetails) {
        int result = 0;
        try {
            /*
             * stock_id_pk integer primary key not null auto_increment,
             stock_item_id_fk integer not null,
             stock_quantity_case bigint not null,
             stock_quantity_units bigint not null,
             stock_quantity_freeunits varchar(50) not null,
             stock_modified_user_id_fk int not null,
             stock_modified_date timestamp,
             */
            Connection con = DBConnect.getConnection();
            Timestamp timeStamp = new Timestamp(new Date().getTime());



            /*String existsQuery = "SELECT EXISTS(SELECT 1 FROM stocks "
                    + "where stock_item_id_fk=" + purchaseDetails.getItemId() + ")";*/

            String existsQuery = "SELECT EXISTS(SELECT 1 FROM stocks "
                    + "where stock_item_id_fk=" + purchaseDetails.getItemId() + ")";

                            
            System.out.append("exists query:" + existsQuery);

            PreparedStatement prestStmt = con.prepareStatement(existsQuery);
            prestStmt.execute(existsQuery);
            ResultSet resultSet = prestStmt.getResultSet(); //result set for records  
            int exists = 0;
            while (resultSet.next()) {
                exists = resultSet.getInt(1);
            }
            boolean recordFound = resultSet.next();
            System.out.append("recordFound:" + recordFound);

            if (exists == 0) {


                String sql = "INSERT INTO stocks ("
                        + "stock_item_id_fk,"
                        + "stock_quantity_case,"
                        + "stock_quantity_units ,"
                        + "stock_quantity_freeunits,"
                        + " stock_modified_user_id_fk,"
                        + "stock_modified_date)"
                        + "  VALUES(?,?,?,?,?,?) ";

                PreparedStatement prest = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                prest.setInt(1, purchaseDetails.getItemId());
                System.out.println("getItemId:" + purchaseDetails.getItemId());

                prest.setInt(2, purchaseDetails.getCaseQuantity());
                System.out.println("getCaseQuantity:" + purchaseDetails.getCaseQuantity());
                prest.setInt(3, purchaseDetails.getUnitsQuantity());
                System.out.println("getUnitsQuantity:" + purchaseDetails.getUnitsQuantity());
                prest.setInt(4, purchaseDetails.getFreeUnits());
                System.out.println("getFreeUnits:" + purchaseDetails.getFreeUnits());

                prest.setInt(5, 1);
                prest.setTimestamp(6, timeStamp);


                System.out.println("sql:" + sql);
                result = prest.executeUpdate();
            } else {
                String sql = "update stocks "
                        + " set stock_quantity_case=+" + purchaseDetails.getCaseQuantity()
                        + ", stock_quantity_units=+" + purchaseDetails.getUnitsQuantity()
                        + ", stock_quantity_freeunits=+" + purchaseDetails.getFreeUnits()
                        + ", stock_modified_user_id_fk=" + 1
                        + ", stock_modified_date='" + timeStamp + "'"
                        + "   where stock_item_id_fk=" + purchaseDetails.getItemId();

                PreparedStatement prest = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


                System.out.println("getItemId:" + purchaseDetails.getItemId());


                System.out.println("getCaseQuantity:" + purchaseDetails.getCaseQuantity());

                System.out.println("getUnitsQuantity:" + purchaseDetails.getUnitsQuantity());

                System.out.println("getFreeUnits:" + purchaseDetails.getFreeUnits());




                System.out.println("sql:" + sql);
                result = prest.executeUpdate();
            }







            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        }

        if (result == 0) {
            return false;
        } else {
            return false;
        }
    }
    
    
   
    public int updateStocksOnInvoice(int itemId,int quantity)
    {
       int result=0;
       try {
            Connection con = DBConnect.getConnection();
            
            
            
              String sql = "update stocks "
                       // + " set stock_quantity_case=" + purchaseDetails.getCaseQuantity()
                        + " stock_quantity_units=-" + quantity
                      
                        + "   where stock_item_id_fk=" + itemId;

                PreparedStatement prest = con.prepareStatement(sql);
         
             result=prest.executeUpdate();
            
           
            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result; 
    }
}
