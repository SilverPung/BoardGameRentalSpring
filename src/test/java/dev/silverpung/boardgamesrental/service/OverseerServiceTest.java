package dev.silverpung.boardgamesrental.service;


import dev.silverpung.boardgamesrental.model.Overseer;
import dev.silverpung.boardgamesrental.repository.OverseerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OverseerServiceTest {

    @Mock
    private OverseerRepository overseerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private OverseerService overseerService;

    private final PasswordEncoder realPasswordEncoder = new BCryptPasswordEncoder();


    @Test
    public void GetAllGetAllOverseersTest() {
        //given
        when(overseerRepository.findAll()).thenReturn(List.of(new Overseer(), new Overseer()));


        //when
        List<Overseer> overseers = overseerService.getAll();

        //then
        assertEquals(2, overseers.size());

    }

    @Test
    public void GetOverseerByIdTest() {
        when(overseerRepository.getValidOverseerById(1L)).thenReturn(new Overseer());

        Overseer overseer = overseerService.getById(1L);

        assertEquals(Overseer.class, overseer.getClass());
    }



    @Test
    public void AddOverseerTest() {
        Overseer overseer = new Overseer("user","name","surname","email.com","password");
        when(passwordEncoder.encode(overseer.getPassword())).thenReturn("password");
        when(overseerRepository.save(overseer)).thenReturn(overseer);

        Overseer savedOverseer = overseerService.save(overseer);

        assertEquals(overseer, savedOverseer);
    }

    @Test
    public void AddOverseerWithPasswordTest() {
        Overseer overseer = new Overseer("user","name","surname","email.com","password");

        when(overseerRepository.save(overseer)).thenReturn(overseer);
        when(passwordEncoder.encode(overseer.getPassword())).thenReturn(realPasswordEncoder.encode(overseer.getPassword()));

        Overseer savedOverseer = overseerService.save(overseer);

        assertEquals(overseer, savedOverseer);
        assertTrue(realPasswordEncoder.matches("password", savedOverseer.getPassword()));
    }




    @Test
    public void DeleteOverseerTest() {
        when(overseerRepository.existsById(1L)).thenReturn(true);

        overseerService.delete(1L);
    }

    @Test
    public void DeleteOverseerThrowsExceptionTest() {
        when(overseerRepository.existsById(1L)).thenReturn(false);

        try {
            overseerService.delete(1L);
        } catch (EntityNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    public void UpdateOverseerTest() {
        Overseer overseer = new Overseer("user","name","surname","email.com","password");
        when(overseerRepository.getValidOverseerById(1L)).thenReturn(overseer);
        when(overseerRepository.save(overseer)).thenReturn(overseer);

        Overseer updatedOverseer = overseerService.update(1L, overseer);

        assertEquals(overseer, updatedOverseer);

    }

    @Test
    public void UpdateOverseerWithPasswordTest() {
        Overseer overseer = new Overseer("user","name","surname","email.com","password");
        when(overseerRepository.getValidOverseerById(1L)).thenReturn(overseer);
        when(overseerRepository.save(overseer)).thenReturn(overseer);
        when(passwordEncoder.encode(overseer.getPassword())).thenReturn(realPasswordEncoder.encode(overseer.getPassword()));

        Overseer updatedOverseer = overseerService.update(1L, overseer);

        assertEquals(overseer, updatedOverseer);
        assertTrue(realPasswordEncoder.matches("password", updatedOverseer.getPassword()));
    }

    @Test
    public void UpdateOverseerWithoutPasswordTest() {
        Overseer overseer = new Overseer("user","name","surname","email.com",null);
        when(overseerRepository.getValidOverseerById(1L)).thenReturn(overseer);
        when(overseerRepository.save(overseer)).thenReturn(overseer);

        Overseer updatedOverseer = overseerService.update(1L, overseer);

        assertEquals(overseer, updatedOverseer);
    }



}
