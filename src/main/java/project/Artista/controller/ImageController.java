package project.Artista.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.model.PhotoAssociation;
import project.Artista.model.enums.PhotoType;
import project.Artista.model.user.User;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class ImageController {
    private final CloudinaryServiceInterface cloudinaryService;



    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file, User user) throws IOException {
        String url = cloudinaryService.uploadImage(file, PhotoType.USER_PROFILE, user.getId());
        return ResponseEntity.ok(url);
    }
    @PostMapping("/associate-image")
    public ResponseEntity<String> associateImage(@RequestParam int photoId, @RequestParam int entityId, @RequestParam PhotoType type) {
        cloudinaryService.associateImage(photoId, entityId, type);
        return ResponseEntity.ok("Photo associated successfully");
    }

    @GetMapping("/studio/{studioId}")
    public ResponseEntity<List<String>> getStudioPhotos(@PathVariable int studioId) {
        //List<PhotoAssociation> associations = photoAssociationRepo.findByTypeAndEntityId(PhotoType.STUDIO, studioId);
        //List<String> photoUrls = associations.stream().map(pa -> pa.getPhoto().getUrl()).toList();
        return null;
    }
}
