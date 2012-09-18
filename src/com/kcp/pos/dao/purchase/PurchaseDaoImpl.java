/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.purchase;

import com.kcp.pos.dao.invoice.InvoiceDaoImpl;
import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.dao.stocks.StocksDao;
import com.kcp.pos.dao.stocks.StocksDaoImpl;
import com.kcp.pos.modal.invoice.InvoiceDetails;
import com.kcp.pos.modal.item.Item;
import com.kcp.pos.modal.purchase.PurchaseDetails;
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
public class PurchaseDaoImpl implements PurchaseDao {

    public int getPurchaseId() {

        int auto_id = 0;
        try {
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO purchase (         "
                    + " purchase_invoice_number,"
                    + "purchase_distibutor_id_fk, "
                    + "purchase_received_date, "
                    + "purchase_modified_user_id_fk, "
                    + "purchase_modified_date )"
                    + "  VALUES(?,?,?,?,?) ";
            PreparedStatement prest = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prest.setInt(1, 1);
            prest.setInt(2, 1);/*Distributor invoice number*/

            Timestamp timeStamp = new Timestamp(new Date().getTime());
            prest.setTimestamp(3, timeStamp);

            prest.setInt(4, 1);
            prest.setTimestamp(5, timeStamp);

            prest.executeUpdate();

            ResultSet rs = prest.getGeneratedKeys();
            rs.next();
            auto_id = rs.getInt(1);

            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto_id;
    }

   /* public List<PurchaseDetails> getPurchaseItems(String purchaseNumber) {
        List<PurchaseDetails> invoiceDetailsList = new ArrayList<PurchaseDetails>();
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
            if (KCPUtils.isNullString(purchaseNumber)) {
                purchaseNumber = "1";
            }
            String sql = "select * from "
                    + " purchase_details where purchase_id_fk=" + purchaseNumber;
            System.out.println("sql:" + sql);
            prest = con.prepareStatement(sql);

            ResultSet rs = prest.executeQuery();
            PurchaseDetails data = null;
            while (rs.next()) {
                data = new PurchaseDetails();
            



                Item item = itemsMap.get(rs.getInt(3));

                data.itemName.set(item.getName());


                invoiceDetailsList.add(data);


            }
        } catch (SQLException es) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, es);
            es.printStackTrace();;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                prest.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


        //need to return auto incremented id

        return invoiceDetailsList;
    }*/

    public boolean addPurcaseItem(PurchaseDetails purchaseDetails) {
        int auto_id = 0;
        try {
            
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO purchase_details ("
                    + "purchase_id_fk,"
                    + "item_id_fk,"
                    + "mrp ,"
                    + "quantity_case,"
                    + " quantity_units,"
                    + "quantity_free,"
                    + "basic_rate,"
                    + "gross_amount,"
                    + "scheme,"
                    + "cd,"
                    + "tax_percentage,"
                    + "tax,"
                    + "net_amount )"
                    + "  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
          
            PreparedStatement prest = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prest.setInt(1, purchaseDetails.getPurchaseId());
            System.out.println("purchaseId:"+purchaseDetails.getPurchaseId());
            prest.setInt(2, purchaseDetails.getItemId());
            System.out.println("getItemId:"+purchaseDetails.getItemId());
            prest.setDouble(3, purchaseDetails.getMrp());
            System.out.println("getMrp:"+purchaseDetails.getMrp());
            prest.setInt(4, purchaseDetails.getCaseQuantity());
            System.out.println("getCaseQuantity:"+purchaseDetails.getCaseQuantity());
            prest.setInt(5, purchaseDetails.getUnitsQuantity());
            System.out.println("getUnitsQuantity:"+purchaseDetails.getUnitsQuantity());
            prest.setInt(6, purchaseDetails.getFreeUnits());
            System.out.println("getFreeUnits:"+purchaseDetails.getFreeUnits());
            prest.setDouble(7, purchaseDetails.getBasicRate());
            System.out.println("getBasicRate:"+purchaseDetails.getBasicRate());
            prest.setDouble(8, purchaseDetails.getGrossAmount());
            System.out.println("getPurchaseId:"+purchaseDetails.getPurchaseId());
            prest.setInt(9, purchaseDetails.getScheme());
            System.out.println("getScheme:"+purchaseDetails.getScheme());
            prest.setDouble(10, purchaseDetails.getCd());
            System.out.println("getCd:"+purchaseDetails.getCd());
            prest.setDouble(11, purchaseDetails.getTaxPercentage());
            System.out.println("getTaxPercentage:"+purchaseDetails.getTaxPercentage());
            prest.setDouble(12, purchaseDetails.getTax());
            System.out.println("getTax:"+purchaseDetails.getTax());
            prest.setDouble(13, purchaseDetails.getNetAmount());
            System.out.println("getNetAmount:"+purchaseDetails.getNetAmount());
         

            System.out.println("sql:"+sql);
            int retVal=prest.executeUpdate();
            
            if(retVal>0)
            {
                StocksDao stocksDao=new StocksDaoImpl();
                stocksDao.addorUpdateStockOnPurcase(purchaseDetails);
                
            }

           

            //need to return auto incremented id
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        finally
        {
            
        }
        return true;
    }
    
     public List<PurchaseDetails> getPurchaseItems(String purchaseNumber)
     {
          List<PurchaseDetails> purchaseDetailsList=new ArrayList<PurchaseDetails>();
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
            if(KCPUtils.isNullString(purchaseNumber))
                purchaseNumber="1";
            String sql = "select * from purchase_details where purchase_id_fk="+purchaseNumber;
            System.out.println("sql:"+sql);
            prest = con.prepareStatement(sql);
            
            ResultSet rs=prest.executeQuery();
            PurchaseDetails data=null;
            while(rs.next())
            {
                /*
                 *  + "purchase_id_fk,"
                    + "item_id_fk,"
                    + "mrp ,"
                    + "quantity_case,"
                    + " quantity_units,"
                    + "quantity_free"
                    + "basic_rate,"
                    + "gross_amount,"
                    + "scheme"
                    + "cd"
                    + "tax_percentage,"
                    + "tax,"
                    + "net_amount )"
                 */
               
                data=new PurchaseDetails();
                data.setPurchaseDetailsId(rs.getInt(1));
                
                data.setPurchaseId(rs.getInt(2));
                data.setItemId(rs.getInt(3));
                Item item=itemsMap.get(data.getItemId());
                System.out.println("purchase item id:"+data.getItemId());
                
              
                data.setMrp(rs.getDouble(4));
                data.setCaseQuantity(rs.getInt(5));
                data.setUnitsQuantity(rs.getInt(6));
                data.setFreeUnits(rs.getInt(7));
               
                data.setBasicRate(rs.getDouble(8));
                data.setGrossAmount(rs.getDouble(9));
                data.setScheme(rs.getInt(10));
                data.setCd(rs.getDouble(11));
                data.setTaxPercentage(rs.getDouble(12));
                data.setTax(rs.getDouble(13));
                data.setNetAmount(rs.getDouble(14));
                
                data.itemName.set(item.getName());
                System.out.println("purchase item name:"+item.getName());
                data.itemBarcode.set(item.getBarcode());
                System.out.println("purchase getBarcode name:"+item.getBarcode());
                data.itemMrp.set(data.getMrp());
                data.itemCaseQuantity.set(data.getCaseQuantity());
                data.itemUnitsQuantity.set(data.getUnitsQuantity());
                data.itemFreeUnits.set(data.getFreeUnits());
                data.itemBasicPrice.set(data.getBasicRate());
                data.itemGrossAmount.set(data.getGrossAmount());
                data.purchaseScheme.set(data.getScheme());
                data.purchaseCd.set(data.getCd());
                data.purchaseTax.set(data.getTax());
                data.purchaseTaxPercentage.set(data.getTaxPercentage());
                data.purchaseNetAmount.set(data.getNetAmount());
           
               
                purchaseDetailsList.add(data);
        
                
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
        
       return purchaseDetailsList;
     }
}
