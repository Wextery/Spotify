package com.Spotify.Spotify;

import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.SongRepository;
import com.Spotify.Spotify.Service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("test")
public class SongServiceTests {

    @Autowired
    SongService songService;

    @Autowired
    SongRepository songRepository;


    @Test
    void testCreateSong() {

        Song song = new Song();
        song.setSongName("Drop IN");
        song.setArtistName("GRiZ");

        song = songService.createSong(song);

        assertThat(song.getSongId()).isNotNull();
    }

    @Test
    void testReadAudioDataFromFile() {

        String audioFilePath = "/Users/andrew/Desktop/Spotify/SpotifyMusicStorage/LSDream - HIGH VIBRATION.mp3";

        try {

            byte[] audioData = songService.readAudioDataFromFile(audioFilePath);

            assertNotNull(audioData);
            assertTrue(audioData.length > 0);
        } catch (IOException e) {
            fail("Exception should not be thrown when reading the audio file.");
        }
    }


}
