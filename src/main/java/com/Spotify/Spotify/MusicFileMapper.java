package com.Spotify.Spotify;

import java.io.*;
import java.sql.*;

public class MusicFileMapper {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "yoloswag";

        String selectQuery = "SELECT url FROM song";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                String url = resultSet.getString("url");
                String musicFilePath = resultSet.getString("url");

                playMusic(musicFilePath);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(String musicFilePath) throws IOException {
        File musicFile = new File(musicFilePath);

        if (musicFile.exists()) {
            // Use your music player library to play the music file
            // Example: Play the music using a media player library
        } else {
            System.out.println("Music file not found: " + musicFilePath);
        }
    }
}

