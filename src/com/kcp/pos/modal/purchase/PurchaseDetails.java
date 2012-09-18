/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal.purchase;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class PurchaseDetails {
    
  private Integer  purchaseDetailsId;
  private String  barcode;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
  private String item;
  private Integer purchaseId;
  private Integer  itemId;
  private Double mrp;
  private Integer caseQuantity;
  private Integer unitsQuantity;
  private Integer freeUnits;
  private Double basicRate;
  private Double grossAmount;
  private Integer scheme;
  private Double cd;
  private Double taxPercentage;
  private Double tax;
  private Double netAmount;
  
    public SimpleStringProperty itemBarcode = new SimpleStringProperty();

    public SimpleStringProperty itemName = new SimpleStringProperty();
    
    public SimpleDoubleProperty itemMrp = new SimpleDoubleProperty();
    
    public SimpleIntegerProperty itemCaseQuantity = new SimpleIntegerProperty();
    public SimpleIntegerProperty itemUnitsQuantity = new SimpleIntegerProperty();
    public SimpleIntegerProperty itemFreeUnits = new SimpleIntegerProperty();
    
    public SimpleDoubleProperty itemBasicPrice = new SimpleDoubleProperty();
    
    public SimpleDoubleProperty itemGrossAmount = new SimpleDoubleProperty();
    
    public SimpleIntegerProperty purchaseScheme = new SimpleIntegerProperty();
    
     public SimpleDoubleProperty purchaseCd = new SimpleDoubleProperty();
     
     public SimpleDoubleProperty purchaseTaxPercentage = new SimpleDoubleProperty();
     public SimpleDoubleProperty purchaseTax = new SimpleDoubleProperty();
     public SimpleDoubleProperty purchaseNetAmount = new SimpleDoubleProperty();

    public PurchaseDetails() {
    }
     
     
    

    public PurchaseDetails(Integer purchaseDetailsId, Integer purchaseId, Integer itemId, Double mrp, Integer caseQuantity, Integer unitsQuantity, Integer freeUnits, Double basicRate, Double grossAmount, Integer scheme, Double cd, Double taxPercentage, Double tax, Double netAmount) {
        this.purchaseDetailsId = purchaseDetailsId;
        this.purchaseId = purchaseId;
        this.itemId = itemId;
        this.mrp = mrp;
        this.caseQuantity = caseQuantity;
        this.unitsQuantity = unitsQuantity;
        this.freeUnits = freeUnits;
        this.basicRate = basicRate;
        this.grossAmount = grossAmount;
        this.scheme = scheme;
        this.cd = cd;
        this.taxPercentage = taxPercentage;
        this.tax = tax;
        this.netAmount = netAmount;
    }
  
  
  
  

    public Integer getPurchaseDetailsId() {
        return purchaseDetailsId;
    }

    public void setPurchaseDetailsId(Integer purchaseDetailsId) {
        this.purchaseDetailsId = purchaseDetailsId;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public Integer getCaseQuantity() {
        return caseQuantity;
    }

    public void setCaseQuantity(Integer caseQuantity) {
        this.caseQuantity = caseQuantity;
    }

    public Integer getUnitsQuantity() {
        return unitsQuantity;
    }

    public void setUnitsQuantity(Integer unitsQuantity) {
        this.unitsQuantity = unitsQuantity;
    }

    public Integer getFreeUnits() {
        return freeUnits;
    }

    public void setFreeUnits(Integer freeUnits) {
        this.freeUnits = freeUnits;
    }

    public Double getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(Double basicRate) {
        this.basicRate = basicRate;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Integer getScheme() {
        return scheme;
    }

    public void setScheme(Integer scheme) {
        this.scheme = scheme;
    }

    public Double getCd() {
        return cd;
    }

    public void setCd(Double cd) {
        this.cd = cd;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

  
  

    public String getItemBarcode() {
        return itemBarcode.get();
    }

    public void setItemBarcode(SimpleStringProperty itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(SimpleStringProperty itemName) {
        this.itemName = itemName;
    }

    public Double getItemMrp() {
        return itemMrp.get();
    }

    public void setItemMrp(SimpleDoubleProperty itemMrp) {
        this.itemMrp = itemMrp;
    }

    public Integer getItemCaseQuantity() {
        return itemCaseQuantity.get();
    }

    public void setItemCaseQuantity(SimpleIntegerProperty itemCaseQuantity) {
        this.itemCaseQuantity = itemCaseQuantity;
    }

    public Integer getItemUnitsQuantity() {
        return itemUnitsQuantity.get();
    }

    public void setItemUnitsQuantity(SimpleIntegerProperty itemUnitsQuantity) {
        this.itemUnitsQuantity = itemUnitsQuantity;
    }

    public Integer getItemFreeUnits() {
        return itemFreeUnits.get();
    }

    public void setItemFreeUnits(SimpleIntegerProperty itemFreeUnits) {
        this.itemFreeUnits = itemFreeUnits;
    }

    public Double getItemBasicPrice() {
        return itemBasicPrice.get();
    }

    public void setItemBasicPrice(SimpleDoubleProperty itemBasicPrice) {
        this.itemBasicPrice = itemBasicPrice;
    }

    public Double getItemGrossAmount() {
        return itemGrossAmount.get();
    }

    public void setItemGrossAmount(SimpleDoubleProperty itemGrossAmount) {
        this.itemGrossAmount = itemGrossAmount;
    }

    

    public Double getPurchaseCd() {
        return purchaseCd.get();
    }

    public void setPurchaseCd(SimpleDoubleProperty purchaseCd) {
        this.purchaseCd = purchaseCd;
    }

    public Double getPurchaseTaxPercentage() {
        return purchaseTaxPercentage.get();
    }

    public void setPurchaseTaxPercentage(SimpleDoubleProperty purchaseTaxPercentage) {
        this.purchaseTaxPercentage = purchaseTaxPercentage;
    }

    public Double getPurchaseTax() {
        return purchaseTax.get();
    }

    public void setPurchaseTax(SimpleDoubleProperty purchaseTax) {
        this.purchaseTax = purchaseTax;
    }

    public Double getPurchaseNetAmount() {
        return purchaseNetAmount.get();
    }

    public void setPurchaseNetAmount(SimpleDoubleProperty purchaseNetAmount) {
        this.purchaseNetAmount = purchaseNetAmount;
    }

   

    public Integer getPurchaseScheme() {
        return purchaseScheme.get();
    }

    public void setPurchaseScheme(SimpleIntegerProperty purchaseScheme) {
        this.purchaseScheme = purchaseScheme;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    
    
  
}
