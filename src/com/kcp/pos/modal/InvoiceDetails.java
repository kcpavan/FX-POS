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
    private int invoiceDetailsIdPk;
    private int invoiceIdFk;
    private int invoiceItemIdFk;
    private int invoiceItemQuantity;
    private double invoiceItemTotalPrice;

    public InvoiceDetails(int invoiceIdFk, int invoiceItemIdFk, int invoiceItemQuantity, double invoiceItemTotalPrice) {
        this.invoiceIdFk = invoiceIdFk;
        this.invoiceItemIdFk = invoiceItemIdFk;
        this.invoiceItemQuantity = invoiceItemQuantity;
        this.invoiceItemTotalPrice = invoiceItemTotalPrice;
    }

    public int getInvoiceDetailsIdPk() {
        return invoiceDetailsIdPk;
    }

    public void setInvoiceDetailsIdPk(int invoiceDetailsIdPk) {
        this.invoiceDetailsIdPk = invoiceDetailsIdPk;
    }

    public int getInvoiceItemIdFk() {
        return invoiceItemIdFk;
    }

    public void setInvoiceItemIdFk(int invoiceItemIdFk) {
        this.invoiceItemIdFk = invoiceItemIdFk;
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
