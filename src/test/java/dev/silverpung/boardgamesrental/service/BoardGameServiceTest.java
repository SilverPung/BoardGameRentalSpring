package dev.silverpung.boardgamesrental.service;

import dev.silverpung.boardgamesrental.model.BoardGame;
import dev.silverpung.boardgamesrental.model.Event;
import dev.silverpung.boardgamesrental.model.request.BoardGameRequest;
import dev.silverpung.boardgamesrental.repository.BoardGameRepository;
import dev.silverpung.boardgamesrental.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BoardGameServiceTest {

    @InjectMocks
    private BoardGameService boardGameService;

    @Mock
    private BoardGameRepository boardGameRepository;

    @Mock
    private EventRepository eventRepository;

    @Test
    public void getAllBoardGamesReturnsEmptyListWhenNoBoardGames() {
        when(boardGameRepository.findAll()).thenReturn(List.of());
        List<BoardGame> boardGames = boardGameService.getAll();
        assertEquals(0, boardGames.size());
    }

    @Test
    public void getAllBoardGamesReturnsListOfBoardGames() {
        List<BoardGame> boardGames = List.of(new BoardGame(), new BoardGame());
        when(boardGameRepository.findAll()).thenReturn(boardGames);
        List<BoardGame> result = boardGameService.getAll();
        assertEquals(boardGames, result);
    }

    @Test
    public void getBoardGameByIdReturnsBoardGame() {
        BoardGame boardGame = new BoardGame();
        when(boardGameRepository.getValidBoardGameById(1L)).thenReturn(boardGame);
        BoardGame result = boardGameService.getById(1L);
        assertEquals(boardGame, result);
    }

    @Test
    public void getBoardGameByIdThrowsRuntimeExceptionWhenBoardGameNotFound() {
        when(boardGameRepository.getValidBoardGameById(1L)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> boardGameService.getById(1L));
    }

    @Test
    public void saveBoardGameReturnsBoardGame() {
        Event event = new Event();
        BoardGame boardGame = new BoardGame("barcode", "name", "description", "notes", 10, 5);
        when(eventRepository.getValidEventById(1L)).thenReturn(event);
        when(boardGameRepository.save(any(BoardGame.class))).thenReturn(boardGame);
        BoardGame result = boardGameService.save(new BoardGameRequest(1L, "barcode", "name", "description", "notes", 10, 5));
        assertEquals(boardGame, result);
    }

    @Test
    public void updateBoardGameReturnsBoardGame() {
        Event event = new Event();
        BoardGame boardGame = new BoardGame("barcode", "name", "description", "notes", 10, 5);
        when(eventRepository.getValidEventById(1L)).thenReturn(event);
        when(boardGameRepository.getValidBoardGameById(1L)).thenReturn(boardGame);
        when(boardGameRepository.save(any(BoardGame.class))).thenReturn(boardGame);
        BoardGame result = boardGameService.update(1L, new BoardGameRequest(1L, "barcode", "name", "description", "notes", 10, 5));
        assertEquals(boardGame, result);
    }

    @Test
    public void updateBoardGameThrowsRuntimeExceptionWhenBoardGameNotFound() {
        when(boardGameRepository.getValidBoardGameById(1L)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> boardGameService.update(1L, new BoardGameRequest(1L, "barcode", "name", "description", "notes", 10, 5)));
    }

    @Test
    public void deleteBoardGameThrowsRuntimeExceptionWhenBoardGameNotFound() {
        when(boardGameRepository.existsById(1L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> boardGameService.delete(1L));
    }

    @Test
    public void deleteBoardGameDeletesBoardGame() {
        when(boardGameRepository.existsById(1L)).thenReturn(true);
        boardGameService.delete(1L);
    }
}