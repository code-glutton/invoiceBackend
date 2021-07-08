package com.practice.invoicingapp.controller;

import com.practice.invoicingapp.entities.*;
import com.practice.invoicingapp.security.UserDetailsServiceImp;
import com.practice.invoicingapp.service.UserService;
import com.practice.invoicingapp.service.UserServiceImpl;
import com.practice.invoicingapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    @Qualifier("userdetailsserviceimpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    AuthenticationManager authenticationManager;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }



    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try { authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));}
                catch(BadCredentialsException e) {
                    throw new Exception("Incorrect username or password", e);
                }
                final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
                final String jwt = jwtUtil.generateToken(userDetails);
                return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/getByEmail/{email}")
    public String testApi(@PathVariable String email){
        Optional<User> user = userService.getUserByEmail(email);
        System.out.println(user.get());
        return "<h1>successful</h1>";
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
