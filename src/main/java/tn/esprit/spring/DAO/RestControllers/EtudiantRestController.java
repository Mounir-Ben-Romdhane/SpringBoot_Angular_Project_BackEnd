package tn.esprit.spring.DAO.RestControllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Dto.AuthenticationResponse;
import tn.esprit.spring.DAO.Dto.AuthenticationRequest;
import tn.esprit.spring.DAO.Dto.RegisterRequest;
import tn.esprit.spring.DAO.Dto.VerificationRequest;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Services.Etudiant.IEtudiantService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("etudiant")
@RequiredArgsConstructor
public class EtudiantRestController {
    @Autowired
    IEtudiantService iEtudiantService;

    //Authentification
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        var response = iEtudiantService.register(registerRequest);
        if (registerRequest.isMFaEnabled()) {
            return ResponseEntity.ok(response);
        }
       return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(iEtudiantService.authenticate(authenticationRequest));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        iEtudiantService.refreshToken(request, response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(
            @RequestBody VerificationRequest verificationRequest
    ) {
        return ResponseEntity.ok(iEtudiantService.verifyCode(verificationRequest));
    }


    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    List<Etudiant> findAll(){
        return  iEtudiantService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    Etudiant addEtudiant(@RequestBody Etudiant e) {
        return iEtudiantService.addEtudiant(e);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Etudiant updateEtudiant(@PathVariable("id") int id, @RequestBody Etudiant e){
        return iEtudiantService.editEtudiant(id, e);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteEtudiant(@PathVariable("id") int id){
        iEtudiantService.deleteById(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Etudiant findById(@PathVariable("id") int id){
        return iEtudiantService.findById(id);
    }
}