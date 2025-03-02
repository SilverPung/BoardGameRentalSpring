package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.OverseerEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverseerEventRepository extends JpaRepository<OverseerEvent, Long> {
}
