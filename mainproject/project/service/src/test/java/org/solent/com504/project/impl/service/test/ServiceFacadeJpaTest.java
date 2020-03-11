/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.auction.dto.AuctionType;
import org.solent.com504.project.model.auction.service.AuctionService;
import org.solent.com504.project.model.bid.dto.Bid;
import org.solent.com504.project.model.lot.dto.Lot;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author gallenc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/appconfig-service.xml"})
public class ServiceFacadeJpaTest {

    final static Logger LOG = LogManager.getLogger(ServiceFacadeJpaTest.class);

    @Autowired
    ServiceFacade serviceFacade = null;
    
    @Autowired
    AuctionService auctionService = null;
    @Test
    public void testFactory() {
        LOG.debug("start ServiceFacadeTest testFpartyy");
        assertNotNull(serviceFacade);
        
        LOG.debug("end ServiceFacadeTest testFpartyy");
    }

    @Test
    public void testGetHeartbeat() {
        LOG.debug("start ServiceFacadeTest testGetHeartbeat()");
        assertNotNull(serviceFacade);

        String heartbeat = serviceFacade.getHeartbeat();
        LOG.debug("recieved heartbeat: " + heartbeat);
        assertNotNull(heartbeat);

        LOG.debug("end FarmFacadeTest testGetHeartbeat()");
    }

    @Test
    public void testGetAllByPartyRole() {

        // you may want to create people first but you need to add this to the facade :)
        List<Party> partyList = serviceFacade.findByPartyRole(null);
        assertNotNull(partyList);

        partyList = serviceFacade.findByPartyRole(PartyRole.UNDEFINED);
        assertNotNull(partyList);

    }
    
    @Test
    public void testGettingAuctionsService(){ //this test passes
        
        assertNotNull(auctionService);
        
        Auction auction = new Auction();
        
        auction.setStartTime(new Date());
                
        auctionService.saveAuction(auction); //we can save an auction
        
        List<Auction> auctions = auctionService.getAuctions();  //we can get auctions
        
        for(int i=0; i < auctions.size(); i++) {
            LOG.debug(auctions.get(i).toString());
        }
        
        assertNotNull(auctions);
        
        auctionService.deleteAllAuctions(); //we delete all auctions
        
        //after auctions are delete, it returns an empty array. Not a null value
        auctions = auctionService.getAuctions();
        
        assertTrue(auctions.isEmpty() == true);
        
    }
    
    @Test
    public void testDeleteSingleAuction(){ //this test does not pass
        
        assertNotNull(auctionService);
        
        Auction auction = new Auction();
    
        auction.setAuctionType(AuctionType.STANDARD);
        auction.setStartTime(new Date());
        
        Lot lot1 = createRandomLot(createRandomBidListForLot());
        Lot lot2 = createRandomLot(createRandomBidListForLot());
        Lot lot3 = createRandomLot(createRandomBidListForLot());
        
        Set<Lot> lots = new HashSet();
        lots.add(lot1);
        lots.add(lot2);
        lots.add(lot3);
        
        auction.setLots(lots);
        
        auctionService.saveAuction(auction);
        
        
        LOG.debug("@@@");
    }

    private List<Bid> createRandomBidListForLot(){
        List<Bid> bids = new ArrayList();

        for (int i = 0; i < 10; i++){
            Bid bid = new Bid();
            Date date = new Date();
            bid.setTimeStamp(date);
            bid.setLot(null);
            //just to test the filter inside the lot object shouldn't add a bid if the value is lower than the reserved price
            bid.setValue(7.0 + i);
            bids.add(bid);
        }
        
        return bids;
    }
       
    private Lot createRandomLot(List<Bid>bids){
        Lot lot = new Lot();
        lot.setDuration(1);
        lot.setGrade("Test grade: " + (Math.random() * 20));
        lot.setQuantity(10);
        lot.setReservedPrice(10.0);
        lot.setPickdate(null);
        lot.setLife_days(12);
        
        for(int i = 0; i < bids.size(); i++){
            lot.addBid(bids.get(i));
        }
        
        return lot;
    }

}
