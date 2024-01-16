package com.juniorjjr.demo.service;

import com.juniorjjr.demo.domain.User;
import org.springframework.stereotype.Service;

@Service
public class NotficationService {

    public void SendNotification (User user, String massage){

        String email = user.getEmail();

        System.out.println(email + massage);
    }
}
