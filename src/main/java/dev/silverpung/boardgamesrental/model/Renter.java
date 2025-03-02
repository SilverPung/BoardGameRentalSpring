package dev.silverpung.boardgamesrental.model;


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
    private String name;

    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.REMOVE)
    private Set<Rents> rentedGames;

    public Renter(String barcode, String name) {
        this.barcode = barcode;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
