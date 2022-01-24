package LifeManager.WebServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import LifeManager.WebServer.model.FrontEndMessage;

@Service
public class WSService {

    private SimpMessagingTemplate wstemplate;

    @Autowired
    public WSService(SimpMessagingTemplate wstemplate){
        this.wstemplate = wstemplate;
    }

    public void notifyFrontend(FrontEndMessage message){
        System.out.println("Notifying");
        wstemplate.convertAndSend("/topic/messages", message);
    }
    
}
