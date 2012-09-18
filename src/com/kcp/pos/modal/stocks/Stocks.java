/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal.stocks;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Prakash
 */
public class Stocks {

   
    private String barcode;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    private String item;
    private Integer quantity;
    private Integer itemId;
    private Double mrp;
    private Integer caseQuantity;
    private Integer unitsQuantity;
    private Integer freeUnits;
    
    public SimpleStringProperty itemBarcode = new SimpleStringProperty();
    public SimpleStringProperty itemName = new SimpleStringProperty();
    public SimpleDoubleProperty itemMrp = new SimpleDoubleProperty();
    public SimpleIntegerProperty itemCaseQuantity = new SimpleIntegerProperty();
    public SimpleIntegerProperty itemUnitsQuantity = new SimpleIntegerProperty();
    public SimpleIntegerProperty itemFreeUnits = new SimpleIntegerProperty();

    public Stocks(String barcode, String item, Integer quantity, Integer itemId, Double mrp, Integer caseQuantity, Integer unitsQuantity, Integer freeUnits) {
        this.barcode = barcode;
        this.item = item;
        this.quantity = quantity;
        this.itemId = itemId;
        this.mrp = mrp;
        this.caseQuantity = caseQuantity;
        this.unitsQuantity = unitsQuantity;
        this.freeUnits = freeUnits;
    }

    public Stocks() {
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

    
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
