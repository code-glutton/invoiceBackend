package com.practice.invoicingapp.controller;

import com.practice.invoicingapp.entities.Customer;
import com.practice.invoicingapp.service.CustomerService;
import com.practice.invoicingapp.service.CustomerServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create/customer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }
}
