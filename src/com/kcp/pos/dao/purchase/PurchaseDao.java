/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.purchase;

import com.kcp.pos.modal.invoice.InvoiceDetails;
import com.kcp.pos.modal.item.Item;
import com.kcp.pos.modal.purchase.PurchaseDetails;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface PurchaseDao {
    
      public List<PurchaseDetails> getPurchaseItems(String purchaseNumber);
      
      public int getPurchaseId();
      
    public boolean addPurcaseItem(PurchaseDetails purchaseDetails);
    
    
    
    //public List<PurchaseDetails> getPurchaseItems(List<PurchaseDetails> purchaseDetailsList);
    
}
