package project.Artista.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.model.PhotoAssociation;
import project.Artista.model.enums.PhotoType;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class ImageController {
    private final CloudinaryServiceInterface cloudinaryService;


    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        String url = cloudinaryService.uploadImage(file, PhotoType.USER_PROFILE);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/studio/{studioId}")
    public ResponseEntity<List<String>> getStudioPhotos(@PathVariable int studioId) {

        return null;
    }
}
