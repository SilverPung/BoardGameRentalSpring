package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.Overseer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverseerRepository extends JpaRepository<Overseer, Long> {

    default Overseer getValidOverseerById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException("Overseer on id " + id + " not found"));
    }
}
