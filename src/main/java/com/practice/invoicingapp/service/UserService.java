package com.practice.invoicingapp.service;

import com.practice.invoicingapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public User getUserById(Long id);
    public User createNewUser(User user);
    public void deleteUser(String email);

}
