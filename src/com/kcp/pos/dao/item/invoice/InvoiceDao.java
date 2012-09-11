/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcp.pos.dao.item.invoice;

import com.kcp.pos.modal.Invoice;
import com.kcp.pos.modal.InvoiceDetails;
import java.util.List;

/**
 *
 * @author pbhimanna
 */
public interface InvoiceDao {
    
    
    public Invoice addItems(Invoice item);
    public List<Invoice> saveInvoice();
    
    public int getInvoiceId();
    
     public boolean addInvoiceItem(InvoiceDetails invoiceDetails);
     
     public Invoice getInvoiceById();

    
}
