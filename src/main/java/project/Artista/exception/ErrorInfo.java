package project.Artista.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ErrorInfo {
    private int status;
    private String message;
    private long timestamp;

    public ErrorInfo(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
