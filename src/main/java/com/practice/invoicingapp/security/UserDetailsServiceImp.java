package com.practice.invoicingapp.security;

import com.practice.invoicingapp.entities.User;
import com.practice.invoicingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userdetailsserviceimpl")
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userService.getUserByEmail(s);
        // System.out.println(user.get());
        user.orElseThrow(() -> new UsernameNotFoundException("not found:" + s));
        return user.map(UserDetailsImpl::new).get();
    }
}
