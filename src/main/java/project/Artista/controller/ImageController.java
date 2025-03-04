package project.Artista.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class ImageController {
    private final CloudinaryServiceInterface cloudinaryService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        String url = cloudinaryService.uploadImage(file);
        return ResponseEntity.ok(url);
    }
}
