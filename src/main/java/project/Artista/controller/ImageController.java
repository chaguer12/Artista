package project.Artista.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.model.PhotoAssociation;
import project.Artista.model.enums.PhotoType;
import project.Artista.repository.PhotoAssociationRepo;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class ImageController {
    private final CloudinaryServiceInterface cloudinaryService;
    private final PhotoAssociationRepo photoAssociationRepo;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file, @RequestParam("type")PhotoType type,@RequestParam("entityId") int entityId) throws IOException {
        String url = cloudinaryService.uploadImage(file, type, entityId);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/studio/{studioId}")
    public ResponseEntity<List<String>> getStudioPhotos(@PathVariable int studioId) {
        List<PhotoAssociation> associations = photoAssociationRepo.findByTypeAndEntityId(PhotoType.STUDIO, studioId);
        List<String> photoUrls = associations.stream().map(pa -> pa.getPhoto().getUrl()).toList();
        return ResponseEntity.ok(photoUrls);
    }
}
