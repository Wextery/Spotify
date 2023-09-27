package com.Spotify.Spotify.Repository;

import com.Spotify.Spotify.Model.Song;
import com.Spotify.Spotify.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserName(String userName);

    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String username);

    boolean existsByEmailAddress(String email);

    @Query("select u from User u where lower(u.userName) like %:searchString%")
    List<User> searchByUserName(@Param("searchString") String searchString);

    @Query("select u from User u where lower(u.emailAddress) like %:searchString%")
    List<User> searchByEmailAddress(@Param("searchString") String searchString);

    @Query("select u from User u where u.createdAt like %:searchLong%")
    List<User> searchByCreatedAt(@Param("searchLong") String searchLong);

}
