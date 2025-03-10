package dev.silverpung.boardgamesrental.repository;


import dev.silverpung.boardgamesrental.model.Event;
import dev.silverpung.boardgamesrental.model.Overseer;
import dev.silverpung.boardgamesrental.model.OverseerEvent;
import dev.silverpung.boardgamesrental.model.types.PermissionsType;
import org.antlr.v4.runtime.NoViableAltException;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OverseerEventRepositoryTest {



    private final OverseerEventRepository overseerEventRepository;
    private final EventRepository eventRepository;
    private final OverseerRepository overseerRepository;


    @Autowired
    public OverseerEventRepositoryTest(OverseerEventRepository overseerEventRepository, EventRepository eventRepository, OverseerRepository overseerRepository) {
        this.overseerEventRepository = overseerEventRepository;
        this.eventRepository = eventRepository;
        this.overseerRepository = overseerRepository;
    }

    private Overseer overseer;
    private Event event;
    private OverseerEvent overseerEvent;

    @BeforeEach
    public void setUp(){
        event = new Event("TestEvent","TestDescription",new Date());
        eventRepository.save(event);

        overseer = new Overseer("Username","Name","Surname","Email","Password");
        overseerRepository.save(overseer);

        overseerEvent = new OverseerEvent(PermissionsType.ADMIN,overseer,event);
        overseerEventRepository.save(overseerEvent);
    }

    @Test
    public void getOverseerPermissionTypeForEventTest(){

        assertEquals(PermissionsType.ADMIN, overseerEventRepository.findRoleByUserAndEventId(overseer, event.getId()));
    }




}
