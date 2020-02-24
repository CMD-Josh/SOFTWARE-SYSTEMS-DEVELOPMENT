/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.auction.dto;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Josh
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name = "lots")
public class Lots {
    
    @Id
    private Long id;
    private Double reservedPrice;
    private Double highestPrice;
    private int duration;
    private Date pickDate;
    private String grade;   // What is grade being returned as? if it's just a single character then char would be a better data type just fyi
    private int lifeDays;
    private int quantity;
    
    @XmlElementWrapper(name = "bids")
    @XmlElement(name = "bid")
    private List<Bids> bids;

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

    public Double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getPickDate() {
        return pickDate;
    }

    public void setPickDate(Date pickDate) {
        this.pickDate = pickDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getLifeDays() {
        return lifeDays;
    }

    public void setLifeDays(int lifeDays) {
        this.lifeDays = lifeDays;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Bids> getBids() {
        return bids;
    }

    public void setBids(List<Bids> bids) {
        this.bids = bids;
    }
    
    
}
