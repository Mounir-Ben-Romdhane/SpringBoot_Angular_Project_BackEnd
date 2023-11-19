package tn.esprit.spring.DAO.Services.Etudiant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tn.esprit.spring.DAO.Dto.AuthenticationRequest;
import tn.esprit.spring.DAO.Dto.AuthenticationResponse;
import tn.esprit.spring.DAO.Dto.RegisterRequest;
import tn.esprit.spring.DAO.Dto.VerificationRequest;
import tn.esprit.spring.DAO.Entities.Etudiant;

import java.io.IOException;
import java.util.List;

public interface IEtudiantService {
    Etudiant addEtudiant(Etudiant b);

    List<Etudiant> addEtudiants(List<Etudiant> blocs);
    Etudiant editEtudiant(int id, Etudiant b);
    List<Etudiant> findAll();
    Etudiant findById(int id);
    void deleteById(int id);
    void delete(Etudiant b);


    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    AuthenticationResponse verifyCode(VerificationRequest verificationRequest);
}
