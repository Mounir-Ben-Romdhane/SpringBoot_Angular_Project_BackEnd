package tn.esprit.spring.DAO.RestControllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Services.Chambre.IChambreService;
import tn.esprit.spring.DAO.Services.Chambre.IChambreService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("chambre")
@CrossOrigin(origins = "http://localhost:4200")
public class ChambreRestController {
    @Autowired
    IChambreService iChambreService;

    @Autowired
    ChambreRepository chambreRepository;

    @GetMapping("/findAll")
    List<Chambre> findAll(){
        return  iChambreService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addChambre(@RequestBody Chambre chambre) {
        long numeroChambre = chambre.getNumeroChambre();

        if (!iChambreService.isNumeroChambreUnique(numeroChambre)) {
            return new ResponseEntity<>("Le numéro de chambre doit être unique", HttpStatus.CONFLICT);
        }

        Chambre nouvelleChambre = iChambreService.addChambre(chambre);
        return new ResponseEntity<>(nouvelleChambre, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateChambre(@PathVariable("id") Long idChambre, @RequestBody Chambre updatedChambre) {
        Chambre existingChambre = iChambreService.getChambreById(idChambre);

        if (existingChambre == null) {
            return new ResponseEntity<>("Chambre not found", HttpStatus.NOT_FOUND);
        }

        long numeroChambre = updatedChambre.getNumeroChambre();

        // Check for uniqueness, excluding the current chambre being updated
        if (!iChambreService.isNumeroChambreUniqueForUpdate(idChambre, numeroChambre)) {
            return new ResponseEntity<>("Le numéro de chambre doit être unique", HttpStatus.CONFLICT);
        }

        Chambre modifiedChambre = iChambreService.editChambre(idChambre, updatedChambre);
        return new ResponseEntity<>(modifiedChambre, HttpStatus.OK);
    }





    @GetMapping("/{id}")
    Chambre findById(@PathVariable("id") Long id){
        return iChambreService.findById(id);
    }

    @GetMapping("selectByNumSQL")
    List<Chambre> selectByNumSQL(long num){
        return chambreRepository.selectByNumSQL(num);
    }

    @GetMapping("/findByNumeroChambre/{numeroChambre}")
    public ResponseEntity<Chambre> findByNumeroChambre(@PathVariable long numeroChambre) {
        Chambre chambre = iChambreService.findByNumeroChambre(numeroChambre);
        if (chambre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chambre, HttpStatus.OK);
    }
    @GetMapping("/{chambreId}/occupee")
    public ResponseEntity<Boolean> isChambreOccupee(@PathVariable("chambreId") long chambreId) {
        boolean isOccupee = iChambreService.isChambreOccupee(chambreId);
        return ResponseEntity.ok(isOccupee);

}


    @GetMapping("/bloc/{idChambre}")
    public ResponseEntity<Bloc> getBlocByChambre(@PathVariable long idChambre) {
        Bloc bloc = iChambreService.getBlocByChambre(idChambre);
        return new ResponseEntity<>(bloc, HttpStatus.OK);
    }
    @GetMapping("/checkNumeroChambreUnique/{numeroChambre}")
    public ResponseEntity<Boolean> checkNumeroChambreUnique(@PathVariable long numeroChambre) {
        boolean isUnique = iChambreService.isNumeroChambreUnique(numeroChambre);
        return new ResponseEntity<>(isUnique, HttpStatus.OK);
    }



}
