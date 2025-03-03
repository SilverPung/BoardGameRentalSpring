package dev.silverpung.boardgamesrental.service;


import dev.silverpung.boardgamesrental.model.Renter;
import dev.silverpung.boardgamesrental.model.request.RenterRequest;
import dev.silverpung.boardgamesrental.repository.EventRepository;
import dev.silverpung.boardgamesrental.repository.RenterRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenterService {

    private final RenterRepository renterRepository;

    private final EventRepository eventRepository;

    @Autowired
    public RenterService(RenterRepository renterRepository, EventRepository eventRepository) {
        this.renterRepository = renterRepository;
        this.eventRepository = eventRepository;
    }


    public List<Renter> getAll() {
        return renterRepository.findAll();
    }

    public Renter getById(Long id) {
        return renterRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Renter with id " + id + " not found")
        );
    }

    public Renter save(RenterRequest renterRequest) {
        Renter renter = new Renter();
        renter.setEvent(eventRepository.findById(renterRequest.getEventId()).orElseThrow(
                () -> new RuntimeException("Event with id " + renterRequest.getEventId() + " not found")
        ));
        renter.setData(renterRequest);
        return renterRepository.save(renter);
    }

    public void delete(Long id) {
        if(!renterRepository.existsById(id)){
            throw new RuntimeException("Renter with id " + id + " not found");
        }
        renterRepository.deleteById(id);
    }

    public Renter update(Long id, RenterRequest renterRequest) {
        Renter renter = renterRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Renter with id " + id + " not found")
        );
        renter.setEvent(eventRepository.findById(renterRequest.getEventId()).orElseThrow(
                () -> new RuntimeException("Event with id " + renterRequest.getEventId() + " not found")
        ));
        renter.setData(renterRequest);
        return renterRepository.save(renter);
    }
}
