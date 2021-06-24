package com.practice.invoicingapp.service;

import com.practice.invoicingapp.entities.Customer;
import com.practice.invoicingapp.entities.Invoice;
import com.practice.invoicingapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    public User getUserById(Long id);
    public User createNewUser(User user);
    public void deleteUser(String email);
    public Set<Invoice> getAllUserInvoice(String email);
    public Set<Customer> getAllCustomers(String email);
    Optional<User> getUserByEmail(String email);
}
