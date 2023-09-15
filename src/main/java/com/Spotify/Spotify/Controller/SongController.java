package com.Spotify.Spotify.Controller;

import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Repository.SongRepository;
import com.Spotify.Spotify.Service.SongService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/spotify/song")
public class SongController {

    @Autowired
    protected SongRepository songRepository;

    @Autowired
    protected SongService songService;


    @GetMapping("/getAllSongs")
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @PostMapping("/saveSong")
    Song newSong(@RequestBody Song newSong) {
        return songRepository.save(newSong);
    }

    @DeleteMapping("/deleteSong/{id}")
    void deleteSong(@PathVariable Long id) {
        songRepository.deleteById(id);
    }

    @CrossOrigin
    @GetMapping("/stream/{songId}")
    public ResponseEntity<byte[]> streamMusic(@PathVariable Long songId) throws IOException {
        byte[] audioData = songService.getAudioData(songId);
        if (audioData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(audioData, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
