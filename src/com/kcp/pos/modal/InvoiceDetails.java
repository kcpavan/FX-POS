/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class InvoiceDetails extends Item{

    private int invoiceIdFk;
    private int itemIdFk;
    private int invoiceItemQuantity;
    private double invoiceItemTotalPrice;

    public InvoiceDetails(int invoiceIdFk, int itemIdFk, int invoiceItemQuantity, double invoiceItemTotalPrice) {
        this.invoiceIdFk = invoiceIdFk;
        this.itemIdFk = itemIdFk;
        this.invoiceItemQuantity = invoiceItemQuantity;
        this.invoiceItemTotalPrice = invoiceItemTotalPrice;
    }

   
    

  

   

   

   public InvoiceDetails()
   {
       super();
   }
    
    public int getInvoiceIdFk() {
        return invoiceIdFk;
    }

    public void setInvoiceIdFk(int invoiceIdFk) {
        this.invoiceIdFk = invoiceIdFk;
    }

    public int getItemIdFk() {
        return itemIdFk;
    }

    public void setItemIdFk(int itemIdFk) {
        this.itemIdFk = itemIdFk;
    }

    public int getInvoiceItemQuantity() {
        return invoiceItemQuantity;
    }

  
    public double getInvoiceItemTotalPrice() {
        return invoiceItemTotalPrice;
    }

    public void setInvoiceItemTotalPrice(double invoiceItemTotalPrice) {
        this.invoiceItemTotalPrice = invoiceItemTotalPrice;
    }

   

    public void setItemMrp(SimpleDoubleProperty itemMrp) {
        this.itemMrp = itemMrp;
    }

   

    public void setInvoiceItemQuantity(int invoiceItemQuantity) {
        this.invoiceItemQuantity = invoiceItemQuantity;
    }

    
    
}
