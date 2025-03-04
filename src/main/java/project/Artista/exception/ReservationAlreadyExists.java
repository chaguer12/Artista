package project.Artista.exception;

public class ReservationAlreadyExists extends RuntimeException{
    public ReservationAlreadyExists(String message) {
        super(message);

    }
}
