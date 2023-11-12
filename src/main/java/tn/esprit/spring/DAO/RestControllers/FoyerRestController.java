package tn.esprit.spring.DAO.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.DAO.Entities.Foyer;
import tn.esprit.spring.DAO.Services.Etudiant.IEtudiantService;
import tn.esprit.spring.DAO.Services.Foyer.IFoyerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("foyer")
public class FoyerRestController {
    @Autowired
    IFoyerService iFoyerService;

    @GetMapping("/findAll")
    List<Foyer> findAll(){
        return  iFoyerService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    Foyer addFoyer(@RequestBody Foyer f) {
        return iFoyerService.addFoyer(f);
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
}