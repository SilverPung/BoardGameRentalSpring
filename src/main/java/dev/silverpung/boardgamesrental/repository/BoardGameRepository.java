package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.BoardGame;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {


    default BoardGame getValidBoardGameById(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException("BoardGame on id " + id + " not found"));
    }

}
