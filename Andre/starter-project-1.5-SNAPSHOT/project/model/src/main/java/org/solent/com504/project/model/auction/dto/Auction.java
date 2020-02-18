/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.auction.dto;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andre
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Auction {

    private Long id;
    private String date; 
    private String time; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(String date){
        this.date = date;
    }
    
    public String getDate(){
        return date;
    }

    public void setTime(String time){
        this.time = time;
    }
    
    public String getTime(){
        return time;
    }


    @Override
    public String toString() {
        return "Auction{" + "id=" + id + ", Date=" + date + ", Time=" + time + ", uuid=" + id + '}';
    }

}
