package org.solent.com504.project.model.dto;

import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.party.dto.Party;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReplyMessage {

    private Integer code = null;

    private String debugMessage = null;

    @XmlElementWrapper(name = "stringlist")
    @XmlElement(name = "string")
    private List<String> stringList = null;

    @XmlElementWrapper(name = "partylist")
    @XmlElement(name = "party")
    private List<Party> partyList = null;
    
    @XmlElementWrapper(name = "auctionlist")
    @XmlElement(name = "auction")
    private Set<Auction> auctionList = null; 
  

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<Party> getPartyList() {
        return partyList;
    }

    public void setPartyList(List<Party> partyList) {
        this.partyList = partyList;
    }

    public Set<Auction> getAuctionList() {
        return auctionList;
    }

    public void setAuctionList(Set<Auction> auctionList) {
        this.auctionList = auctionList;
    }   

}
