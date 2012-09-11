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
    private double invoiceItemQuantity;
    private double invoiceItemTotal;

    public InvoiceDetails(int invoiceIdFk, int itemIdFk, double invoiceItemQuantity, double invoiceItemTotal) {
        this.invoiceIdFk = invoiceIdFk;
        this.itemIdFk = itemIdFk;
        this.invoiceItemQuantity = invoiceItemQuantity;
        this.invoiceItemTotal = invoiceItemTotal;
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

    public double getInvoiceItemQuantity() {
        return invoiceItemQuantity;
    }

    public void setInvoiceItemQuantity(double invoiceItemQuantity) {
        this.invoiceItemQuantity = invoiceItemQuantity;
    }

    public double getInvoiceItemTotal() {
        return invoiceItemTotal;
    }

    public void setInvoiceItemTotal(double invoiceItemTotal) {
        this.invoiceItemTotal = invoiceItemTotal;
    }
}
