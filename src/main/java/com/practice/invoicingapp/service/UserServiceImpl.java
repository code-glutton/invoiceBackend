package com.practice.invoicingapp.service;

import com.practice.invoicingapp.config.UserExists;
import com.practice.invoicingapp.config.UserNotFound;
import com.practice.invoicingapp.entities.Customer;
import com.practice.invoicingapp.entities.Invoice;
import com.practice.invoicingapp.entities.User;
import com.practice.invoicingapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> findUserOpt = userRepository.findById(id);
        User user;
        if(!findUserOpt.isPresent()){
            throw new UserNotFound("User not found");
        }
        User usergotten = findUserOpt.get();
        return usergotten;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
       Optional<User> user = userRepository.findByEmail(email);
    return user;
}
    @Override
    public User createNewUser(User user) {
        List<User> users = userRepository.findAll();
        for (User person: users
             ) {
            if(person.getEmail().equalsIgnoreCase(user.getEmail())){
                throw new UserExists("email exists");
            }
        }
        return userRepository.save(user);
    }
    
    @Override
    public void deleteUser(String email){
        List<User> users = userRepository.findAll();
        Long userId;
        for (User person: users
        ) {
            if(person.getEmail().equalsIgnoreCase(email)){
                User deleteUser = person;
                userId = deleteUser.getId();
                userRepository.deleteById(userId);
            }
        }

    }

    @Override
    public Set<Invoice> getAllUserInvoice(String email) {
        List<User> users = userRepository.findAll();
        Set<Invoice> invoices = new HashSet<>();
        for (User person: users
        ) {
            if(person.getEmail().equalsIgnoreCase(email)){
                Set<Invoice> userInvoice;
                User invoiceUser = person;
                userInvoice = invoiceUser.getInvoices();
                invoices = userInvoice;
            }
        }
        System.out.println(invoices);
        return invoices;
    }

    @Override
    public Set<Customer> getAllCustomers(String email) {
        List<User> users = userRepository.findAll();
        Set<Customer> customers = new HashSet<>();
        for (User person: users
        ) {
            if(person.getEmail().equalsIgnoreCase(email)){
                Set<Customer> userCustomer;
                User customerUser = person;
                userCustomer = customerUser.getCustomers();
                customers = userCustomer;
            }
        }
        System.out.println(customers);
        return customers;
    }
}
