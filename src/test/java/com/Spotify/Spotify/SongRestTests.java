package com.Spotify.Spotify;

import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Repository.SongRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SongRestTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SongRepository songRepository;

    @Test
    void getSongs() throws Exception {
        mockMvc.perform(get("/songs"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void getSongNotFound() throws Exception {

        MvcResult result = mockMvc.perform(get("/songs/999"))
                .andDo(print())
                .andReturn();

        Assertions.assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void getSongSuccess() throws Exception {

        MvcResult result = mockMvc.perform(get("/songs/1"))
                .andDo(print())
                .andReturn();

        Assertions.assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(result.getResponse().getContentAsString())
                .contains("IOTO");
    }

    @Test
    void patchSongSuccess() throws Exception {

        MvcResult result = mockMvc.perform(patch("/songs/1")
                        .content("{\"name\":\"KARMA\"}")
                        .accept("application/json"))
                .andDo(print())
                .andReturn();

        Assertions.assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());

        Optional<Song> song2 = songRepository.findById(1L);

        Assertions.assertThat(song2).isPresent();
        Assertions.assertThat(song2.get().getSongName())
                .contains("Map");


    }

    @Test
    void deleteSongSuccess() throws Exception {

        long count = songRepository.count();

        MvcResult result = mockMvc.perform(delete("/songs/1"))
                .andDo(print())
                .andReturn();

        long count2 = songRepository.count();

        Assertions.assertThat(count2).isEqualTo(count - 1);

    }
}
