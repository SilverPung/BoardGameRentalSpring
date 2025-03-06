package dev.silverpung.boardgamesrental.service;

import dev.silverpung.boardgamesrental.model.BoardGame;
import dev.silverpung.boardgamesrental.model.Rent;
import dev.silverpung.boardgamesrental.model.Renter;
import dev.silverpung.boardgamesrental.model.request.RentRequest;
import dev.silverpung.boardgamesrental.repository.BoardGameRepository;
import dev.silverpung.boardgamesrental.repository.RentRepository;
import dev.silverpung.boardgamesrental.repository.RenterRepository;
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
public class RentServiceTest {

    @InjectMocks
    private RentService rentService;

    @Mock
    private RentRepository rentRepository;

    @Mock
    private BoardGameRepository boardGameRepository;

    @Mock
    private RenterRepository renterRepository;

    @Test
    public void getAllRentsReturnsEmptyListWhenNoRents() {
        when(rentRepository.findAll()).thenReturn(List.of());
        List<Rent> rents = rentService.getAll();
        assertEquals(0, rents.size());
    }

    @Test
    public void getAllRentsReturnsListOfRents() {
        List<Rent> rents = List.of(new Rent(), new Rent());
        when(rentRepository.findAll()).thenReturn(rents);
        List<Rent> result = rentService.getAll();
        assertEquals(rents, result);
    }

    @Test
    public void getRentByIdReturnsRent() {
        Rent rent = new Rent();
        when(rentRepository.getValidRentById(1L)).thenReturn(rent);
        Rent result = rentService.getById(1L);
        assertEquals(rent, result);
    }

    @Test
    public void getRentByIdThrowsRuntimeExceptionWhenRentNotFound() {
        when(rentRepository.getValidRentById(1L)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> rentService.getById(1L));
    }

    @Test
    public void saveRentReturnsRent() {
        BoardGame boardGame = new BoardGame();
        Renter renter = new Renter();
        Rent rent = new Rent();
        when(boardGameRepository.getValidBoardGameById(1L)).thenReturn(boardGame);
        when(renterRepository.getValidRenterById(1L)).thenReturn(renter);
        when(rentRepository.save(any(Rent.class))).thenReturn(rent);
        Rent result = rentService.save(new RentRequest(false, 1L, 1L));
        assertEquals(rent, result);
    }

    @Test
    public void updateRentReturnsRent() {
        BoardGame boardGame = new BoardGame();
        Renter renter = new Renter();
        Rent rent = new Rent();
        when(boardGameRepository.getValidBoardGameById(1L)).thenReturn(boardGame);
        when(renterRepository.getValidRenterById(1L)).thenReturn(renter);
        when(rentRepository.findById(1L)).thenReturn(Optional.of(rent));
        when(rentRepository.save(any(Rent.class))).thenReturn(rent);
        Rent result = rentService.update(1L, new RentRequest(false, 1L, 1L));
        assertEquals(rent, result);
    }

    @Test
    public void updateRentThrowsRuntimeExceptionWhenRentNotFound() {
        when(rentRepository.findById(1L)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> rentService.update(1L, new RentRequest(false, 1L, 1L)));
    }

    @Test
    public void deleteRentThrowsRuntimeExceptionWhenRentNotFound() {
        when(rentRepository.existsById(1L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> rentService.deleteById(1L));
    }

    @Test
    public void deleteRentDeletesRent() {
        when(rentRepository.existsById(1L)).thenReturn(true);
        rentService.deleteById(1L);
    }
}