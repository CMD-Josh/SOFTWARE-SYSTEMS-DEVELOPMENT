package org.solent.com504.project.model.lot.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.solent.com504.project.model.bid.dto.Bid;

public class Lot {

    private Long id;

    private Double reservedPrice = 0.0;

    private Double highestBidPrice = 0.0;

    private Integer duration;

    private Date pickdate;

    private String grade;

    private Integer life_days;

    private Integer quantity;

    private List<Bid> bids = new ArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getReservedPrice() {
        return reservedPrice;
    }

    public void setReservedPrice(Double reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    public Double getHighestBidPrice() {
        return highestBidPrice;
    }

    private void setHighestBidPrice() {
        //TODO: find the current highest bid in the bids array
        Bid bid = bids.get(bids.size() - 1);
        this.highestBidPrice = bid.getValue();
        
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getPickdate() {
        return pickdate;
    }

    public void setPickdate(Date pickdate) {
        this.pickdate = pickdate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getLife_days() {
        return life_days;
    }

    public void setLife_days(Integer life_days) {
        this.life_days = life_days;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    
    //before adding a bid to a lot, the bid must have a higher value that the bid that was added before
    public Boolean addBid(Bid bid){
        //before adding a bid to a lot, the bid must have a higher value that the bid that was added before
        if(bid.getValue() > highestBidPrice && bid.getValue() > reservedPrice){
                bids.add(bid);
                //set the new highest bid price
                setHighestBidPrice();
                return true;
            }
        
        return false;
    }
    
    
}
