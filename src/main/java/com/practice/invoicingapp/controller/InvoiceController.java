package com.practice.invoicingapp.controller;


import com.practice.invoicingapp.entities.Invoice;
import com.practice.invoicingapp.service.InvoiceService;
import com.practice.invoicingapp.service.InvoiceServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController

public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/invoice/create")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Invoice createInvoiceCont(@RequestBody Invoice invoice){
        return invoiceService.createInvoice(invoice);
    }

    @DeleteMapping("/invoice/delete/{id}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public void deleteInvoiceCont(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
    }
}
