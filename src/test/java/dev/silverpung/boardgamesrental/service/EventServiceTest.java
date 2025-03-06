package dev.silverpung.boardgamesrental.service;


import dev.silverpung.boardgamesrental.model.Event;
import dev.silverpung.boardgamesrental.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;


    @Test
    public void findAllReturnsEmptyListWhenNoEvents() {
        // given
        when(eventRepository.findAll()).thenReturn(List.of());

        // when
        List<Event> events = eventService.getAll();

        // then
        assertEquals(0, events.size());
    }

    @Test
    public void findAllReturnsListOfEvents() {
        // given
        List<Event> events = List.of(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(events);

        // when
        List<Event> result = eventService.getAll();

        // then
        assertEquals(events, result);
    }

    @Test
    public void findByIdReturnsEvent() {
        // given
        Event event = new Event("name", "description", new Date());
        when(eventRepository.getValidEventById(1L)).thenReturn(event);

        // when
        Event result = eventService.getById(1L);

        // then
        assertEquals(event, result);
    }

    @Test
    public void findByIdThrowsExceptionWhenEventDoesNotExist() {
        // given
        when(eventRepository.getValidEventById(1L)).thenThrow(EntityNotFoundException.class);

        // when
        // then

        assertThrows(EntityNotFoundException.class, () -> eventService.getById(1L));

    }

    @Test
    public void saveReturnsEvent() {
        // given
        Event event = new Event();
        when(eventRepository.save(event)).thenReturn(event);

        // when
        Event result = eventService.save(event);

        // then
        assertEquals(event, result);
    }

    @Test
    public void deleteByIdDeletesEvent() {
        // given
        when(eventRepository.existsById(1L)).thenReturn(true);

        // when
        eventService.delete(1L);

        // then
        verify(eventRepository).deleteById(1L);
    }

    @Test
    public void deleteByIdThrowsExceptionWhenEventDoesNotExist() {
        // given
        when(eventRepository.existsById(1L)).thenReturn(false);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> eventService.delete(1L));
    }

    @Test
    public void updateReturnsUpdatedEvent() {
        // given
        Event event = new Event();
        when(eventRepository.existsById(1L)).thenReturn(true);
        when(eventRepository.save(event)).thenReturn(event);

        // when
        Event result = eventService.update(1L, event);

        // then
        assertEquals(event, result);
    }

    @Test
    public void updateThrowsExceptionWhenEventDoesNotExist() {
        // given
        Event event = new Event();
        when(eventRepository.existsById(1L)).thenReturn(false);

        // when
        // then
        assertThrows(EntityNotFoundException.class, () -> eventService.update(1L, event));
    }





}
