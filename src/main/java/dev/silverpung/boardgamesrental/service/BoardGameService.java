package dev.silverpung.boardgamesrental.service;


import dev.silverpung.boardgamesrental.model.BoardGame;
import dev.silverpung.boardgamesrental.model.request.BoardGameRequest;
import dev.silverpung.boardgamesrental.repository.BoardGameRepository;
import dev.silverpung.boardgamesrental.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardGameService {

    private final BoardGameRepository boardGameRepository;

    private final EventRepository eventRepository;

    @Autowired
    public BoardGameService(BoardGameRepository boardGameRepository, EventRepository eventRepository) {
        this.boardGameRepository = boardGameRepository;
        this.eventRepository = eventRepository;
    }

    public List<BoardGame> getAll() {
        return boardGameRepository.findAll();
    }

    public BoardGame getById(Long id) {
        return boardGameRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("BoardGame on id " + id + " not found"));
    }



    public void delete(Long id) {
        if(!boardGameRepository.existsById(id)){
            throw new EntityNotFoundException("BoardGame on id " + id + " not found");
        }
        boardGameRepository.deleteById(id);
    }


    public BoardGame save(BoardGameRequest boardGameRequest) {
        BoardGame boardGame = new BoardGame();
        boardGame.setEvent(eventRepository.findById(boardGameRequest.getEventId()).orElseThrow(
                () -> new EntityNotFoundException("Event with id " + boardGameRequest.getEventId() + " not found")));

        boardGame.setData(boardGameRequest);


        return boardGameRepository.save(boardGame);
    }

    public BoardGame update(Long id, BoardGameRequest boardGameRequest) {
        BoardGame boardGame = boardGameRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("BoardGame on id " + id + " not found"));
        boardGame.setEvent(eventRepository.findById(boardGameRequest.getEventId()).orElseThrow(
                () -> new EntityNotFoundException("Event with id " + boardGameRequest.getEventId() + " not found")));
        boardGame.setData(boardGameRequest);
        return boardGameRepository.save(boardGame);
    }

}

