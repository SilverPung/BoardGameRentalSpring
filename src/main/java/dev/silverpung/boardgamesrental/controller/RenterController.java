package dev.silverpung.boardgamesrental.controller;


import dev.silverpung.boardgamesrental.model.Renter;
import dev.silverpung.boardgamesrental.model.request.RenterRequest;
import dev.silverpung.boardgamesrental.service.RenterService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/renter")
public class RenterController {

    private final RenterService renterService;

    @Autowired
    public RenterController(RenterService renterService) {
        this.renterService = renterService;
    }

    @GetMapping("")
    public ResponseEntity<List<Renter>> getAll() {
        return ResponseEntity.ok(renterService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Renter> getById(@PathVariable Long id) {
        return ResponseEntity.ok(renterService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Renter> save(@RequestBody RenterRequest renterRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(renterService.save(renterRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        renterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Renter> update(@PathVariable Long id, @RequestBody RenterRequest renterRequest){
        return ResponseEntity.ok(renterService.update(id, renterRequest));
    }
}
