package dev.silverpung.boardgamesrental.controller;


import dev.silverpung.boardgamesrental.model.OverseerEvent;
import dev.silverpung.boardgamesrental.model.request.OverseerEventRequest;
import dev.silverpung.boardgamesrental.service.OverseerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/overseer/event")
public class OverseerEventController {


    private final OverseerEventService overseerEventService;

    @Autowired
    public OverseerEventController(OverseerEventService overseerEventService) {
        this.overseerEventService = overseerEventService;
    }

    @PostMapping
    public ResponseEntity<OverseerEvent> save(@RequestBody OverseerEventRequest overseerEventRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(overseerEventService.save(overseerEventRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<OverseerEvent> update(@PathVariable Long id, @RequestBody OverseerEventRequest overseerEventRequest){
        return ResponseEntity.ok(overseerEventService.update(id, overseerEventRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        overseerEventService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
