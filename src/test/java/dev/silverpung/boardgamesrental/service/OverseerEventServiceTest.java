package dev.silverpung.boardgamesrental.service;

import dev.silverpung.boardgamesrental.model.Event;
import dev.silverpung.boardgamesrental.model.OverseerEvent;
import dev.silverpung.boardgamesrental.model.request.OverseerEventRequest;
import dev.silverpung.boardgamesrental.model.types.PermissionsType;
import dev.silverpung.boardgamesrental.repository.EventRepository;
import dev.silverpung.boardgamesrental.repository.OverseerEventRepository;
import dev.silverpung.boardgamesrental.repository.OverseerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OverseerEventServiceTest {

    @InjectMocks
    private OverseerEventService overseerEventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private OverseerRepository overseerRepository;

    @Mock
    private OverseerEventRepository overseerEventRepository;

    @Test
    public void save() {
        // given
        OverseerEvent overseerEvent = new OverseerEvent();
        when(overseerEventRepository.save(any(OverseerEvent.class))).thenReturn(overseerEvent);

        // when
        OverseerEvent result = overseerEventService.save(new OverseerEventRequest(1L, 1L, "ADMIN"));

        // then
        assertEquals(overseerEvent, result);
    }

    @Test
    public void delete() {
        // given
        when(overseerEventRepository.existsById(1L)).thenReturn(true);

        // when
        overseerEventService.delete(1L);

        // then
        // no exception thrown
    }

    @Test
    public void delete_notFound() {
        // given
        when(overseerEventRepository.existsById(1L)).thenReturn(false);


        // then
        assertThrows(EntityNotFoundException.class, () -> overseerEventService.delete(1L));
    }

    @Test
    public void update() {
        // given
        OverseerEvent overseerEvent = new OverseerEvent();
        when(overseerEventRepository.getValidOverseerEventById(1L)).thenReturn(overseerEvent);
        when(overseerEventRepository.save(any(OverseerEvent.class))).thenReturn(overseerEvent);

        // when
        OverseerEvent result = overseerEventService.update(1L, new OverseerEventRequest(1L, 1L, "ADMIN"));

        // then
        assertEquals(overseerEvent, result);
    }
    @Test
    public void update_notFound() {
        // given
        when(overseerEventRepository.getValidOverseerEventById(1L)).thenThrow(EntityNotFoundException.class);


        // then
        assertThrows(EntityNotFoundException.class, () -> overseerEventService.update(1L, new OverseerEventRequest(1L, 1L, "ADMIN")));
    }









}
