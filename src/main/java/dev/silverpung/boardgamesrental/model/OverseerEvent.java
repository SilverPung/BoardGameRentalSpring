package dev.silverpung.boardgamesrental.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.silverpung.boardgamesrental.model.types.PermissionsType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"overseerId", "eventId"})})
public class OverseerEvent implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PermissionsType permissionsType;

    @JsonIgnoreProperties("overseerEvents")
    @ManyToOne
    @JoinColumn(name = "overseerId", nullable = false)
    private Overseer overseer;

    @JsonIgnoreProperties("overseerEvents")
    @ManyToOne
    @JoinColumn(name = "eventId", nullable = false)
    private Event event;

    public OverseerEvent(PermissionsType permissionsType, Overseer overseer, Event event) {
        this.permissionsType = permissionsType;
        this.overseer = overseer;
        this.event = event;
    }

    @Override
    public String toString() {
        return "OverseerEvent{" +
                "id=" + id +
                ", permissionsType=" + permissionsType +
                ", overseer=" + overseer +
                ", event=" + event +
                '}';
    }

    @Override
    public String getAuthority() {
        return permissionsType.toString();
    }
}
