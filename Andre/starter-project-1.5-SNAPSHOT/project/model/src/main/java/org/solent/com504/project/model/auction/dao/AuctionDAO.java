package org.solent.com504.project.model.auction.dao;

import java.util.List;
import org.solent.com504.project.model.auction.dto.Auction;

public interface AuctionDAO {

    public Long findAuctionById(Long id);

    public List<Auction> findAll();

    public Auction deleteAutcion(Auction auction);

    public Long getAuctionLotsById(Long auctionId);

    public Auction updateAuction(Auction auction);
}
