package project.Artista.controller.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.Artista.exception.EntityNotFound;
import project.Artista.exception.ErrorInfo;
import project.Artista.exception.PasswordDoNotMatch;
import project.Artista.exception.UserAlreadyExists;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> userHandler(UserAlreadyExists userAlreadyExists) {
        ErrorInfo errorResponse = new ErrorInfo(HttpStatus.CONFLICT.value(), userAlreadyExists.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> entityHandler(EntityNotFound entityNotFound) {
        ErrorInfo errorResponse = new ErrorInfo(HttpStatus.NOT_FOUND.value(), entityNotFound.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> passwordHandler(PasswordDoNotMatch passwordDoNotMatch) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.CONFLICT.value(), passwordDoNotMatch.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }
}
