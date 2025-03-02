package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.Overseer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverseerRepository extends JpaRepository<Overseer, Long> {
}
