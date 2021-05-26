package ro.zah.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.zah.auction.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM PRODUCT WHERE auction_id =?1", nativeQuery = true)
    List<Product> findAllProductsByAuctionId(Long id);
}
