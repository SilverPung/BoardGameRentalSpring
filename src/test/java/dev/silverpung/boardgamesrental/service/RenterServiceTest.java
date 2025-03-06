package dev.silverpung.boardgamesrental.service;


import dev.silverpung.boardgamesrental.model.Event;
import dev.silverpung.boardgamesrental.model.Renter;
import dev.silverpung.boardgamesrental.model.request.RenterRequest;
import dev.silverpung.boardgamesrental.repository.EventRepository;
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
public class RenterServiceTest {

    @InjectMocks
    private RenterService renterService;

    @Mock
    private RenterRepository renterRepository;

    @Mock
    private EventRepository eventRepository;

    @Test
    public void getAllRentersReturnsEmptyListWhenNoRenters() {
        // given
        when(renterRepository.findAll()).thenReturn(List.of());

        // when
        List<Renter> renters = renterService.getAll();

        // then
        assertEquals(0, renters.size());
    }

    @Test
    public void getAllRentersReturnsListOfRenters() {
        // given
        List<Renter> renters = List.of(new Renter(), new Renter());
        when(renterRepository.findAll()).thenReturn(renters);

        // when
        List<Renter> result = renterService.getAll();

        // then
        assertEquals(renters, result);
    }

    @Test
    public void getRenterByIdReturnsRenter() {
        // given
        Renter renter = new Renter();
        when(renterRepository.getValidRenterById(1L)).thenReturn(renter);

        // when
        Renter result = renterService.getById(1L);

        // then
        assertEquals(renter, result);
    }

    @Test
    public void getRenterByIdThrowsRuntimeExceptionWhenRenterNotFound() {
        // given
        when(renterRepository.getValidRenterById(1L)).thenThrow(new EntityNotFoundException());

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> renterService.getById(1L));
    }

    @Test
    public void saveRenterReturnsRenter() {
        // given
        Event event = new Event();
        Renter renter = new Renter("barcode", "userName");
        when(eventRepository.getValidEventById(1L)).thenReturn(event);
        when(renterRepository.save(any(Renter.class))).thenReturn(renter);

        // when
        Renter result = renterService.save(new RenterRequest("barcode", "userName", 1L));

        // then
        assertEquals(renter, result);
    }
    @Test
    public void updateRenterReturnsRenter() {
        // given
        Event event = new Event();
        Renter renter = new Renter("barcode", "userName");
        when(eventRepository.getValidEventById(1L)).thenReturn(event);
        when(renterRepository.getValidRenterById(1L)).thenReturn(renter);
        when(renterRepository.save(any(Renter.class))).thenReturn(renter);

        // when
        Renter result = renterService.update(1L, new RenterRequest("barcode", "userName", 1L));

        // then
        assertEquals(renter, result);
    }
    @Test
    public void updateRenterThrowsRuntimeExceptionWhenRenterNotFound() {
        // given
        when(renterRepository.getValidRenterById(1L)).thenThrow(new EntityNotFoundException());

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> renterService.update(1L, new RenterRequest("barcode", "userName", 1L)));
    }

    @Test
    public void deleteRenterThrowsRuntimeExceptionWhenRenterNotFound() {
        // given
        when(renterRepository.existsById(1L)).thenReturn(false);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> renterService.delete(1L));
    }
    @Test
    public void deleteRenterDeletesRenter() {
        // given
        when(renterRepository.existsById(1L)).thenReturn(true);

        // when
        renterService.delete(1L);

        // then
    }



}
