package com.Spotify.Spotify.Repository;

import com.Spotify.Spotify.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RepositoryRestResource(path="songs")
public interface SongRepository extends JpaRepository<Song, Long>{

    @Query("SELECT s FROM Song s WHERE s.songId = 1")
    List<Song> findSongById();

    @Query("select s from Song s where lower(s.songName) like %:searchString%")
    List<Song> searchSongByName(@Param("searchString") String searchString);

    @Query("select s from Song s where lower(s.artistName) like %:searchString%")
    List<Song> searchSongByArtistName(@Param("searchString") String searchString);

    @Query("select s from Song s where lower(s.songGenre) like %:searchString%")
    List<Song> searchSongByGenre(@Param("searchString") String searchString);

    @Query("select s from Song s where lower(s.releaseType) like %:searchString%")
    List<Song> searchSongByReleaseType(@Param("searchString") String searchString);

    @Query("select s from Song s where s.dateRelease like %:searchLong%")
    List<Song> searchSongByDateRelease(@Param("searchLong") String searchLong);
}
