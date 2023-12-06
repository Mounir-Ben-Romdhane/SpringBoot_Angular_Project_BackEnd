package tn.esprit.spring.Config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;
import static tn.esprit.spring.DAO.Entities.Permission.*;
import static tn.esprit.spring.DAO.Entities.Role.ADMIN;
import static tn.esprit.spring.DAO.Entities.Role.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/etudiant/**")
                        .permitAll()
                        /*.requestMatchers("/bloc/**").hasRole(ADMIN.name())
                        .requestMatchers(GET, "/bloc/**").hasAnyAuthority(ADMIN_READ.name() , USER_READ.name())
                        .requestMatchers(POST, "/bloc/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/bloc/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/bloc/**").hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/chambre/**").hasRole(ADMIN.name())
                        .requestMatchers(GET, "/chambre/**").hasAnyAuthority(ADMIN_READ.name() , USER_READ.name())
                        .requestMatchers(POST, "/chambre/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/chambre/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/chambre/**").hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/foyer/**").hasRole(ADMIN.name())
                        .requestMatchers(GET, "/foyer/**").hasAnyAuthority(ADMIN_READ.name() , USER_READ.name())
                        .requestMatchers(POST, "/foyer/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/foyer/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/foyer/**").hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/universite/**").hasRole(ADMIN.name())
                        .requestMatchers(GET, "/universite/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                        .requestMatchers(POST, "/universite/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/universite/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/universite/**").hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/reservation/**").hasAnyRole(ADMIN.name() , USER.name())
                        .requestMatchers(GET, "/reservation/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                        .requestMatchers(POST, "/reservation/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
                        .requestMatchers(PUT, "/reservation/**").hasAnyAuthority(ADMIN_UPDATE.name(), USER_UPDATE.name())
                        .requestMatchers(DELETE, "/reservation/**").hasAnyAuthority(ADMIN_DELETE.name(), USER_DELETE.name())*/

                        .anyRequest()
                        .permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/etudiant/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) ->
                                        SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}
