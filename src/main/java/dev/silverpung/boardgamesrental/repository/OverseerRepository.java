package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.Overseer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OverseerRepository extends JpaRepository<Overseer, Long> {

    Optional<Overseer> getOverseerByUsername(String username);

    default Overseer getValidOverseerById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException("Overseer on id " + id + " not found"));

    }

    default Overseer getValidOverseerByUsername(String username) {
        return getOverseerByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Overseer with not found"));
    }


}
