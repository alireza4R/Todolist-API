package com.Alireza.Todolist.security;

import com.Alireza.Todolist.entity.UserEntity;
import com.Alireza.Todolist.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new User(user.getEmail(), user.getPassHash(), Collections.emptyList());
    }
}
