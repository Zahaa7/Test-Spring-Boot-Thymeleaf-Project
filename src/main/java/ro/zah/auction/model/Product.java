package ro.zah.auction.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
//@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable = false)
    private Long productId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "product_image")
    private String productImageURL;

    @Column(name = "base_price")
    private Double basePrice;

    @Column(name = "settled_price")
    private Double settledPrice;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Bid> bids;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;
}
