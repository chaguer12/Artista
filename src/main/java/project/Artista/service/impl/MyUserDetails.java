package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.Artista.exception.EntityNotFound;
import project.Artista.model.user.User;
import project.Artista.model.user.UserPrincipal;
import project.Artista.repository.UserRepo;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {
    private final UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username).orElseThrow(() -> new EntityNotFound("entity not found using this username: " + username));
        return new UserPrincipal(user);
    }
}
