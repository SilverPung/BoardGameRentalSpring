package dev.silverpung.boardgamesrental.service;


import dev.silverpung.boardgamesrental.model.OverseerEvent;
import dev.silverpung.boardgamesrental.model.request.OverseerEventRequest;
import dev.silverpung.boardgamesrental.model.types.PermissionsType;
import dev.silverpung.boardgamesrental.repository.EventRepository;
import dev.silverpung.boardgamesrental.repository.OverseerEventRepository;
import dev.silverpung.boardgamesrental.repository.OverseerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverseerEventService {

    private final OverseerRepository overseerRepository;
    private final EventRepository eventRepository;
    private final OverseerEventRepository overseerEventRepository;

    @Autowired
    public OverseerEventService(OverseerRepository overseerRepository, EventRepository eventRepository, OverseerEventRepository overseerEventRepository) {
        this.overseerRepository = overseerRepository;
        this.eventRepository = eventRepository;
        this.overseerEventRepository = overseerEventRepository;
    }

    public OverseerEvent save(OverseerEventRequest overseerEventRequest) {
        OverseerEvent overseerEvent = new OverseerEvent();
        return saveOverseerEvent(overseerEventRequest, overseerEvent);
    }

    public void delete(Long id){
        if(!overseerEventRepository.existsById(id)){
            throw new EntityNotFoundException("OverseerEvent on id " + id + " not found");
        }
        overseerEventRepository.deleteById(id);
    }

    public OverseerEvent update(Long id, OverseerEventRequest overseerEventRequest){
        OverseerEvent overseerEvent = overseerEventRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("OverseerEvent on id " + id + " not found")
        );
        return saveOverseerEvent(overseerEventRequest, overseerEvent);
    }

    private OverseerEvent saveOverseerEvent(OverseerEventRequest overseerEventRequest, OverseerEvent overseerEvent) {
        overseerEvent.setOverseer(overseerRepository.findById(overseerEventRequest.getOverseerId()).orElseThrow(
                () -> new EntityNotFoundException("Overseer on id " + overseerEventRequest.getOverseerId() + " not found")
        ));
        overseerEvent.setEvent(eventRepository.findById(overseerEventRequest.getEventId()).orElseThrow(
                () -> new EntityNotFoundException("Event on id " + overseerEventRequest.getEventId() + " not found")
        ));
        overseerEvent.setPermissionsType(
                PermissionsType.valueOf(overseerEventRequest.getPermissionsType())
        );
        return overseerEventRepository.save(overseerEvent);
    }
}
