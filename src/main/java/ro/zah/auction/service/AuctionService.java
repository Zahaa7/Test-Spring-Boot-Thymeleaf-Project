package ro.zah.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.zah.auction.model.Auction;
import ro.zah.auction.repository.AuctionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    public AuctionRepository auctionRepository;

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    public void addAuction(Auction auction) {
        auctionRepository.save(auction);
    }

    public void deleteAuctionById(long id) {
        auctionRepository.deleteById(id);
    }

    public Optional<Auction> editAuctionById(long id) {
        return auctionRepository.findById(id);
    }

}
