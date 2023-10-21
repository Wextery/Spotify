package com.Spotify.Spotify.Service;

import com.Spotify.Spotify.Service.Message.SmsMessage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationSmsListener implements ApplicationListener<SmsMessage> {

    @Override
    public void onApplicationEvent(SmsMessage event){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Received sms message - " + event.getMessage());
    }
}