package com.example.demo.security;

import com.example.demo.DTO.UsersDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService  {
    private UsersDTORepository usersDTORepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        usersDTORepository.findByUserName(username);
        return null;
    }
}
