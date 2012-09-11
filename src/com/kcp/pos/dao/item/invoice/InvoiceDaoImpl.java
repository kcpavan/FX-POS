/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.item.invoice;

import com.kcp.pos.dao.item.ItemDaoImpl;
import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.utils.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    public Invoice saveItem(InvoiceDetails item) {
        Invoice invoice = null;

        try {
            Connection con = DBConnect.getConnection();
            String sql = "INSERT INTO invoice_details (invoice_id_fk,"
                    + "item_id_fk,"
                    + "quantity,"
                    + "total_amount) VALUES(?,?,?,?) ";
            PreparedStatement prest = con.prepareStatement(sql);
            prest.setInt(1, item.getInvoiceIdFk());
            prest.setInt(2, item.getItemIdFk());
            prest.setDouble(3, item.getInvoiceItemQuantity());
            prest.setDouble(4, item.getInvoiceItemTotal());


            int count = prest.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


        return invoice;
    }

    public List<Invoice> getAllItems() {
        List<Invoice> invoiceList = new ArrayList<Invoice>();

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

    public int getInvoiceNumber() {
        Invoice invoice = null;
        Connection con = null;

        try {
            con = DBConnect.getConnection();
            String sql = "INSERT INTO invoice (invoice_user_id_fk,"
                    + "invoice_date,"
                    + "invoice_total) VALUES(?,?,?) ";


            PreparedStatement prest = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prest.setInt(1, 1);
            Date date = new Date();

            Timestamp currTime;
            currTime = new Timestamp(date.getTime());
            prest.setTimestamp(2, currTime);
            prest.setDouble(3, 0.0);



            int count = prest.executeUpdate();
            ResultSet rs = prest.getGeneratedKeys();
            if (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int colCount = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= colCount; i++) {
                        String key = rs.getString(i);
                        System.out.println("key " + i + "is " + key);
                    }
                } while (rs.next());
} 
else {
    System.out.println("There are no generated keys.");
            }

            

        return key;
    }
}
