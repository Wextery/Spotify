package com.Spotify.Spotify;

import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.UserRepository;
import com.Spotify.Spotify.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    void testCreateUser() {

        User user = new User();
        user.setUserName("Abraham Lincoln");
        user.setEmailAddress("abrahamlincoln@gmail.com");

        user = userService.createUser(user);

        assertThat(user.getUserId()).isNotNull();
    }

    @Test
    void testFindUser() {
        Optional<User> userOptional = userService.findUserById(100L);

        assertThat(userOptional).isPresent();
        assertThat(userOptional.get().getUserName()).isEqualTo("Andrej Pernica");
    }

    @Test
    void testFindUserNotFound() {
        Optional<User> libraryOptional = userService.findUserById(200L);

        assertThat(libraryOptional).isEmpty();
    }

}
