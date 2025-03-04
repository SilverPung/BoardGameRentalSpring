package dev.silverpung.boardgamesrental.service;


import dev.silverpung.boardgamesrental.model.Overseer;
import dev.silverpung.boardgamesrental.repository.OverseerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class OverseerService {

    private final OverseerRepository overseerRepository;

    @Autowired
    public OverseerService(OverseerRepository overseerRepository) {
        this.overseerRepository = overseerRepository;
    }

    public List<Overseer> getAll() {
        return overseerRepository.findAll();

    }

    public Overseer getById(Long id) {
        return overseerRepository.getValidOverseerById(id);
    }

    public Overseer save(Overseer overseer) {
        return overseerRepository.save(overseer);
    }

    public void delete(Long id) {
        if(!overseerRepository.existsById(id)){
            throw new EntityNotFoundException("Overseer on id " + id + " not found");
        }
        overseerRepository.deleteById(id);
    }

    public Overseer update(long id,Overseer overseer) {
        if(!overseerRepository.existsById(id)){
            throw new EntityNotFoundException("Overseer on id " + id + " not found");
        }
        overseer.setId(id);
        return overseerRepository.save(overseer);
    }
}
