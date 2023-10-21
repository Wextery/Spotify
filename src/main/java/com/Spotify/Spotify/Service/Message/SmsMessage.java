package com.Spotify.Spotify.Service.Message;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SmsMessage extends ApplicationEvent {
    private String message;
    private String phoneNumber;

    public SmsMessage(Object source, String message, String phoneNumber) {
        super(source);
        this.message = message;
        this.phoneNumber = phoneNumber;
    }
}
