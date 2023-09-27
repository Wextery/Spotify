package com.Spotify.Spotify;

import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



import java.awt.print.Pageable;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;


    @Test
    void testLoadUsers() {

        List<User> usersList = userRepository.findAll();

        assertThat(usersList).isNotEmpty();
    }

    @Test
    void testLoadUsersByName() {

        List<User> userList = userRepository.findAllByUserName("Andrej Pernica");

        assertThat(userList).isNotEmpty();
    }

    @Test
    void testCreateUser() {
        long count = userRepository.count();

        User user = new User();
        user.setUserName("New Person");
        user.setEmailAddress("newperson@gmail.com");
        userRepository.save(user);

        long count2 = userRepository.count();

        assertThat(count2).isEqualTo(count + 1);
    }

    @Test
    void testDeleteUser() {
        long count = userRepository.count();

        userRepository.deleteById(2L);

        long count2 = userRepository.count();

        assertThat(count2).isEqualTo(count - 1);
    }


}
