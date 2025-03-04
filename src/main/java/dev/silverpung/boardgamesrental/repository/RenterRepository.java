package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.Renter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepository extends JpaRepository<Renter, Long> {

    default Renter getValidRenterById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException("Renter on id " + id + " not found"));
    }
}
