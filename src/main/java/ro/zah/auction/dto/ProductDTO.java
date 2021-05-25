package ro.zah.auction.dto;

import lombok.Data;
import ro.zah.auction.model.Bid;
import java.util.List;

@Data
public class ProductDTO {

    private Long productId;
    private String authorName;
    private String title;
    private String description;
    private String productImageURL;
    private Double basePrice;
    private Double settledPrice;
    private List<Bid> bids;
    private long auctionId;
}
