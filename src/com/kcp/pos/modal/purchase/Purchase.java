/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.modal.purchase;

import java.util.Date;

/**
 *
 * @author Prakash
 */
public class Purchase {

        private Integer purchaseId;
        private Integer purchaseNumber;
        private Integer distributorId;
        private Date receivedDate;
        private Integer modifiedUser;
        private Integer modifiedDate;

    public Purchase(Integer purchaseId, Integer purchaseNumber, Integer distributorId, Date receivedDate, Integer modifiedUser, Integer modifiedDate) {
        this.purchaseId = purchaseId;
        this.purchaseNumber = purchaseNumber;
        this.distributorId = distributorId;
        this.receivedDate = receivedDate;
        this.modifiedUser = modifiedUser;
        this.modifiedDate = modifiedDate;
    }
        
        
        
        
        

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Integer purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Integer getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(Integer modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Integer getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Integer modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
        
        
        
        
        
        
    
}
