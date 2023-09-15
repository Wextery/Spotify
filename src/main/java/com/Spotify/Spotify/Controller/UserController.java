package com.Spotify.Spotify.Controller;

import com.Spotify.Spotify.DTO.UserDTO;
import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.UserRepository;
import com.Spotify.Spotify.Service.UserService;
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

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/deleteUser/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
    /*
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        String token = userService.loginUser(loginDTO);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }*/
}
