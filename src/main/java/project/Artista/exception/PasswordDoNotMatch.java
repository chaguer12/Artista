package project.Artista.exception;

public class PasswordDoNotMatch extends RuntimeException{
    public PasswordDoNotMatch(String message) {
        super(message);

    }
}
