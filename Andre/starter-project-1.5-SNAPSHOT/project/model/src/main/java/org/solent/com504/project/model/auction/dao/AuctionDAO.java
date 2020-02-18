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
 * @author Andre
 */
public interface AuctionDAO {
    public Auction findById(Long id);

    public Auction save(Auction auction);

    public List<Auction> findAll();

    public void deleteById(long id);

    public void delete(Auction auction);

    public void deleteAll();

    public Auction findByUuid(String uuid);
}
