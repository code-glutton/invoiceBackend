package com.practice.invoicingapp.controller;

import com.practice.invoicingapp.entities.Customer;
import com.practice.invoicingapp.entities.Invoice;
import com.practice.invoicingapp.entities.User;
import com.practice.invoicingapp.service.UserService;
import com.practice.invoicingapp.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
    public User newUser(@RequestBody User user){
        return userService.createNewUser(user);
    }

    @DeleteMapping("/delete/{email}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
    public void deletedUser(@PathVariable String email){
        userService.deleteUser(email);
    }

    @GetMapping("/userDetail/{id}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
    public User getUserCont(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/getInvoice/{email}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
    public Set<Invoice>  getAllUserInvoice(@PathVariable String email){
        return userService.getAllUserInvoice(email);
    }

    @GetMapping("/add/customer/{email}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Set<Customer> getEveryCustomer(@PathVariable String email){
        return userService.getAllCustomers(email);
    }
}
