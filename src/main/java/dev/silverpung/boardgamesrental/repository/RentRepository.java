package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    default Rent getValidRentById(Long id) {
        return findById(id).orElseThrow(
                () -> new jakarta.persistence.EntityNotFoundException("Rent on id " + id + " not found"));
    }
}
