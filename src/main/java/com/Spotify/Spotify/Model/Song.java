package com.Spotify.Spotify.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.h2.api.Interval;

import java.time.Duration;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    protected Long songId;

    @Column(name = "song_name")
    protected String songName;

    @Column(name = "date_release")
    protected Date dateRelease;

    @Column(name = "artist")
    protected String artistName;

    @Column(name = "release_type")
    protected String releaseType;

    @Column(name = "duration")
    protected Duration songDuration;

    @Column(name = "genre")
    protected String songGenre;

    @Column(name = "file_path")
    protected String filePath;


}
