package com.Spotify.Spotify;

import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Model.User;
import com.Spotify.Spotify.Repository.SongRepository;
import com.Spotify.Spotify.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SpotifyApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SongRepository songRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpotifyApplication.class, args);

		}
	}
