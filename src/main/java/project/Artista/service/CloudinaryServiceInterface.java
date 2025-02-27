package project.Artista.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryServiceInterface {
    String uploadImage(MultipartFile file) throws IOException;
}
