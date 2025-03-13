package project.Artista.service;

import org.springframework.web.multipart.MultipartFile;
import project.Artista.model.enums.PhotoType;

import java.io.IOException;

public interface CloudinaryServiceInterface {
    String uploadImage(MultipartFile file, PhotoType type) throws IOException;
}
