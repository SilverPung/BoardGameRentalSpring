package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.Overseer;
import dev.silverpung.boardgamesrental.model.OverseerEvent;
import dev.silverpung.boardgamesrental.model.types.PermissionsType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OverseerEventRepository extends JpaRepository<OverseerEvent, Long> {

    default OverseerEvent getValidOverseerEventById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException("OverseerEvent on id " + id + " not found"));
    }

    @Query("SELECT o.permissionsType FROM OverseerEvent o WHERE o.overseer = :overseer AND o.event.id = :eventId")
    PermissionsType findRoleByUserAndEventId(@Param("overseer") Overseer overseer, @Param("eventId") Long eventId);
}
