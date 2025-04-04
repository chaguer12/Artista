package project.Artista.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.Artista.security.JwtAuthFilter;
import project.Artista.service.AuthServiceInterface;
import project.Artista.service.impl.AuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public JwtAuthFilter jwtAuthFilter(AuthService authService) {
        return new JwtAuthFilter(authService);
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthFilter jwtAuthFilter) throws Exception {
        http

                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/log-in").permitAll()
                        .requestMatchers(HttpMethod.POST,"/provider").permitAll()
                        .requestMatchers(HttpMethod.POST,"/upload/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/admin","blog/add").permitAll()
                        .requestMatchers(HttpMethod.PATCH,"/admin/admin-upload","/user/user-upload","/provider/provider-upload","/equipment/equipment-upload").permitAll()
                        .requestMatchers(HttpMethod.GET,"/provider/get").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/studio/**","/equipment/**").hasRole("PROVIDER")
                        .requestMatchers("/auth/profile","/studio/by-provider/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/equipment/**","/studio/**","/studio/**").hasRole("PROVIDER")
                        .requestMatchers(HttpMethod.DELETE,"/equipment/**","/studio/**","/equipment/**").hasRole("PROVIDER")
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200", "http://127.0.0.1:4200")
                        .allowedMethods("GET", "POST", "PATCH   ", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }


}
