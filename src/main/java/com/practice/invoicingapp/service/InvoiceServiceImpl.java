package com.practice.invoicingapp.service;

import com.practice.invoicingapp.entities.Invoice;
import com.practice.invoicingapp.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
