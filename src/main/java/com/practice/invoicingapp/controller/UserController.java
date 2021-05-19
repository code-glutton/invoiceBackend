package com.practice.invoicingapp.controller;

import com.practice.invoicingapp.entities.Customer;
import com.practice.invoicingapp.entities.Invoice;
import com.practice.invoicingapp.entities.User;
import com.practice.invoicingapp.service.UserService;
import com.practice.invoicingapp.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User newUser(@RequestBody User user){
        return userService.createNewUser(user);
    }

    @DeleteMapping("/delete/{email}")
    public void deletedUser(@PathVariable String email){
        userService.deleteUser(email);
    }

    @GetMapping("/userDetail/{id}")
    public User getUserCont(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/getInvoice/{email}")
    public Set<Invoice>  getAllUserInvoice(@PathVariable String email){
        return userService.getAllUserInvoice(email);
    }

    @GetMapping("/add/customer/{email}")
    public Set<Customer> getEveryCustomer(@PathVariable String email){
        return userService.getAllCustomers(email);
    }
}
