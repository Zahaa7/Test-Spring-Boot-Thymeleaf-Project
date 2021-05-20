package ro.zah.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.zah.auction.model.Product;
import ro.zah.auction.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> editProductById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProductsByAuctionId(int id) {
        return productRepository.findAllByAuction(id);
    }
}
