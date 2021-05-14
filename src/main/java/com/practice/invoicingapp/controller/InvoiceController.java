package com.practice.invoicingapp.controller;


import com.practice.invoicingapp.entities.Invoice;
import com.practice.invoicingapp.service.InvoiceService;
import com.practice.invoicingapp.service.InvoiceServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller

public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/invoice/create")
    public Invoice createInvoiceCont(@RequestBody Invoice invoice){
        return invoiceService.createInvoice(invoice);
    }

    @DeleteMapping("/invoice/delete/{id}")
    public void deleteInvoiceCont(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
    }
}
