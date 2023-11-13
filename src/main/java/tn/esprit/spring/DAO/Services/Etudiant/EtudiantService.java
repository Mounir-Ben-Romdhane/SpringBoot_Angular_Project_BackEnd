package tn.esprit.spring.DAO.Services.Etudiant;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Dto.AuthenticationRequest;
import tn.esprit.spring.DAO.Dto.AuthenticationResponse;
import tn.esprit.spring.DAO.Dto.RegisterRequest;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Entities.Token;
import tn.esprit.spring.DAO.Entities.TokenType;
import tn.esprit.spring.DAO.Repositories.EtudiantRepository;
import tn.esprit.spring.DAO.Repositories.TokenRepository;
import tn.esprit.spring.DAO.Services.JWT.JwtService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EtudiantService implements IEtudiantService{
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;


    private final JwtService jwtService;


    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var etudiant = Etudiant.builder()
                .nomEt(registerRequest.getNomEt())
                .prenomEt(registerRequest.getPrenomEt())
                .cin(registerRequest.getCin())
                .ecole(registerRequest.getEcole())
                .dateNaissance(registerRequest.getDateNaissance())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .passwordDecoder(registerRequest.getPassword())
                .role(registerRequest.getRole())
                .build();
        var savedEtudiant = etudiantRepository.save(etudiant);
        var jwtToken = jwtService.generateToken(etudiant);
        var refreshToken = jwtService.generateRefreshToken(etudiant);
        saveEtudiantToken(savedEtudiant, jwtToken);
        return AuthenticationResponse.builder()
                .accesToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }



    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var etudiant = etudiantRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(etudiant);
        var refreshToken = jwtService.generateRefreshToken(etudiant);
        revokeAllEtudiantTokens(etudiant);
        saveEtudiantToken(etudiant, jwtToken);
        return AuthenticationResponse.builder()
                .accesToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null ) {
            var etudiant = this.etudiantRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, etudiant)) {
                var accessToken = jwtService.generateToken(etudiant);
                revokeAllEtudiantTokens(etudiant);
                saveEtudiantToken(etudiant, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accesToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void revokeAllEtudiantTokens(Etudiant etudiant) {
        var validEtudiantTokens = tokenRepository.findAllValidTokensByEtudiant(etudiant.getIdEtudiant());
        if (validEtudiantTokens.isEmpty()) {
            return;
        }
        validEtudiantTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validEtudiantTokens);
    }

    private void saveEtudiantToken(Etudiant etudiant, String jwtToken) {
        var token = Token.builder()
                .etudiant(etudiant)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }


    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {
        return etudiantRepository.saveAll(etudiants);
    }

    @Override
    public Etudiant editEtudiant(int id, Etudiant e) {
        if(etudiantRepository.findById(id).isPresent()){
            Etudiant toUpdateEtudiant = etudiantRepository.findById(id).get();
            toUpdateEtudiant.setNomEt(e.getNomEt());
            toUpdateEtudiant.setPrenomEt(e.getPrenomEt());
            toUpdateEtudiant.setCin(e.getCin());
            toUpdateEtudiant.setEcole(e.getEcole());
            toUpdateEtudiant.setDateNaissance(e.getDateNaissance());
            toUpdateEtudiant.setReservations(e.getReservations());

            return etudiantRepository.save(toUpdateEtudiant);
        }
        return null;
    }

    @Override
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant findById(int id) {
        return etudiantRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public void delete(Etudiant e) {
        etudiantRepository.delete(e);
    }


}
