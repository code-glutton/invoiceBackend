package com.practice.invoicingapp.service;


import com.practice.invoicingapp.entities.Invoice;
import org.springframework.stereotype.Service;


public interface InvoiceService {
    public Invoice createInvoice(Invoice invoice);
    public void deleteInvoice(Long id);
}
