package ro.zah.auction.model;


import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("ROLE_ADMIN")
@NoArgsConstructor
public class Admin extends User {

    public Admin(Long userId, String firstName, String lastName, String username, String email, String password,
                  String profileImageURL, String gender, String address, LocalDate joinDate) {
        super(userId, firstName, lastName, username, email, password, profileImageURL, gender, address, joinDate);
    }
}
