package com.Spotify.Spotify.Service;

import com.Spotify.Spotify.DTO.LoginDTO;
import com.Spotify.Spotify.DTO.UserDTO;
import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findByNameContaining(String name) {
        return userRepository.findByNameContaining(name);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User registerUser (User user) {
        if (user.getEmailAddress().isEmpty())
            throw new IllegalArgumentException("Enter email address !");
        if (user.getUserName().isEmpty())
            throw new IllegalArgumentException("Enter user name !");
        if (user.getPasswordHash().isEmpty())
            throw new IllegalArgumentException("Enter password !");
        if (user.getBirthDate().isEmpty())
            throw new IllegalArgumentException("Enter birth date !");

        return userRepository.save(user);
    }




    public void registerUser(UserDTO userDTO) {
        // Implement user registration logic and save user data to the database
    }

    public String loginUser(LoginDTO loginDTO) {
        // Implement user login and authentication logic using Spring Security
        // Return a JWT token upon successful authentication
        return "";
    }
}

