package com.Spotify.Spotify.DTO;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userName;
    private String password;
    private String emailAddress;
}
