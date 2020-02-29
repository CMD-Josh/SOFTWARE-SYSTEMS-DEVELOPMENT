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
import java.util.concurrent.CopyOnWriteArrayList;
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
    
    Bid currentHighestBid;
    
    // bid id
    private int bidId = 1;
    
    private static Set<BidWebSocketServer> serverendpoints = new CopyOnWriteArraySet<>();
    // all the client sessions stored in HashSet
    //private final Set<Session> sessions = new HashSet<>();
    
    // all the bids stored in HashSet
    private static List<Bid> bids = new CopyOnWriteArrayList<>();
    
    public SessionHandler(){
        int totalbids = bids.size();
        //System.out.println("Total bids "+ totalbids);
        if(totalbids == 0){
            currentHighestBid = new Bid();
            currentHighestBid.setId(0);
            currentHighestBid.setValue(0);
        }else{
            currentHighestBid = bids.get(totalbids - 1);
        }
        //System.out.println("Current hight bid value "+ currentHighestBid.getValue());
    }
    
    // add session
    public void addSession(BidWebSocketServer serverendpoint) {
        serverendpoints.add(serverendpoint);
        
        System.out.println("Total sessions: "+ serverendpoints.size());
        
        JsonObject hightBidMessage = createHighestBidMessage(currentHighestBid);
        sendToSession(serverendpoint, hightBidMessage);
        
// commented for future use purpose <-----------------------
//        for (Bid bid : bids) {
//            JsonObject addMessage = createAddMessage(bid);
//            sendToSession(serverendpoint, addMessage);
//        }
//--------------------->

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
        
        currentHighestBid = bid;
        
        bidId++;
        
        JsonObject addBidMessage = createAddBidMessage(bid);
        sendToAllConnectedSessions(addBidMessage);
        
        JsonObject hightBidMessage = createHighestBidMessage(currentHighestBid);
        sendToAllConnectedSessions(hightBidMessage);
    }
    
    public void addBidError(BidWebSocketServer serverendpoint, String message){
        JsonObject errorMessage = createErrorMessage(message);
        sendToSession(serverendpoint, errorMessage);
    }

    public void removeBid(int id) {
        //to be implemented
    }

    private Bid getBidById(int id) {
        return null;
    }
    
    private JsonObject createAddBidMessage(Bid bid) {
        //JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = Json.createObjectBuilder()
                .add("action", "add")
                .add("id", bid.getId())
                .add("value", bid.getValue())
                .build();
        return addMessage;
    }
    
    private JsonObject createHighestBidMessage(Bid bid) {
        JsonObject HighestBidMessage = Json.createObjectBuilder()
                .add("action", "highest")
                .add("id", bid.getId())
                .add("value", bid.getValue())
                .build();
        return HighestBidMessage;
    }
    
    private JsonObject createErrorMessage(String errorString) {
        
        JsonObject HighestBidMessage = Json.createObjectBuilder()
                .add("action", "error")
                .add("value", errorString)
                .build();
        return HighestBidMessage;
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
