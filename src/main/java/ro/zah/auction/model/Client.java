package ro.zah.auction.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("ROLE_USER")
@NoArgsConstructor

public class Client extends User {

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Bid> bids;

    public Client(Long userId, String firstName, String lastName, String username, String email, String password,
                  String profileImageURL, String gender, String address, LocalDate joinDate, List<Bid> bids) {
        super(userId, firstName, lastName, username, email, password, profileImageURL, gender, address, joinDate);

        this.bids = bids;
    }
}
