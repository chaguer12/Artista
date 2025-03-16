package project.Artista.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import project.Artista.model.user.UserPrincipal;
import project.Artista.service.AuthServiceInterface;

import java.io.IOException;
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthServiceInterface authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        try {
            String token = extractToken(request);

            // ✅ Token must not be null before processing
            if (token != null) {
                String userName = authService.extractUsername(token);

                // ✅ Validate token and get UserDetails
                UserDetails userDetails = authService.validateToken(token);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    if (userDetails instanceof UserPrincipal) {
                        request.setAttribute("userId", ((UserPrincipal) userDetails).getId());
                    }
                } else {
                    log.warn("🔴 Invalid JWT token for user: {}", userName);
                }
            }
        } catch (Exception e) {
            log.warn("🔴 Error processing JWT token: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
