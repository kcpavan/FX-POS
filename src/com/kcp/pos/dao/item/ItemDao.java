/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.item;

import com.kcp.pos.modal.InvoiceDetails;
import com.kcp.pos.modal.Item;
import java.util.List;

/**
 *
 * @author K.C.Pavan
 */
public interface ItemDao {

    public Item saveItems(Item item);

    public List<Item> getAllItems();

    public int getIdByName(String Name);

    public Item getItemByName(String Name);
    
    public List<Item> getItemListByInvoiceId(List<InvoiceDetails> invoiceDetailsList);
    
    
    
   
                    
                    
                    }
