package com.Spotify.Spotify.Service;

import com.Spotify.Spotify.Service.Message.EmailMessage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEmailListener implements ApplicationListener<EmailMessage> {

    @Override
    public void onApplicationEvent(EmailMessage event){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Received email notification - " + event.getMessage());
    }
}
