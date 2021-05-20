package ro.zah.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.zah.auction.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByAuction(int id);
}
