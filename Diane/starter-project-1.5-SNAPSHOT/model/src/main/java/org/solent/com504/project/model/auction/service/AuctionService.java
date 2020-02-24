/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.auction.service;

import java.util.Date;
import java.util.List;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.auction.dto.Lots;

/**
 *
 * @author Josh
 */
public interface AuctionService {
    
    // What do you want the ReST service to be able to do relative to the auction?
    
    void create(Auction auction);
    
    void deleteAuction(Auction auction);
    
    void deleteAuctionbyId(Long auctionId);
    
    Auction findByID(Long id);
    
    List<Auction> findAll();
    
    List<Lots> getAuctionLotsById(Long auctionID);
    
    Auction updateAuctionById(Long auctionId, Auction auction);
    
    
}
