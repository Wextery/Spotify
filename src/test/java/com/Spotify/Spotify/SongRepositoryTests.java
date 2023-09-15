package com.Spotify.Spotify;

import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Repository.SongRepository;
import org.antlr.v4.runtime.misc.Interval;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.datatype.Duration;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SongRepositoryTests {

    @Autowired
    SongRepository songRepository;

    @Test
    void testFindAll() {
        List<Song> songList = songRepository.findAll();

        assertThat(songList).hasSize(5);
    }

    @Test
    void testCreateSong() {
        long count = songRepository.count();

        Song song = new Song();
        song.setSongName("Tranquil Echoes");
        song.setArtistName("Koush");
        song.setDateRelease(new Date(2000,9,1));
        songRepository.save(song);

        long count2 = songRepository.count();

        assertThat(count2).isEqualTo(count + 1);
    }

    @Test
    void testDeleteSong() {
        long count = songRepository.count();

        songRepository.deleteById(3L);

        long count2 = songRepository.count();

        assertThat(count2).isEqualTo(count - 1);
    }

}
