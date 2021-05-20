package ro.zah.auction.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
//@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUCTION")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id", nullable = false, updatable = false)
    private Long auctionId;

    @Column(name = "auction_name")
    private String auctionName;

    @Column(name = "auction_state")
    private String auctionState;       // settled / expired

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private List<Product> products;
}
