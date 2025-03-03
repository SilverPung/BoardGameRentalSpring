package dev.silverpung.boardgamesrental.model;


import dev.silverpung.boardgamesrental.model.request.RenterRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Renter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String barcode;

    @NotNull
    private String userName;

    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.REMOVE)
    private Set<Rent> rentedGames;

    public Renter(String barcode, String userName) {
        this.barcode = barcode;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", name='" + userName + '\'' +
                '}';
    }

    public void setData(RenterRequest renterRequest) {
        this.barcode = renterRequest.getBarcode();
        this.userName = renterRequest.getUserName();
    }
}
