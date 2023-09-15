package com.Spotify.Spotify.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "spotify_users")
public class User {

    @Column(name = "user_id") // Mapping - user_id in the table - here in java its userId
    @Id                                             // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatic generated id, strategy - how it generated
    protected Long userId;

    @Column(name = "username")
    protected String userName;

    @Column(name = "email")
    protected String emailAddress;

    @Column(name = "password_hash")
    protected String passwordHash;

    @Column(name = "birth_date")
    protected String birthDate;

    @Column(name = "gender")
    protected String gender;

    @Column(name = "created_at")
    protected String createdAt;

    @Column(name = "last_login")
    protected String lastLogin;

    @Column(name = "profile_image")
    protected String profileImage;

}
