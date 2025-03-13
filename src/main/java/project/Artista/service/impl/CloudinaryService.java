package project.Artista.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
    public String uploadImage(MultipartFile file, PhotoType type) throws IOException {
        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id",file.getOriginalFilename()));
        String imageUrl =  (String) result.get("secure_url");
        Photo image = Photo.builder()
                .url(imageUrl)
                .description(file.getOriginalFilename())
                .type(type)
                .build();
        photoRepo.save(image);
        return imageUrl;
    }
}
