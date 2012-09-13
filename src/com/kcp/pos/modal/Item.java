/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author K.C.Pavan
 */
public class Item {

    private String name;
    private String barcode;
    private double mrp;
    private double weight;
    private String weightUnit;
    private double actualPrice;
    private double sellingPrice;
    private boolean hasGift;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean isHasGift() {
        return hasGift;
    }

    public void setHasGift(boolean hasGift) {
        this.hasGift = hasGift;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getItemBarcode() {
        return itemBarcode.get();
    }

    public String getItemName() {
        return itemName.get();
    }
    
    public SimpleStringProperty itemBarcode = new SimpleStringProperty("<BarCode>");

    public SimpleStringProperty itemName = new SimpleStringProperty("<Name>");
    
    public SimpleDoubleProperty itemMrp = new SimpleDoubleProperty();
    
    public SimpleDoubleProperty itemWeight = new SimpleDoubleProperty();
    
    public SimpleStringProperty itemWeightUnit = new SimpleStringProperty();
    
    public SimpleDoubleProperty itemSellingPrice = new SimpleDoubleProperty();
    
    public SimpleDoubleProperty itemActualPrice = new SimpleDoubleProperty();
    
    public SimpleBooleanProperty itemHasFree = new SimpleBooleanProperty();

    public double getItemMrp() {
        return itemMrp.get();
    }
}
