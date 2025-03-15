package project.Artista.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.Artista.exception.TokenExpired;
import project.Artista.mapper.mappers.UserMapper;
import project.Artista.dto.records.user.LogInDTO;
import project.Artista.dto.records.user.SignUpDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.exception.NoUserFound;
import project.Artista.exception.PasswordDoNotMatch;
import project.Artista.exception.UserAlreadyExists;
import project.Artista.model.user.Client;
import project.Artista.model.user.User;
import project.Artista.repository.UserRepo;
import project.Artista.service.AuthServiceInterface;

import java.security.Key;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final MyUserDetails userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String secretKey;
    private final Long jwtExpiryMs = 86400000L;
    @Override
    public UserResDTO signUp(SignUpDTO signUpDTO) {
        validateUser(signUpDTO);
        String encoded_password = passwordEncoder.encode(signUpDTO.password());
        User user = buildUser(signUpDTO,encoded_password);
        userRepo.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserDetails logIn(LogInDTO logInDTO) {
        validateCreds(logInDTO);
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDTO.email(),logInDTO.password()));
//        return userDetailsService.loadUserByUsername(logInDTO.email());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDTO.email(), logInDTO.password()));
        return (UserDetails) authentication.getPrincipal();
    }
    private void validateCreds(LogInDTO logInDTO){
        if(!userRepo.existsByEmail(logInDTO.email())){
                throw new NoUserFound("No user was found with email: " + logInDTO.email());
        }

    }

    private void validateUser(SignUpDTO userDTO){
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


    private User buildUser(SignUpDTO userDTO, String encodedPassword){
        return  Client.builder()
                .fullName(userDTO.fullName())
                .userName(userDTO.userName())
                .email(userDTO.email())
                .password(encodedPassword)
                .build();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",userDetails.getAuthorities().iterator().next().getAuthority());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiryMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public UserDetails validateToken(String token) {
        if(isTokenExpired(token)){
            throw new TokenExpired("Token expired");

        }
        return userDetailsService.loadUserByUsername(extractUsername(token));
    }

    private String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    @Value("${jwt.secret}")
    private String base64Secret;
    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Secret));
    }
    private boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())  // Use the same signing key used for signing the token
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
