package com.Spotify.Spotify;

import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserRestTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    void getUserNotFound() throws Exception {

        MvcResult result = mockMvc.perform(get("/users/99999"))
                .andDo(print())
                .andReturn();

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void testGetUser() throws Exception {

        User user = userRepository.findAll().iterator().next();

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/users/" + user.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user.getUserName()))
                .andReturn();
    }

    @Test
    void postUserSuccess() throws Exception {
        mockMvc.perform(post("/users")
                        .content("{\"name\":\"meno\"}")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void deleteUserNotAllowed() throws Exception {
        int count = (int) userRepository.count();

        mockMvc.perform(delete("/users/1"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed())
                .andReturn();

        assertThat(userRepository.count()).isEqualTo(count);
    }

}
