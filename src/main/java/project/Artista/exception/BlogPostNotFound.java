package project.Artista.exception;

public class BlogPostNotFound extends RuntimeException{
    public BlogPostNotFound(String message) {
        super(message);
    }
}
