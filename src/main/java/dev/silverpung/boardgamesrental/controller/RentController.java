package dev.silverpung.boardgamesrental.controller;


import dev.silverpung.boardgamesrental.model.Rent;
import dev.silverpung.boardgamesrental.model.request.RentRequest;
import dev.silverpung.boardgamesrental.repository.RentRepository;
import dev.silverpung.boardgamesrental.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rent")
public class RentController {


    private final RentService rentService;

    public RentController(RentService rentService){
        this.rentService = rentService;
    }
    @GetMapping("")
    public ResponseEntity<List<Rent>> getAll(){
        return ResponseEntity.ok(rentService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Rent> getById(@PathVariable long id){
        return ResponseEntity.ok(rentService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Rent> save(@RequestBody RentRequest rentRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(rentService.save(rentRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        rentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Rent> update(@PathVariable long id, @RequestBody RentRequest rentRequest){
        return ResponseEntity.ok(rentService.update(id,rentRequest));
    }
}
