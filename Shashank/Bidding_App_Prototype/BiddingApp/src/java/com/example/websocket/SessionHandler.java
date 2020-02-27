/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.websocket;

import com.example.objects.Bid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author shanky
 */
@ApplicationScoped
//@ApplicationScoped
public class SessionHandler {
    // bid id
    private int bidId = 1;
    
    private static Set<BidWebSocketServer> serverendpoints = new CopyOnWriteArraySet<>();
    // all the client sessions stored in HashSet
    //private final Set<Session> sessions = new HashSet<>();
    
    // all the bids stored in HashSet
    private final Set<Bid> bids = new HashSet<>();
    
    // add session
    public void addSession(BidWebSocketServer serverendpoint) {
        serverendpoints.add(serverendpoint);
        
        System.out.println("Total sessions: "+ serverendpoints.size());
        
        for (Bid bid : bids) {
            JsonObject addMessage = createAddMessage(bid);
            sendToSession(serverendpoint, addMessage);
        }
    }
    
    // remove session
    public void removeSession(BidWebSocketServer serverendpoint) {
        serverendpoints.remove(serverendpoint);
    }
    
    // the methods that operate on the Bid object
    
    public List<Bid> getBids() {
        return new ArrayList<>(bids);
    }

    public void addBid(Bid bid) {
        bid.setId(bidId);
        bids.add(bid);
        bidId++;
        JsonObject addMessage = createAddMessage(bid);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeBid(int id) {
        //to be implemented
    }

    private Bid getBidById(int id) {
        return null;
    }

    private JsonObject createAddMessage(Bid bid) {
        //JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = Json.createObjectBuilder()
                .add("action", "add")
                .add("id", bid.getId())
                .add("value", bid.getValue())
                .build();
        return addMessage;
    }
    // end of methods for Bid object

    // send message to all the connection
    private void sendToAllConnectedSessions(JsonObject message) {
        
        serverendpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                sendToSession(endpoint, message);
            }
        });
    }
    
    // send message to current session
    private void sendToSession(BidWebSocketServer serverendpoint, JsonObject message) {
        try {
            serverendpoint.session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            serverendpoints.remove(serverendpoint);
            Logger.getLogger(SessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
