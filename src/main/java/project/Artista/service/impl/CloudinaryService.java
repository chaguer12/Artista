package project.Artista.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.model.Photo;
import project.Artista.model.PhotoAssociation;
import project.Artista.model.enums.PhotoType;
import project.Artista.repository.PhotoAssociationRepo;
import project.Artista.repository.PhotoRepo;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService implements CloudinaryServiceInterface {
    private final Cloudinary cloudinary;
    private final PhotoRepo photoRepo;
    private final PhotoAssociationRepo photoAssociationRepo;

    @Override
    public String uploadImage(MultipartFile file, PhotoType type,Integer entitytId) throws IOException {
        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id",file.getOriginalFilename()));
        String imageUrl =  (String) result.get("secure_url");
        Photo image = Photo.builder()
                .url(imageUrl)
                .description(file.getOriginalFilename())
                .type(type)
                .build();
        photoRepo.save(image);
        PhotoAssociation association = PhotoAssociation.builder()
                .photo(image)
                .type(type)
                .entityId(entitytId)
                .build();
        photoAssociationRepo.save(association);

        return imageUrl;
    }
    @Override
    public void associateImage(int photoId, int entityId, PhotoType type) {
        Photo photo = photoRepo.findById(photoId)
                .orElseThrow(() -> new EntityNotFoundException("Photo not found"));

        // Create a new PhotoAssociation object
        PhotoAssociation association = PhotoAssociation.builder()
                .photo(photo)  // Associate the photo
                .type(type)     // Associate the photo type (e.g., USER_PROFILE, STUDIO, etc.)
                .entityId(entityId)  // Associate it with the entity (e.g., a user or a studio)
                .build();

        // Save the association to the database
        photoAssociationRepo.save(association);
    }
}
