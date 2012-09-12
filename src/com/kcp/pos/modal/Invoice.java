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
 * @author pbhimanna
 */
public class Invoice extends Item {
    

    private int invoiceNumber;
    private int invoiceUser;
    private Date invoiceDate;
    private double total;
    
    //List for combo boxs
    private List<InvoiceDetails> detailsList;

    public List<InvoiceDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<InvoiceDetails> detailsList) {
        this.detailsList = detailsList;
    }

  

   

    public Invoice() {
    }

    
    
    /*private int itemIdFk;
    private int 
   
    private String name;
    private String barcode;
    private double mrp;
    private double weight;
    private String weightUnit;
    private double actualPrice;
    private double sellingPrice;
    private boolean hasGift;
    private double quantity;*/

   

  
    
    
    
    
    
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getInvoiceUser() {
        return invoiceUser;
    }

    public void setInvoiceUser(int invoiceUser) {
        this.invoiceUser = invoiceUser;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

  
    
    
    
    
    
}
