package com.practice.invoicingapp.repository;

import com.practice.invoicingapp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
