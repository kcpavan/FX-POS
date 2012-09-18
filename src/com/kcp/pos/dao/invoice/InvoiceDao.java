/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.invoice;

;

import com.kcp.pos.modal.invoice.Invoice;
import com.kcp.pos.modal.invoice.InvoiceDetails;
import com.kcp.pos.modal.item.Item;
import java.util.List;
import com.kcp.pos.modal.invoice.InvoiceDetails;
import com.kcp.pos.modal.item.Item;
import java.util.List;

/**
 *
 * @author pbhimanna
 */
public interface InvoiceDao {
    
    
    public Invoice addItems(Invoice item);
    public int saveInvoice(List<InvoiceDetails> invoiceDetailsList);
    
    public int getInvoiceId();
    
     public boolean addInvoiceItem(InvoiceDetails invoiceDetails,Item item);
     
     public Invoice getInvoiceById();
     
    public List<InvoiceDetails> getInvoiceItems(String invoiceNumber);
    
    public int deleteInvoiceItem(int invoiceId,int itemId);
    

    
}
