package com.Spotify.Spotify;

import com.Spotify.Spotify.Config.JwtTokenProvider;
import com.Spotify.Spotify.Controller.AuthController;
import com.Spotify.Spotify.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthController authController;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private UserService userService;

    @Test
    public void testAuthenticate_Success() throws Exception {
        // Mock your user details and token generation
        when(userService.findByNameContaining("And")).thenReturn(someUserDetails);
        when(jwtTokenProvider.generateToken(someUserDetails)).thenReturn("mocked-jwt-token");

        // Perform a mock POST request to the authentication endpoint
        mockMvc.perform(post("/authenticate")
                        .content("{\"username\":\"username\",\"password\":\"password\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


}

