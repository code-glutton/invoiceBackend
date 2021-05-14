package com.practice.invoicingapp.service;

import com.practice.invoicingapp.config.UserExists;
import com.practice.invoicingapp.config.UserNotFound;
import com.practice.invoicingapp.entities.User;
import com.practice.invoicingapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
