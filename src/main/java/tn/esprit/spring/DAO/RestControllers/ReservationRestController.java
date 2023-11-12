package tn.esprit.spring.DAO.RestControllers;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Reservation;
import tn.esprit.spring.DAO.Services.Etudiant.IEtudiantService;
import tn.esprit.spring.DAO.Services.Reservation.IReservationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("reservation")
public class ReservationRestController {
    @Autowired
    IReservationService iReservationService;

    @GetMapping("/findAll")
    List<Reservation> findAll(){
        return  iReservationService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    Reservation addReservation(@RequestBody Reservation r) {
        return iReservationService.addReservation(r);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Reservation updateReservation(@PathVariable("id") String id, @RequestBody Reservation r){
        return iReservationService.editReservation(id, r);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteReservation(@PathVariable("id") String id){
        iReservationService.deleteById(id);
    }

    @GetMapping("/{id}")
    Reservation findById(@PathVariable("id") String id){
        return iReservationService.findById(id);
    }

    @PostMapping("/ajouterReservationEtAssignerAChambreEtAEtudiant/{numChambre}/{cin}")
    Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(@PathVariable("numChambre") Long numChambre,
                                                                @PathVariable("cin") Long cin) {
        return iReservationService.ajouterReservationEtAssignerAChambreEtAEtudiant(numChambre,cin);
    }
}