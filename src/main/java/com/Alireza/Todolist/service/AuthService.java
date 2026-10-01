package com.Alireza.Todolist.service;

import com.Alireza.Todolist.entity.UserEntity;
import com.Alireza.Todolist.exception.EmailAlreadyExistsException;
import com.Alireza.Todolist.repository.UserRepository;
import com.Alireza.Todolist.security.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login (String email, String password) {
        Authentication loginAttempt =
                UsernamePasswordAuthenticationToken.unauthenticated(
                        email,
                        password
                );

        Authentication authenticatedUser =
                authenticationManager.authenticate(loginAttempt);


            return jwtService.generateToken(authenticatedUser);

    }

    public String register(String name, String email, String password) throws EmailAlreadyExistsException {
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException("User exist");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setName(name);
        userEntity.setPassHash(passwordEncoder.encode(password));
        userRepository.save(userEntity);
        return login(userEntity.getEmail(), password);
    }

}
