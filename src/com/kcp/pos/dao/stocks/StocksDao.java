/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.stocks;

import com.kcp.pos.modal.purchase.PurchaseDetails;
import com.kcp.pos.modal.stocks.Stocks;
import java.util.List;

/**
 *
 * @author Prakash
 */
public interface StocksDao {
    
    public List<Stocks> getStocksList();
    
    public int updateStocksOnInvoice(int itemId,int quantity);
    
    public boolean addorUpdateStockOnPurcase(PurchaseDetails purchaseDetails);
    
}
