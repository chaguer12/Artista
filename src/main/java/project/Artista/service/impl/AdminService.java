package project.Artista.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.exception.EntityNotFound;
import project.Artista.exception.PasswordDoNotMatch;
import project.Artista.exception.UserAlreadyExists;
import project.Artista.mapper.mappers.AdminMapper;
import project.Artista.model.Photo;
import project.Artista.model.enums.PhotoType;
import project.Artista.model.enums.Role;
import project.Artista.model.user.Admin;
import project.Artista.repository.AdminRepo;
import project.Artista.repository.UserRepo;
import project.Artista.service.AdminServiceInterface;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceInterface {

    private final AdminRepo adminRepo;
    private final UserRepo userRepo;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryServiceInterface cloudinaryService;
    @Override
    public UserResDTO saveUser(UserReqDTO user) throws IOException {


        validateUser(user);
        String encodedPass = passwordEncoder.encode(user.confirmPassword());
        Admin admin = buildUser(user, encodedPass);
        adminRepo.save(admin);
        return adminMapper.toDTO(admin);

    }
    private void validateUser(UserReqDTO userDTO) {
        //for validation mzyana
        if (!Objects.equals(userDTO.confirmPassword(), userDTO.password())) {
            throw new PasswordDoNotMatch("Password and confirmation password do not match.");
        }
        if(userRepo.existsByUserName(userDTO.userName())){
            throw new UserAlreadyExists("User already exists with username: " + userDTO.userName());
        }
        if(userRepo.existsByEmail(userDTO.email())){
            throw new UserAlreadyExists("User already exists with email: " + userDTO.email());
        }
    }
    private Admin buildUser(UserReqDTO userDTO, String encodedPassword ){
        return  Admin.builder()
                .fullName(userDTO.fullName())
                .userName(userDTO.userName())
                .email(userDTO.email())
                .password(encodedPassword)
                .role(Role.ROLE_ADMIN)
                .build();
    }

    @Override
    public UserResDTO updateUser(int id, UserUpdateDTO updateDTO) {
        Admin user = adminRepo.findById(id).orElseThrow(() -> new EntityNotFound("Admin not found with id: " + id));
        updateDTO.fullName().ifPresent(user::setFullName);
        updateDTO.userName().ifPresent(user::setUserName);
        updateDTO.email().ifPresent(user::setEmail);
        updateDTO.password().ifPresent(user::setPassword);
        updateDTO.profilePic().ifPresent(user::setProfilePic);
        updateDTO.password().ifPresent(user::setPassword);
        adminRepo.save(user);
        return adminMapper.toDTO(user);
    }

    @Override
    public boolean deleteUser(int id) {
        if(adminRepo.existsById(id)) {
            adminRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserResDTO getUser(int id) {
       Admin admin = adminRepo.findById(id).orElseThrow(() -> new EntityNotFound("Admin not found with id: " + id));
       return adminMapper.toDTO(admin);
    }

    @Override
    public List<UserResDTO> getAllUsers() {
        List<Admin> admins = adminRepo.findAll();
        return admins.stream().map(adminMapper::toDTO).toList();
    }
    @Transactional
    @Override
    public String uploadProfilePic(String email, MultipartFile file) throws IOException {
        Admin admin = adminRepo.findByEmail(email).orElseThrow(() -> new EntityNotFound("Admin not found with email: " + email));
        String photo = cloudinaryService.uploadImage(file, PhotoType.USER_PROFILE);
        admin.setProfilePic(photo);
        adminRepo.save(admin);
        return photo;
    }
}
