package dev.silverpung.boardgamesrental.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;
    private String description;

    @NotNull
    private Date date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private Set<OverseerEvent> overseerEvents;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private Set<BoardGame> boardGames;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    private Set<Renter> renters;


    public Event(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
