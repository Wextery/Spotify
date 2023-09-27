package com.Spotify.Spotify.Service;

import com.Spotify.Spotify.Exception.UserAuthenticationException;
import com.Spotify.Spotify.Exception.UserRegistrationException;
import com.Spotify.Spotify.DTO.LoginDTO;
import com.Spotify.Spotify.DTO.UserDTO;
import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.UserRepository;
import com.Spotify.Spotify.Util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Autowired
    UserRepository userRepository;



    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) throws IllegalArgumentException {
        if (user.getUserName() == null || user.getUserName().length() < 3)
            throw new IllegalArgumentException("Name is too short");
        if (user.getEmailAddress() == null || user.getEmailAddress().equals("") || !user.getEmailAddress().contains("@"))
            throw new IllegalArgumentException("Email address is required.");

        return userRepository.save(user);
    }


    public void deleteUserById(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }

        userRepository.deleteById(userId);
    }

    


    @Transactional
    public User registerUser(UserDTO userDTO) {
        if (userRepository.existsByUserName(userDTO.getUserName())) {
            throw new UserRegistrationException("Username is already taken.");
        }

        if (userRepository.existsByEmailAddress(userDTO.getEmailAddress())) {
            throw new UserRegistrationException("Email is already registered.");
        }

        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmailAddress(userDTO.getEmailAddress());

        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));

        user = createUser(user);

        return user;
    }


    public User loginUser(LoginDTO loginDTO) {
        String username = loginDTO.getUserName();
        String password = loginDTO.getPassword();

        Optional<User> userOptional = userRepository.findByUserName(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(password, user.getPasswordHash())) {

                jwtUtil.generateToken(username);

                return user;
            }
        }

        throw new UserAuthenticationException("Invalid username or password");

    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

}

