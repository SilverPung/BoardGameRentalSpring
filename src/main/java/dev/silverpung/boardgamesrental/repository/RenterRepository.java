package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepository extends JpaRepository<Renter, Long> {
}
