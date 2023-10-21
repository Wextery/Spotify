package com.Spotify.Spotify.Service.Message;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmailMessage extends ApplicationEvent {
    private String message;
    private String emailAddress;

    public EmailMessage(Object source,String emailAddress, String message) {
        super(source);
        this.message = message;
        this.emailAddress = emailAddress;
    }
}
