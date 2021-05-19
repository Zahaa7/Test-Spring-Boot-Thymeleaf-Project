package ro.zah.auction.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BID")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id", nullable = false, updatable = false)
    private Long bidId;

    @Column(name = "bid_date")
    private LocalDateTime bidDate;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @Column(name = "bid_value")
    private Double bidValue;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
