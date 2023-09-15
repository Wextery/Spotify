package com.Spotify.Spotify.Service;

import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    SongRepository songRepository;

    public Optional<Song> findSongById(Long id) {
        return songRepository.findById(id);
    }

    public Song createSong (Song song) {
        if (song.getSongName().isEmpty())
            throw new IllegalArgumentException("Enter the name of the song !");

        return songRepository.save(song);
    }

    public byte[] getAudioData(Long songId) throws IOException {

        Optional<Song> songOptional = songRepository.findById(songId);

        if (songOptional.isPresent()) {
            Song song = songOptional.get();

            return readAudioDataFromFile(song.getFilePath());
        } else {
            throw new IOException ("Song not found with ID: " + songId);
        }
    }

    private byte[] readAudioDataFromFile(String audioFilePath) throws IOException {

        Path filePath = Path.of(audioFilePath);
        return Files.readAllBytes(filePath);
    }
}
