package project.Artista.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService implements CloudinaryServiceInterface {
    private final Cloudinary cloudinary;
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id",file.getOriginalFilename()));
        return (String) result.get("secure_url");
    }
}
