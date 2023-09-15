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
@Repository
@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, Long> {

   /* @Query("select u from User u where u.userId like %:searchLong%")
    List<User> searchByUserId(@Param("searchLong") Long searchLong);

    */
   List<User> findAllByUserName(String userName);

    @RestResource(path = "userName")
    List<User> findByNameContaining(String userName);

   @Query("select u from User u where lower(u.userName) like %:searchString%")
    List<User> searchByUserName(@Param("searchString") String searchString);

    @Query("select u from User u where lower(u.emailAddress) like %:searchString%")
    List<User> searchByEmailAddress(@Param("searchString") String searchString);

    @Query("select u from User u where u.createdAt like %:searchLong%")
    List<User> searchByCreatedAt(@Param("searchLong") String searchLong);

}
