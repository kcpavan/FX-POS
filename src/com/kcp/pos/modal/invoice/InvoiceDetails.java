/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package com.kcp.pos.modal.invoice;

import com.kcp.pos.modal.item.Item;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class InvoiceDetails{
    private int invoiceDetailsIdPk;
    private int invoiceIdFk;
    private int invoiceItemIdFk;
    private int invoiceItemQuantity;
    private double invoiceItemTotalPrice;
    
    private Item item;
    
    
    
    
    public SimpleStringProperty itemBarcode = new SimpleStringProperty("<BarCode>");

    public SimpleStringProperty itemName = new SimpleStringProperty("<Name>");
    
    public SimpleDoubleProperty itemMrp = new SimpleDoubleProperty();
    
    public SimpleDoubleProperty itemWeight = new SimpleDoubleProperty();
    
    public SimpleStringProperty itemWeightUnit = new SimpleStringProperty();
    
    public SimpleDoubleProperty itemBillingPrice = new SimpleDoubleProperty();
    
    public SimpleDoubleProperty itemActualPrice = new SimpleDoubleProperty();
    
    public SimpleBooleanProperty itemHasFree = new SimpleBooleanProperty();
    
     public SimpleDoubleProperty itemQuantity = new SimpleDoubleProperty();
     
     public SimpleDoubleProperty itemTotalAmount = new SimpleDoubleProperty();

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

    public String getItemBarcode() {
        return itemBarcode.get();
    }

    public String getItemName() {
        return itemName.get();
    }

    public Double getItemMrp() {
        return itemMrp.get();
    }

    public Double getItemWeight() {
        return itemWeight.get();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getItemWeightUnit() {
        return itemWeightUnit.get();
    }

    public void setItemWeightUnit(SimpleStringProperty itemWeightUnit) {
        this.itemWeightUnit = itemWeightUnit;
    }

    public Double getItemBillingPrice() {
        return itemBillingPrice.get();
    }

    public void setItemBillingPrice(SimpleDoubleProperty itemBillingPrice) {
        this.itemBillingPrice = itemBillingPrice;
    }

    public Double getItemActualPrice() {
        return itemActualPrice.get();
    }

    public void setItemActualPrice(SimpleDoubleProperty itemActualPrice) {
        this.itemActualPrice = itemActualPrice;
    }

    public Boolean getItemHasFree() {
        return itemHasFree.get();
    }

    public void setItemHasFree(SimpleBooleanProperty itemHasFree) {
        this.itemHasFree = itemHasFree;
    }

   

    public void setItemBarcode(SimpleStringProperty itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

   

    public void setItemName(SimpleStringProperty itemName) {
        this.itemName = itemName;
    }

   

    public void setItemWeight(SimpleDoubleProperty itemWeight) {
        this.itemWeight = itemWeight;
    }

    public Double getItemQuantity() {
        return itemQuantity.get();
    }

    public void setItemQuantity(SimpleDoubleProperty itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Double getItemTotalAmount() {
        return itemTotalAmount.get();
    }

    public void setItemTotalAmount(SimpleDoubleProperty itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }

    
    
    
    
}
