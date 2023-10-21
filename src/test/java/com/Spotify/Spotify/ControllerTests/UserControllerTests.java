package com.Spotify.Spotify.ControllerTests;

import com.Spotify.Spotify.DTO.LoginDTO;
import com.Spotify.Spotify.DTO.UserDTO;
import com.Spotify.Spotify.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        /*
        @Test
        public void testLoginUser() throws Exception {

            LoginDTO loginDTO = new LoginDTO("Patric Selles","freshmeat", "patricselles@gmail.com");

            UserDTO mockUserDTO = new UserDTO();
            mockUserDTO.setUserName("testUser");

            Mockito.when(userService.loginUser(Mockito.any(LoginDTO.class))).thenReturn(mockUserDTO);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(loginDTO)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("testUser")));
        }



        @Test
        public void testRegisterUser() throws Exception {

            UserDTO userDTO = new UserDTO("testUser", "test@example.com", "password");

            UserDTO mockUserDTO = new UserDTO();
            mockUserDTO.setUserName("testUser");

            Mockito.when(userService.registerUser(Mockito.any(UserDTO.class))).thenReturn(mockUserDTO);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(userDTO)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("testUser")));
        }

         */

        @Test
        public void testDeleteUser() throws Exception {

            Long userId = 2L;

            Mockito.doNothing().when(userService).deleteUserById(userId);

            mockMvc.perform(MockMvcRequestBuilders.delete("/spotify/user/deleteUser/{id}", userId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        private static String asJsonString(final Object obj) {
            try {
                final ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

