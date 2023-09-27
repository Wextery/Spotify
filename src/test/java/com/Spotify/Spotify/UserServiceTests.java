package com.Spotify.Spotify;

import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.UserRepository;
import com.Spotify.Spotify.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
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
    void testFindUserById() {

    }
}
