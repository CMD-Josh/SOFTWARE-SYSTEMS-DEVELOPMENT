/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.rest;

/**
 *
 * @author gallenc
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.auction.service.AuctionService;
import org.solent.com504.project.model.bid.dto.Bid;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.lot.dto.Lot;
import org.solent.com504.project.model.service.ServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * To make the ReST interface easier to program. All of the replies are
 * contained in ReplyMessage classes but only the fields indicated are populated
 * with each reply. All replies will contain a code and a debug message.
 * Possible replies are: List<String> replyMessage.getStringList() AnimalList
 * replyMessage.getAnimalList() int replyMessage.getCode()
 * replyMessage.getDebugMessage(); * @author cgallen
 */
@Component // component allows resource to be picked up
@Path("/auctionService")
public class RestService {

    // SETS UP LOGGING 
    // note that log name will be org.solent.com504.project.impl.rest.RestService
    final static Logger LOG = LogManager.getLogger(RestService.class);

    // This serviceFacade object is injected by Spring
    @Autowired(required = true)
    @Qualifier("serviceFacade")
    ServiceFacade serviceFacade = null;

    @Autowired(required = true)
    @Qualifier("auctionService")
    AuctionService auctionService = null;

    /**
     * this is a very simple rest test message which only returns a string
     *
     * http://localhost:8084/projectweb/rest/auctionService/getHeartbeat
     *
     * @return String simple message
     */
    @GET
    public String message() {
        LOG.debug("appointmentService called");
        return "Hello, rest!";
    }

    /**
     * get heartbeat
     *
     * http://localhost:8084/projectweb/rest/auctionService/getHeartbeat
     *
     * @return Response OK and heartbeat in debug message
     */
    @GET
    @Path("/getHeartbeat")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getHeartbeat() {
        try {

            ReplyMessage replyMessage = new ReplyMessage();
            LOG.debug("/getHeartbeat called");

            if (serviceFacade == null) {
                throw new RuntimeException("serviceFacade==null and has not been initialised");
            }

            String heartbeat = serviceFacade.getHeartbeat();
            replyMessage.setDebugMessage(heartbeat);

            replyMessage.setCode(Response.Status.OK.getStatusCode());

            return Response.status(Response.Status.OK).entity(replyMessage).build();

        } catch (Exception ex) {
            LOG.error("error calling /getHeartbeat ", ex);
            ReplyMessage replyMessage = new ReplyMessage();
            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            replyMessage.setDebugMessage("error calling /getHeartbea " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();
        }
    }

    /**
     * http://localhost:8084/projectweb/rest/auctionService/getAuctions
     *
     * @return
     *
     */
    @GET
    @Path("/getAuctions")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAuctions() {

        try {
            
            //Creating dummy data @@@.
            
            // ##### auction 1 ####
            Lot lot1 = createRandomLot(createRandomBidListForLot());
            Lot lot2 = createRandomLot(createRandomBidListForLot());
            Lot lot3 = createRandomLot(createRandomBidListForLot());
    
            Set<Lot> lots = new HashSet();
            lots.add(lot1);
            lots.add(lot2);
            lots.add(lot3);

            Auction auction1 = new Auction();
            auction1.setLots(lots);
            

            

            // ##### auction 2 ####
            Lot lot4 = createRandomLot(createRandomBidListForLot());
            Lot lot5 = createRandomLot(createRandomBidListForLot());
            Lot lot6 = createRandomLot(createRandomBidListForLot());
    
            Set<Lot> lots2 = new HashSet();
            lots2.add(lot4);
            lots2.add(lot5);
            lots2.add(lot6);
            Auction auction2 = new Auction();
            auction2.setLots(lots2);
            
            //############# end ###############
            
            ReplyMessage replyMessage = new ReplyMessage();
            
            List<Auction> auctionList = new ArrayList();
            auctionList.add(auction1);
            auctionList.add(auction2);
            
            replyMessage.setAuctionList(auctionList);
            
            LOG.debug("/getAuctions called");

            if (auctionService == null) {
                throw new RuntimeException("auctionService==null and has not been initialised");
            }
            replyMessage.setAuctionList(auctionList);
            replyMessage.setCode(Response.Status.OK.getStatusCode());
            return Response.status(Response.Status.OK).entity(replyMessage).build();
        } catch (Exception ex) {

            LOG.error("error calling /getAuctions ", ex);

            ReplyMessage replyMessage = new ReplyMessage();

            replyMessage.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());

            replyMessage.setDebugMessage("error calling /getAuctions " + ex.getMessage());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(replyMessage).build();

        }

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
