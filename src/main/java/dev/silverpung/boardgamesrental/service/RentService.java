package dev.silverpung.boardgamesrental.service;


import dev.silverpung.boardgamesrental.model.Rent;
import dev.silverpung.boardgamesrental.model.request.RentRequest;
import dev.silverpung.boardgamesrental.repository.BoardGameRepository;
import dev.silverpung.boardgamesrental.repository.RentRepository;
import dev.silverpung.boardgamesrental.repository.RenterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final BoardGameRepository boardGameRepository;
    private final RenterRepository renterRepository;

    @Autowired
    public RentService(RentRepository rentRepository, BoardGameRepository boardGameRepository, RenterRepository renterRepository){
        this.rentRepository = rentRepository;
        this.boardGameRepository = boardGameRepository;
        this.renterRepository = renterRepository;
    }

    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    public Rent getById(long id) {
        return rentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Rent with Id " + id + " not found"));
    }

    public Rent save(RentRequest rentRequest) {
        Rent rent = new Rent();
        return saveRent(rentRequest, rent);

    }

    private Rent saveRent(RentRequest rentRequest, Rent rent) {
        rent.setData(rentRequest);
        rent.setBoardGame(
                boardGameRepository.findById(rentRequest.getBoardgameId()).
                    orElseThrow(
                        ()-> new EntityNotFoundException("BoardGame on id " + rentRequest.getBoardgameId() + " not found")
                ));

        rent.setRenter(
                renterRepository.findById(rentRequest.getRenterId()).
                        orElseThrow(
                                () -> new EntityNotFoundException("Renter with id " + rentRequest.getRenterId() + " not found")
                        )

        );

        return rentRepository.save(rent);
    }

    public void deleteById(long id) {
        if(!rentRepository.existsById(id)){
            throw  new EntityNotFoundException("Rent with Id " + id + " not found");
        }
        rentRepository.deleteById(id);
    }

    public Rent update(long id, RentRequest rentRequest) {
        Rent rent = rentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Rent with Id " + id + " not found")

        );
        return saveRent(rentRequest, rent);

    }
}
