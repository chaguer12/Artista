package project.Artista.exception.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.Artista.exception.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> userHandler(UserAlreadyExists userAlreadyExists) {
        ErrorInfo errorResponse = new ErrorInfo(HttpStatus.CONFLICT.value(), userAlreadyExists.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<ErrorInfo>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> entityHandler(EntityNotFound entityNotFound) {
        ErrorInfo errorResponse = new ErrorInfo(HttpStatus.NOT_FOUND.value(), entityNotFound.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<ErrorInfo>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> passwordHandler(PasswordDoNotMatch passwordDoNotMatch) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.CONFLICT.value(), passwordDoNotMatch.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> NoUserHandler(NoUserFound noUserFound) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), noUserFound.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> BlogPostHandler(BlogPostNotFound blogPostNotFound) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), blogPostNotFound.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
