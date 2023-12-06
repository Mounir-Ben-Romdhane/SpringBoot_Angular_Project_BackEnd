package tn.esprit.spring.DAO.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Services.Etudiant.IEtudiantService;
import tn.esprit.spring.DAO.Services.Foyer.FoyerService;
import tn.esprit.spring.DAO.Services.Foyer.IFoyerService;
import tn.esprit.spring.DAO.Entities.Universite;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("foyer")

@CrossOrigin(origins = "http://localhost:4200")

public class FoyerRestController {

    @Autowired
    IFoyerService iFoyerService;
    @Autowired
    FoyerService foyerService;
    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    List<Foyer> findAll(){
        return  iFoyerService.findAll();
    }

    @PostMapping("/add/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    Foyer addFoyer(@RequestBody Foyer f ,@PathVariable("id") Long IdUniversite) {
        return iFoyerService.addFoyer(f,IdUniversite);
    }

    @GetMapping("findAlluniversite")
    @PreAuthorize("hasRole('ADMIN')")
    List<Universite> findAllUniversite(){
        return iFoyerService.findAllUniversite();
    }


    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Foyer updateFoyer(@PathVariable("id") Long id, @RequestBody Foyer f){
        return iFoyerService.editFoyer(id, f);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteFoyer(@PathVariable("id") Long id){
        iFoyerService.deleteById(id);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Foyer findById(@PathVariable("id") Long id){
        return iFoyerService.findById(id);
    }



    @GetMapping("/search/{nomFoyer}")
    public ResponseEntity<List<Foyer>> searchFoyersByNomFoyer(@PathVariable String nomFoyer) {
        List<Foyer> foyers = foyerService.searchFoyersByNomFoyer(nomFoyer);
        return ResponseEntity.ok(foyers);
    }

    @GetMapping("/{id}/countChambres")
    public Long countChambresByFoyerId(@PathVariable Long id) {
        return foyerService.countChambresByFoyerId(id);
    }

    @GetMapping("/findByUniversite/{nomUniversite}")
    public ResponseEntity<List<Foyer>> findByUniversite(@PathVariable String nomUniversite) {
        List<Foyer> foyers = foyerService.findByUniversiteNamee(nomUniversite);
        return ResponseEntity.ok(foyers);
    }

    @GetMapping("/universite/{idFoyer}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Universite> getUniversiteByFoyer(@PathVariable long idFoyer) {
        Universite universite = iFoyerService.getUniversiteByFoyer(idFoyer);
        return new ResponseEntity<>(universite, HttpStatus.OK);
    }

}