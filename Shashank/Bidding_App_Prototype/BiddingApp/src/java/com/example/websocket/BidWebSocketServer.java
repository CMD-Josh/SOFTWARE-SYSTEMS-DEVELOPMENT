/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.websocket;

import com.example.objects.Bid;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author shanky
 */
@ApplicationScoped
@ServerEndpoint("/actions")
public class BidWebSocketServer {
    
    Session session;
    
    //@Inject
    private SessionHandler sessionHandler = new SessionHandler();
    
    @OnOpen
    public void open(Session session) {
        
        System.out.println("New session opened with ID " + session.getId());
        this.session = session;
        sessionHandler.addSession(this);
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Session - "+ session.getId()+", Closed");
        sessionHandler.removeSession(this);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(BidWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("Session - "+ session.getId()+ ", sent message - " + message);
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("add".equals(jsonMessage.getString("action"))) {
                Bid bid = new Bid();
                bid.setValue(Integer.parseInt(jsonMessage.getString("value")));
                
                sessionHandler.addBid(bid);
            }
        }
        
    }
}
