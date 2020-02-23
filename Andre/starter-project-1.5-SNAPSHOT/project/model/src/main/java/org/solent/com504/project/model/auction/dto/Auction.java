package org.solent.com504.project.model.auction.dto;

import java.util.Date;
import java.util.List;
import org.solent.com504.project.model.lot.dto.Lot;

public class Auction {
    
    private Date startDate;

    private Date startTime;

    private AuctionType type;

    private List<Lot> lots;
    
    private AuctionType auctionType;

    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public AuctionType getType() {
        return type;
    }

    public void setType(AuctionType type) {
        this.type = type;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }


}
