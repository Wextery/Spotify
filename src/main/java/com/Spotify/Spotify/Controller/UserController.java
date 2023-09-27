package com.Spotify.Spotify.Controller;

import com.Spotify.Spotify.Exception.UserAuthenticationException;
import com.Spotify.Spotify.Exception.UserRegistrationException;
import com.Spotify.Spotify.DTO.LoginDTO;
import com.Spotify.Spotify.DTO.UserDTO;
import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.UserRepository;
import com.Spotify.Spotify.Service.UserService;
import com.Spotify.Spotify.Util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/spotify/user")
public class UserController {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            User registeredUser = userService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (UserRegistrationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {

            User authenticatedUser = userService.loginUser(loginDTO);

            String token = jwtUtil.generateToken(authenticatedUser.getUserName());
            return ResponseEntity.ok(token);
        } catch (UserAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: Invalid username or password");
        }
    }


}
