package ro.zah.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.zah.auction.model.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
