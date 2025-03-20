    package project.Artista.service.impl;

    import  lombok.RequiredArgsConstructor;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;
    import project.Artista.dto.records.user.UserReqDTO;
    import project.Artista.dto.records.user.UserResDTO;
    import project.Artista.dto.records.user.UserUpdateDTO;
    import project.Artista.exception.EntityNotFound;
    import project.Artista.exception.PasswordDoNotMatch;
    import project.Artista.exception.UserAlreadyExists;
    import project.Artista.mapper.mappers.ProviderMapper;
    import project.Artista.model.enums.PhotoType;
    import project.Artista.model.enums.Role;
    import project.Artista.model.user.Provider;
    import project.Artista.repository.ProviderRepo;
    import project.Artista.repository.UserRepo;
    import project.Artista.service.CloudinaryServiceInterface;
    import project.Artista.service.ProviderServiceInterface;

    import java.io.IOException;
    import java.util.List;
    import java.util.Objects;

    @Service
    @RequiredArgsConstructor
    public class ProviderService implements ProviderServiceInterface {
        private final PasswordEncoder passwordEncoder;
        private final ProviderMapper providerMapper;
        private final UserRepo userRepo;
        private final ProviderRepo providerRepo;
        private final CloudinaryServiceInterface cloudinaryService;
        @Override
        public UserResDTO saveUser(UserReqDTO userDTO) {
            validateUser(userDTO);
            String encodedPass = passwordEncoder.encode(userDTO.password());
            Provider provider  = buildUser(userDTO, encodedPass);
            userRepo.save(provider);
            return providerMapper.toDTO(provider);
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
        private Provider buildUser(UserReqDTO userDTO, String encodedPassword) {
            return  Provider.builder()
                    .fullName(userDTO.fullName())
                    .userName(userDTO.userName())
                    .email(userDTO.email())
                    .password(encodedPassword)
                    .role(Role.ROLE_PROVIDER)
                    .build();
        }

        @Override
        public UserResDTO updateUser(int id, UserUpdateDTO updateDTO) {
            Provider user = providerRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
            updateDTO.fullName().ifPresent(user::setFullName);
            updateDTO.userName().ifPresent(user::setUserName);
            updateDTO.email().ifPresent(user::setEmail);
            updateDTO.password().ifPresent(user::setPassword);
            updateDTO.profilePic().ifPresent(user::setProfilePic);
            updateDTO.password().ifPresent(user::setPassword);
            providerRepo.save(user);
            return providerMapper.toDTO(user);
        }

        @Override
        public boolean deleteUser(int id) {
            if(providerRepo.existsById(id)){
                providerRepo.deleteById(id);
                return true;
            }
            return false;
        }

        @Override
        public UserResDTO getUser(int id) {
            Provider provider = providerRepo.findById(id).orElseThrow(()-> new EntityNotFound("No entity found using id: " + id));
            return providerMapper.toDTO(provider);
        }

        @Override
        public List<UserResDTO> getAllUsers() {
            List<Provider> providers = providerRepo.findAll();
            return providers.stream().map(providerMapper::toDTO).toList();
        }

        @Override
        public String uploadProfilePic(String email, MultipartFile file) throws IOException {
            Provider provider = providerRepo.findByEmail(email).orElseThrow(() -> new EntityNotFound("Admin not found with email: " + email));
            String photo = cloudinaryService.uploadImage(file, PhotoType.USER_PROFILE,provider.getId());
            provider.setProfilePic(photo);
            providerRepo.save(provider);
            return photo;
        }
    }
