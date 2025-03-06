package dev.silverpung.boardgamesrental.exception;

public class UniqueForeignKeyException extends RuntimeException {
    public UniqueForeignKeyException(String message) {
        super(message);
    }
}
