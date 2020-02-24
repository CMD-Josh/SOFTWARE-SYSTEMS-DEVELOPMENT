/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.auction.dao;

import java.util.List;
import org.solent.com504.project.model.auction.dto.Auction;

/**
 *
 * @author Josh
 */
public interface AuctionDAO {
    
    Long findAuctionById(Long auctionId);
    
    List<Auction> findAll();
    
    Auction deleteAuction(Auction auction);
    
    Long getAuctionLotsById(Long auctionId);
    
    Auction updateAuction(Auction auction);
}
