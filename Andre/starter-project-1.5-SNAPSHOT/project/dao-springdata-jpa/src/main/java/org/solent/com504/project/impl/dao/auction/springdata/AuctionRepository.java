/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.auction.springdata;

import java.util.List;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andre
 */
@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
   
      
      @Query("select a from Auction a where a.date = :date and a.time = :time")
      public List<Auction> findByDate(@Param("data") String date, @Param("time") String time);
      
      // TODO: Implement the other methods
    
}
