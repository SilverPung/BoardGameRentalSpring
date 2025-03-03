package dev.silverpung.boardgamesrental.model;


import dev.silverpung.boardgamesrental.model.request.RentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean returned;

    @ManyToOne
    @JoinColumn(name = "renterId", nullable = false)
    private Renter renter;

    @ManyToOne
    @JoinColumn(name = "boardGameId", nullable = false)
    private BoardGame boardGame;

    public void setData(RentRequest rentRequest){
        this.returned = rentRequest.isReturned();
    }
}
